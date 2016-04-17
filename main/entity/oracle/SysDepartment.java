package com.linkcm.core.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_SYS_DEPARTMENT")
public class SysDepartment {
	@Id
	@SequenceGenerator(name = "departmentGenerator", sequenceName = "S_T_SYS_DEPARTMENT", allocationSize = 1 )
	@GeneratedValue(generator = "departmentGenerator", strategy = GenerationType.SEQUENCE)
	@Column(name = "DEPT_ID")
	private Long id;
	
	@Column(name = "DEPT_CODE")
	private String deptCode;
	
	private String title;
	private String description;
	
	@Column(name = "RES_RATE")
	private int resRate;
	
	@Column(name = "AUDIT_VOLUMN")
	private int auditVolumn;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
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
	public int getResRate() {
		return resRate;
	}
	public void setResRate(int resRate) {
		this.resRate = resRate;
	}
	public int getAuditVolumn() {
		return auditVolumn;
	}
	public void setAuditVolumn(int auditVolumn) {
		this.auditVolumn = auditVolumn;
	}
}
