package com.family.web.filter;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.family.service.TokenService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TokenService tokenService;
	
	/** 忽略拦截请求的Url **/
	public static Set<String> ignoreUrl = new HashSet<String>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String requestUrl = request.getRequestURI();

		if (!(ignoreUrl.contains(requestUrl))) {
			String accessToken = request.getHeader("access_token");
			if(accessToken == null) {
				accessToken = request.getParameter("access_token");
			}
			tokenService.verifyAccessToken(accessToken);
		}
		return super.preHandle(request, response, handler);
	}

}
