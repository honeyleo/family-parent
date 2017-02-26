package com.family.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.common.service.UserService;
import com.family.model.AccessToken;
import com.family.model.CurrentUser;
import com.family.service.TokenService;

import cn.lfy.base.model.User;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.model.Message;
import cn.lfy.common.utils.MessageDigestUtil;
import cn.lfy.common.utils.RequestUtil;
import cn.lfy.common.utils.Strings;
import cn.lfy.common.utils.Validators;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/oauth/login")
	@ResponseBody
	public Object login(HttpServletRequest request) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Validators.isFalse(StringUtils.isBlank(username) || StringUtils.isBlank(password), 
				ErrorCode.ERROR_USERNAME_OR_PASSWORD);
		
		User user = userService.findByUsername(username);
		Validators.notNull(user, ErrorCode.ERROR_USERNAME_OR_PASSWORD);
		
		password = MessageDigestUtil.getSHA256(password + user.getSalt());
		Validators.isFalse(!Strings.slowEquals(password, user.getPassword()), 
				ErrorCode.ERROR_USERNAME_OR_PASSWORD);
		
		CurrentUser currentUser = new CurrentUser();
		currentUser.setId(user.getId());
		currentUser.setUsername(user.getUsername());
		currentUser.setNickname(user.getNickname());
		currentUser.setPhone(user.getPhone());
		currentUser.setRegTime(user.getCreateTime().getTime()/1000);
		currentUser.setIp(RequestUtil.getUserIpAddr(request));
		currentUser.setSurname(user.getSurname());
		currentUser.setName(user.getName());
		
		AccessToken accessToken = tokenService.token(currentUser);
		Message.Builder builder = Message.newBuilder("/oauth/login");
		builder.data(accessToken);
		return builder.build();
	}
	
	@RequestMapping("/oauth/refresh_token")
	@ResponseBody
	public Object refreshToken(HttpServletRequest request) {
		String refreshToken = request.getParameter("access_token");
		Validators.notEmpty(refreshToken, ErrorCode.PARAM_ILLEGAL, "access_token");
		AccessToken accessToken = tokenService.refreshToken(refreshToken);
		Validators.notNull(accessToken, ErrorCode.ACCESS_TOKEN_INVALID);
		Message.Builder builder = Message.newBuilder("/oauth/refresh_token");
		return builder.data(accessToken).build();
	}
}
