package com.family.web.filter;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.framework.exception.ErrorCode;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	/** 忽略拦截请求的Url **/
	public static Set<String> ignoreUrl = new HashSet<String>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String requestUrl = request.getRequestURI();

		if (!(ignoreUrl.contains(requestUrl))) {

			if (!LoginVerify(request)) {
				throw ApplicationException.newInstance(ErrorCode.PERMISSION_DENIED);
			}
		}
		return super.preHandle(request, response, handler);
	}

	private boolean LoginVerify(HttpServletRequest request) {
		return true;
	}

}
