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
			Object currentUser = request.getSession().getAttribute("CURRENT_USER");
			if(currentUser == null) {
				String accessToken = request.getHeader("access_token");
				if(accessToken == null) {
					accessToken = request.getParameter("access_token");
				}
				currentUser = tokenService.verifyAccessToken(accessToken);
				request.getSession().setAttribute("CURRENT_USER", currentUser);
			}
		}
		return super.preHandle(request, response, handler);
	}

}
