package com.linkcm.core.web.sys;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkcm.core.annotation.WebRequest;
import com.linkcm.core.annotation.WebRequest.RequestType;
import com.linkcm.core.entity.sys.SysMembership;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.sys.SysMembershipService;
import com.linkcm.core.util.WebUtils;
import com.linkcm.core.web.CoreAction;

/**
 * @Desc 用户角色
 * @author ChunPIG
 * @date 2012-1-16
 * @version 1.0
 */
@Namespace("/membership")
public class MembershipAction extends CoreAction<SysMembership, Long> {

	private static final long serialVersionUID = -6213838974675254418L;

	private String roleId;

	private Long userId;

	@Autowired
	private SysMembershipService sysMembershipService;

	/**
	 * 保存角色和用户的关联
	 * 
	 * @return
	 */
	@WebRequest(RequestType.AJAX)
	public void saveUserRoleShip() {
		List<Long> list = new LinkedList<Long>();
		if (!StringUtils.isEmpty(roleId) && roleId.length() > 0) {
			String[] roleIds = roleId.split(",");
			for (String tmpId : roleIds) {
				list.add(Long.parseLong(tmpId));
			}
		}
		sysMembershipService.saveUserRoleShip((Long[]) list.toArray(new Long[list.size()]), userId);

		success("saveUserRoleShip_success");
	}

	/**
	 * 获取所有实体
	 * 
	 * @return
	 */
	@WebRequest(RequestType.AJAX)
	public void findAll() {
		WebUtils.renderJson(sysMembershipService.findAllByRoleId(userId));
	}

	@Override
	public CoreService<SysMembership, Long> getService() {
		return sysMembershipService;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
