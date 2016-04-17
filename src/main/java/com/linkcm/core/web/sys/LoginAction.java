package com.linkcm.core.web.sys;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/login")
@Results({ @Result(name = "index", location = "/common/main.jsp") })
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String index() {
		return SUCCESS;
	}

}
