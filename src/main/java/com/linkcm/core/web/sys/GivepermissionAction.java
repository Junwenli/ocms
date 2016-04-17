package com.linkcm.core.web.sys;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkcm.core.annotation.WebRequest;
import com.linkcm.core.annotation.WebRequest.RequestType;
import com.linkcm.core.entity.sys.SysGivepermission;
import com.linkcm.core.security.MyInvocationSecurityMetadataSource;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.sys.SysGivePermissionService;
import com.linkcm.core.util.SecurityUtils;
import com.linkcm.core.util.WebUtils;
import com.linkcm.core.web.CoreAction;

@Namespace("/givepermission")
public class GivepermissionAction extends CoreAction<SysGivepermission, Long> {

	private static final long serialVersionUID = 1L;

	private Long userId;

	private String resourceIds;

	@Autowired
	private SysGivePermissionService sysGivePermissionService;

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
		sysGivePermissionService.save(userId, (Long[]) list.toArray(new Long[list.size()]));
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
		WebUtils.renderJson(sysGivePermissionService.findAllByUserId(userId, SecurityUtils.getUserId()));
	}

	@Override
	public CoreService<SysGivepermission, Long> getService() {
		return sysGivePermissionService;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

}
