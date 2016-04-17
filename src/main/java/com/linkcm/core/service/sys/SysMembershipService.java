package com.linkcm.core.service.sys;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysMembership;
import com.linkcm.core.entity.type.RoleStatus;
import com.linkcm.core.repository.sys.SysMembershipRepository;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.util.EasyUiUtils;
import com.linkcm.core.web.EasyUiDataGrid;

/**
 * @Desc 用户角色关联业务类
 * @author ChunPIG
 * @date 2012-1-16
 * @version 1.0
 */
@Component
@Transactional
public class SysMembershipService extends CoreService<SysMembership, Long> {

	@Autowired
	private SysMembershipRepository sysMembershipRepository;

	@Override
	protected BaseRepository<SysMembership, Long> getDao() {
		return sysMembershipRepository;
	}

	/**
	 * 保存用户授予的角色
	 * 
	 * @param roleIds
	 * @param userId
	 */
	@ErrorMessage("sys_membership_saveFail")
	public void saveUserRoleShip(Long[] roleIds, Long userId) {
		List<SysMembership> exists = sysMembershipRepository.findByUserId(userId);
		List<SysMembership> create = getCreateList(userId, roleIds, exists);
		List<SysMembership> remove = getRemoveList(roleIds, exists);
		save(create.toArray(new SysMembership[0]));
		for (SysMembership membership : remove) {
			delete(membership.getMembershipId());
		}
	}

	/**
	 * 获取所有实体
	 * 
	 * @param sysRoleDao
	 */
	@ErrorMessage("sys_membership_getListFail")
	public EasyUiDataGrid findAllByRoleId(Long userId) {
		List<?> result = sysMembershipRepository.findVoByRoleId(userId);
		RoleStatus.transformer.transfer("status", result);
		return EasyUiUtils.getEasyUiDataGrid(result);
	}

	/**
	 * 组装用户与角色的集合
	 * 
	 * @param userId
	 * @param roleIds
	 * @param exists
	 * @return
	 */
	private List<SysMembership> getCreateList(Long userId, Long[] roleIds, List<SysMembership> exists) {
		List<SysMembership> create = new LinkedList<SysMembership>();
		boolean existsMembership = false;
		for (Long roleId : roleIds) {
			for (SysMembership membership : exists) {
				if (roleId == null || "".equals(roleId) || membership.getRoleId().equals(roleId)) {
					existsMembership = true;
					break;
				}
			}
			if (existsMembership) {
				existsMembership = false;
			} else {
				SysMembership membership = new SysMembership();
				membership.setRoleId(roleId);
				membership.setUserId(userId);
				create.add(membership);
			}
		}
		return create;
	}

	/**
	 * 删除已存在的角色
	 * 
	 * @param roleId
	 * @param resourceIds
	 * @param exists
	 * @return
	 */
	private List<SysMembership> getRemoveList(Long[] roleIds, List<SysMembership> exists) {
		List<SysMembership> remove = new LinkedList<SysMembership>();
		boolean existsMembership = false;
		for (SysMembership membership : exists) {
			for (Long roleId : roleIds) {
				if (membership.getRoleId().equals(roleId)) {
					existsMembership = true;
					break;
				}
			}
			if (existsMembership) {
				existsMembership = false;
			} else {
				remove.add(membership);
			}
		}
		return remove;
	}

	/**
	 * 通过角色ID获取所有的用户ID
	 * 
	 * @param roldId
	 * @return
	 */
	@ErrorMessage("获取用户失败")
	public List<SysMembership> getUserIdByRole(Long roleId) {
		return sysMembershipRepository.findByRoleId(roleId);
	}

	@Override
	public String getModule() {
		return "角色管理";
	}
}
