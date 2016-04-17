package com.linkcm.core.web.sys;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkcm.core.annotation.WebRequest;
import com.linkcm.core.annotation.WebRequest.RequestType;
import com.linkcm.core.entity.sys.SysPermission;
import com.linkcm.core.security.MyInvocationSecurityMetadataSource;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.sys.SysPermissionService;
import com.linkcm.core.util.WebUtils;
import com.linkcm.core.web.CoreAction;

@Namespace("/permission")
public class PermissionAction extends CoreAction<SysPermission, Long> {

	private static final long serialVersionUID = 1L;

	private Long roleId;

	private String resourceIds;

	@Autowired
	private SysPermissionService sysPermissionService;

	@Autowired
	private MyInvocationSecurityMetadataSource myInvocationSecurityMetadataSource;

	@WebRequest(RequestType.AJAX)
	public void save() {
		List<Long> list = new LinkedList<Long>();
		if (!StringUtils.isEmpty(resourceIds) && resourceIds.length() > 0) {
			String[] resourceId = resourceIds.split(",");
			for (String tmpId : resourceId) {
				list.add(Long.parseLong(tmpId));
			}
		}
		sysPermissionService.save(roleId, (Long[]) list.toArray(new Long[list.size()]));
		myInvocationSecurityMetadataSource.updateResource();
		success("save_success");
	}

	/**
	 * 获取所有实体
	 * 
	 * @return
	 */
	@WebRequest(RequestType.AJAX)
	public void findAll() {
		WebUtils.renderJson(sysPermissionService.findAllByRoleId(roleId));
	}

	@Override
	public CoreService<SysPermission, Long> getService() {
		return sysPermissionService;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

}
