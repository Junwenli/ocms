package com.linkcm.core.web.sys;

import org.apache.struts2.convention.annotation.Namespace;

import com.linkcm.core.util.MessageUtils;
import com.linkcm.core.util.WebUtils;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/common")
public class CommonAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 供基类的session用 module模块名，msg提示信息 addMsgToCoreSession添加信息进session
	 * */
	private String module;
	
	private String msg;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String addMsgToCoreSession() {
		MessageUtils.addMsgToSession(this.getModule(), this.getMsg());
		return null;
	}

	public String getLog() {
		WebUtils.renderJson(WebUtils.getSessionAttribute("MSG"));
		return null;
	}

}
