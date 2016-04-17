package com.linkcm.core.util;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.CannotCreateTransactionException;

import com.linkcm.core.entity.sys.CoreLogger;
import com.linkcm.core.entity.sys.Log;

public final class MessageUtils {
	
	private MessageUtils() {

	}

	/**
	 * 返回成功消息
	 * 
	 * */
	public static void success(String module, String msg) {
		sendMessage(module, msg, true);
	}

	/**
	 * 返回失败消息
	 * 
	 * */
	public static void failure(String module, Exception e, Class<?> clazz) {
		if (e instanceof CannotCreateTransactionException) {// 这个异常无法在service捕获
			CoreLogger.getCoreLogger(clazz).error("数据库异常", e);
			failure(module, "数据库异常,请联系管理员!");
		} else {
			failure(module, e.getMessage());
		}
	}

	/**
	 * 返回失败消息
	 * 
	 * */
	public static void failure(String module, String msg) {
		sendMessage(module, msg, false);
	}

	private static void sendMessage(String module, String msg, boolean success) {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		messageMap.put("success", success);
		messageMap.put("msg", I18nUtils.getMassage(msg));
		addMsgToSession(module, msg);
		WebUtils.renderJson(messageMap);
	}

	@SuppressWarnings("unchecked")
	public static void addMsgToSession(String module, String msg) {
		List<Log> msgList = null;
		Log log = new Log();
		log.setModule(module);
		log.setTime(DateFormatUtils.format(new Date(), "HH:mm:ss yyyy-MM-dd"));
		log.setMsg(msg);
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (session.getAttribute("MSG") != null) {
			msgList = (List<Log>) session.getAttribute("MSG");

		} else {
			msgList = new LinkedList<Log>();
			session.setAttribute("MSG", msgList);
		}
		final int maxCount  = 29;
		if (msgList.size() > maxCount) {
			msgList.remove(maxCount);
		}
		msgList.add(0, log);
 
	}

}
