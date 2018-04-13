package com.nanzhao2018.web.util;

import com.nanzhao2018.dao.WebUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * @author Ler
 */
public class SessionUtil {
	
	private static final String CURRENT_IN_SESSION = "loginInfo";
	
	private static HttpSession session() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}
	
	private static Cookie[] cookie() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getCookies();
	}
	
	public static WebUser getUserInSession() {
		return (WebUser) session().getAttribute(CURRENT_IN_SESSION);
	}
	
	public static void putWebUserInSession(WebUser webUser) {
		session().setAttribute(CURRENT_IN_SESSION , webUser);
	}
}
