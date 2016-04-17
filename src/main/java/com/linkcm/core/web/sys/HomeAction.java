package com.linkcm.core.web.sys;

import java.util.Locale;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.linkcm.core.util.MessageUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/home")
@Results({ @Result(name = "toMain", location = "main.jsp"), @Result(name = "toCenter", location = "center.jsp"),
		@Result(name = "toMenu", location = "menu.jsp"), @Result(name = "toTop", location = "top.jsp"),
		@Result(name = "toHome", location = "home.jsp"), @Result(name = "toGiveUser", location = "giveUser.jsp"),
		@Result(name = "toIbsInformation", location = "ibsInformation.jsp"),
		@Result(name = "modifyPassword", location = "modifyPassword.jsp") })
public class HomeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Long nodeId;

	public String toMain() {
		String language = ActionContext.getContext().getLocale().getLanguage();
		if (language != null && !language.equals("zh") && !language.equals("en")) {
			Locale currentLocale = Locale.getDefault();
			currentLocale = new Locale("en", "US");
			ActionContext.getContext().setLocale(currentLocale);
			ServletActionContext.getRequest().getSession().setAttribute("WW_TRANS_I18N_LOCALE", currentLocale);
		}
		return "toMain";
	}

	/**
	 * 切换语言
	 */
	public String changeLanguage() {
		String language = ActionContext.getContext().getLocale().getLanguage();
		ActionContext.getContext().getSession().put("language", language);
		MessageUtils.success("个人设置", "切换语言");
		return null;
	}

	/**
	 * 切换主题
	 */
	public String changeTheme() {
		ServletActionContext.getRequest().getSession()
				.setAttribute("theme", ServletActionContext.getRequest().getParameter("j_theme"));
		MessageUtils.success("个人设置", "切换主题");
		return null;
	}

	public String toCenter() {
		return "toCenter";
	}

	public String toMenu() {
		return "toMenu";
	}

	public String toTop() {
		return "toTop";
	}

	public String toHome() {
		return "toHome";
	}

	public String toGiveUser() {
		return "toGiveUser";
	}

	public String toIbsInformation() {
		return "toIbsInformation";
	}

	public String modifyPassword() {
		return "modifyPassword";
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

}
