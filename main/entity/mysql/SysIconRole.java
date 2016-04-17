package com.ericsson.core.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_SYS_ICON_ROLE")
public class SysIconRole {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ITEM_ID")
	private Long id;

	@Column(name = "ROLE_ID")
	private Long roleId;

	@Column(name = "ICON_ID")
	private Long iconId;

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

	public Long getIconId() {
		return iconId;
	}

	public void setIconId(Long iconId) {
		this.iconId = iconId;
	}

}
