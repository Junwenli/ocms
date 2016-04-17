package com.linkcm.core.web;

import javax.servlet.http.HttpServletRequest;

import com.linkcm.core.service.CaptchaServiceSingleton;
import com.linkcm.core.util.MessageUtils;
import com.linkcm.core.util.WebUtils;
import com.octo.captcha.service.CaptchaServiceException;
import com.opensymphony.xwork2.ActionSupport;

public class JcaptchaAction extends ActionSupport {

	// 标志是否通过验证
	Boolean isResponseCorrect = Boolean.FALSE;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6801004634138106302L;

	public void checkJcaptcha() {
		HttpServletRequest request = WebUtils.getRequest();
		String captchaId = WebUtils.getSession().getId();
		// retrieve the response
		String responsestr = request.getParameter("j_captcha_response");
		try {
			isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaId, responsestr);
		} catch (CaptchaServiceException e) {
			MessageUtils.failure(ERROR, "验证码已过期!");
		}
		if (isResponseCorrect) {
			MessageUtils.success(SUCCESS, "验证码正确!");
		} else {
			MessageUtils.failure(ERROR, "验证码不正确!");
		}
	}
}
