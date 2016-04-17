package com.linkcm.core.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Desc 	用户与权限关联表
 * @author 	hujie
 * @date 	2012-4-26
 * @version 1.0
 */
@Entity
@Table(name="T_SYS_GIVEPERMISSION")
public class SysGivepermission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ITEM_ID")
	private Long id;
	
	@Column(name = "PERMISSION_ID")
	private Long permissionId;
	
	@Column(name = "USER_ID")
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
