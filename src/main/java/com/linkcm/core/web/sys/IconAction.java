package com.linkcm.core.web.sys;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.linkcm.core.annotation.WebRequest;
import com.linkcm.core.annotation.WebRequest.RequestType;
import com.linkcm.core.entity.sys.SysIcon;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.sys.SysIconService;
import com.linkcm.core.util.WebUtils;
import com.linkcm.core.web.CoreAction;

@Namespace("/icon")
public class IconAction extends CoreAction<SysIcon, Long> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private SysIconService sysIconService;

	private String roleIds;

	@Override
	public CoreService<SysIcon, Long> getService() {
		return sysIconService;
	}

	@Override
	public Sort getDefaultSort() {
		return new Sort("id");
	}

	@WebRequest(RequestType.AJAX)
	public void findRoleByIconId() {
		WebUtils.renderJson(sysIconService.findRoleByIconId(getId()));
	}

	@WebRequest(RequestType.AJAX)
	public void grant() {
		String[] roleId = roleIds.split(",");

		List<Long> list = new LinkedList<Long>();
		for (String tmpId : roleId) {
			list.add(Long.parseLong(tmpId));
		}
		sysIconService.grant(getId(), (Long[]) list.toArray(new Long[list.size()]));
		success("grant_success");
	}

	public String listRole() {
		return "list_role";
	}

	@WebRequest(RequestType.AJAX)
	public void findIconByUserId() {
		WebUtils.renderJson(sysIconService.findIconByUserId());
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

}
