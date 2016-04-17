package com.linkcm.core.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.linkcm.core.entity.type.UserStatus;

@Entity
@Table(name = "T_SYS_USERS")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class SysUser {
	@Id
	@SequenceGenerator(name = "userGenerator", sequenceName = "S_T_SYS_USERS", allocationSize = 1 )
	@GeneratedValue(generator = "userGenerator", strategy = GenerationType.SEQUENCE)
	@Column(name = "USER_ID")
	private Long id;
	
	@Column(name = "USER_CODE")
	private String userCode;

	private String authenticator;

	@Column(name = "DEPT_ID")
	private Long deptId;

	private String name;
	private String email;
	private String mobile;
	@Column(name = "WRONG_NUMBER")
	private Integer wrongNumber=0;

	@Column(name = "CREATE_TIME")
	private String createTime;
	private int status=1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(String authenticator) {
		this.authenticator = authenticator;
	}
	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return UserStatus.getDesc(status);
	}

	public int getWrongNumber() {
		return wrongNumber;
	}

	public void setWrongNumber(int wrongNumber) {
		this.wrongNumber = wrongNumber;
	}
	
	

}
