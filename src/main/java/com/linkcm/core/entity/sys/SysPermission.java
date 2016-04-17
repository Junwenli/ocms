package com.linkcm.core.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * <b>T_SYS_PERMISSION</b> 是
 * </p>
 * 
 * @since 2012-0-6
 * @author zml
 * @version $Id:$
 * 
 */
@Entity
@Table(name="T_SYS_ROLE_PERMISSION")
public class SysPermission {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ITEM_ID")
	private Long id;
	
	@Column(name = "ROLE_ID")
	private Long roleId;
	
	@Column(name = "PERMISSION_ID")
	private Long permissionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	

}
