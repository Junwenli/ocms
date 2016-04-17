package com.linkcm.core.web.sys;

import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import com.linkcm.core.annotation.WebRequest;
import com.linkcm.core.annotation.WebRequest.RequestType;
import com.linkcm.core.entity.sys.SysDepartment;
import com.linkcm.core.entity.sys.SysUser;
import com.linkcm.core.entity.type.UserStatus;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.sys.SysUserService;
import com.linkcm.core.util.SecurityUtils;
import com.linkcm.core.util.WebUtils;
import com.linkcm.core.web.CoreAction;

@Namespace("/user")
public class UserAction extends CoreAction<SysUser, Long> {

	private static final long serialVersionUID = 1L;

	private String authenticator;

	// 角色业务类
	@Autowired
	private SysUserService sysUserMgtService;

	private String oldPwd;
	
	/**
	 * 用户修改密码
	 * 
	 * @return
	 */
	@WebRequest(RequestType.AJAX)
	public void modifyPwd() {
		sysUserMgtService.modifyPwd(SecurityUtils.getUserId(), oldPwd, authenticator);
		success("modifyPwd_success");
	}

	/**
	 * 跳转到user-pwd页面。
	 * 
	 * @return
	 */
	public String pwd() {
		return "pwd";
	}

	/**
	 * 修改所有人密码
	 * 
	 * @return
	 */
	@WebRequest(RequestType.AJAX)
	public void updatePwd() {
		sysUserMgtService.updatePwd(getId(), authenticator);
		success("modifyPwd_success");
	}

	/**
	 * 获得小于当前用户的权限的用户信息
	 * 
	 * @return
	 */
	@WebRequest(RequestType.AJAX)
	public void findByUserId() {
		WebUtils.renderJson(sysUserMgtService.findByUserId(SecurityUtils.getUserId()));
	}

	public Map<Object, Object> getDepartmentMap() {
		return getTableMap(SysDepartment.class, "id", "title");
	}

	public Map<Object, Object> getStatusMap() {
		return getChooseMap(UserStatus.values());
	}

	@Override
	public CoreService<SysUser, Long> getService() {
		return sysUserMgtService;
	}

	@Override
	public Sort getDefaultSort() {
		return new Sort("id");
	}

	public String getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(String authenticator) {
		this.authenticator = authenticator;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

}
