package com.linkcm.core.web.sys;

import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.linkcm.core.entity.sys.SysRole;
import com.linkcm.core.entity.type.RoleStatus;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.sys.SysRoleService;
import com.linkcm.core.web.CoreAction;

/**
 * 角色管理
 * 
 * @author antking
 */
@Namespace("/role")
public class RoleAction extends CoreAction<SysRole, Long> {

	private static final long serialVersionUID = 7163482057522195747L;

	@Autowired
	private SysRoleService sysRoleMgtService;

	@Override
	public CoreService<SysRole, Long> getService() {
		return sysRoleMgtService;
	}
	@Override
	public Sort getDefaultSort() {
		return new Sort("id");
	}

	public Map<Object, Object> getStatusMap() {
		return getChooseMap(RoleStatus.values());
	}

}
