package com.ericsson.core.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ericsson.core.entity.AbstractEntity;


/**
 * 系统参数表
 * 
 * 
 * @author ZJY
 */
@Entity
@Table(name = "T_SYS_PARAM")
public class SysParam extends AbstractEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PARAM_ID")
	private Long id ;
	
	@Column(name = "PARAM_NAME")
	private String paramName;
	
	@Column(name = "PARAM_VALUE")
	private String paramValue ;

	
	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	
}
