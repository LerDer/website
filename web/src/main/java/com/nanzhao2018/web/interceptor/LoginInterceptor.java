package com.nanzhao2018.web.interceptor;

import com.nanzhao2018.web.annotation.RequireLogin;
import com.nanzhao2018.web.util.SessionUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ler
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request , HttpServletResponse response , Object handler) throws
			Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			if (handlerMethod.hasMethodAnnotation(RequireLogin.class) && SessionUtil.getUserInSession() == null) {
				response.sendRedirect("/welcome.html");
				return false;
			}
		}
		return super.preHandle(request , response , handler);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request , HttpServletResponse response , Object handler , Exception
			ex) throws Exception {
		super.afterCompletion(request , response , handler , ex);
	}
}

