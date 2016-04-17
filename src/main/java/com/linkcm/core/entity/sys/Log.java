package com.linkcm.core.entity.sys;

import com.linkcm.core.util.I18nUtils;

public class Log {

	private String time;

	private String module;

	private String msg;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = I18nUtils.getMassage(module);
	}

	public String getMsg() {
		return I18nUtils.getMassage(msg);
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
