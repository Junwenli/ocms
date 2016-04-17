package com.linkcm.core.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.linkcm.core.annotation.Validation;
import com.linkcm.core.entity.type.RoleStatus;

/**
 * 角色.
 * 
 * 
 * @author pengyi
 */
@Entity
@Table(name = "T_SYS_ROLE")
public class SysRole {

	@Id
	@SequenceGenerator(name = "roleGenerator", sequenceName = "S_T_SYS_ROLE", allocationSize = 1 )
	@GeneratedValue(generator = "roleGenerator", strategy = GenerationType.SEQUENCE)
	@Column(name = "ROLE_ID")
	private Long id;

	@Column(name = "ROLE_CODE")
	private String roleCode;

	@Validation(name = "角色名称", min = 2, max = 20, nullable = false)
	@Column(name = "TITLE")
	private String title;

	private String description;

	@Validation(name = "角色状态", enumClass = RoleStatus.class)
	private int status = 1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return RoleStatus.getDesc(this.getStatus());
	}

}
