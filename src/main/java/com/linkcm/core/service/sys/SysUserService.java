package com.linkcm.core.service.sys;

import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysMembership;
import com.linkcm.core.entity.sys.SysUser;
import com.linkcm.core.entity.type.RoleStatus;
import com.linkcm.core.repository.sys.SysMembershipRepository;
import com.linkcm.core.repository.sys.SysUserRepository;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.ServiceException;
import com.linkcm.core.util.EasyUiUtils;
import com.linkcm.core.util.EncodeUtils;
import com.linkcm.core.web.EasyUiDataGrid;

@Component
@Transactional
public class SysUserService extends CoreService<SysUser, Long> {

	@Autowired
	private SysUserRepository sysUserRepository;

	@Autowired
	private SysMembershipRepository sysMembershipRepository;

	@Autowired
	private SysMembershipService sysMembershipService;

	/**
	 * 保存用户修改密码
	 * 
	 * @param user
	 */
	@ErrorMessage("修改密码失败")
	public void modifyPwd(Long userId, String oldPwd, String pwd) {
		SysUser entity = sysUserRepository.findOne(userId);
		entity = this.sysUserRepository.findByIdAndAuthenticator(entity.getUserCode(),
				EncodeUtils.toMD5(entity.getUserCode() + oldPwd).toUpperCase());
		if (entity != null) {
			String newPwd = entity.getUserCode() + pwd;
			entity.setAuthenticator(EncodeUtils.toMD5(newPwd).toUpperCase());
		} else {
			throw new ServiceException("原密码不正确");
		}
		sysUserRepository.save(entity);
	}

	protected void beforeSave(SysUser entity, boolean operate) {
		if (isEntityUniqueById(entity.getId(), "userCode", entity.getUserCode())) {
			throw new ServiceException("sys_user_codeExist");
		}
		if (operate) {
			Date now = new Date();
			String time = DateUtil.formatDate(now, "yyyyMMddhhmmss");
			entity.setCreateTime(time);
			String newPwd = entity.getUserCode() + entity.getAuthenticator();
			entity.setAuthenticator(EncodeUtils.toMD5(newPwd).toUpperCase());
		}
	}

	protected void beforeDelete(SysUser entity) {
		if ("admin".equals(entity.getUserCode())) {
			throw new ServiceException("sys_role_deleteAdminUser");

		}
		List<SysMembership> list = sysMembershipRepository.findByUserId(entity.getId());
		sysMembershipService.delete(list);
	}

	/**
	 * 修改密码
	 * 
	 * @param entity
	 */
	@ErrorMessage("修改密码失败")
	public void updatePwd(Long userId, String pwd) {
		SysUser user = sysUserRepository.findOne(userId);
		String newPwd = user.getUserCode() + pwd;
		user.setAuthenticator(EncodeUtils.toMD5(newPwd).toUpperCase());
		sysUserRepository.save(user);
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param name
	 *            用户名
	 * @return SysUser
	 */
	@ErrorMessage("获取用户失败")
	public SysUser findByName(String name) {
		return sysUserRepository.findByName(name);
	}

	/**
	 * 根据用户userCode查找用户
	 * 
	 * @param userCode
	 *            用户userCode
	 * @return SysUser
	 */
	@ErrorMessage("获取用户失败")
	public SysUser findByUserCode(String userCode) {
		return sysUserRepository.findByUserCode(userCode);
	}

	/**
	 * 获得小于当前用户的权限的用户信息
	 * 
	 * @return
	 */

	@ErrorMessage("获取用户失败")
	public EasyUiDataGrid findByUserId(Long userId) {
		List<?> result = sysUserRepository.findByUserId(userId);
		RoleStatus.transformer.transfer("status", result);
		return EasyUiUtils.getEasyUiDataGrid(result);
	}

	@Override
	protected BaseRepository<SysUser, Long> getDao() {
		return sysUserRepository;
	}

	@Override
	public String getModule() {
		return "用户管理";
	}
}
