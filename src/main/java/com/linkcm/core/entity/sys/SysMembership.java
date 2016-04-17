package com.linkcm.core.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Desc 	用户与角色关联表
 * @author 	ChunPIG
 * @date 	2012-1-16
 * @version 1.0
 */
@Entity
@Table(name="T_SYS_MEMBERSHIP")
public class SysMembership {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "MEMBERSHIP_ID")
	private Long membershipId;
	
	@Column(name = "ROLE_ID")
	private Long roleId;
	
	@Column(name = "USER_ID")
	private Long userId;
	public Long getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(Long membershipId) {
		this.membershipId = membershipId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
