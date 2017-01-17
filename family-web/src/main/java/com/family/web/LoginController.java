package com.family.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.model.AccessToken;
import com.family.model.CurrentUser;
import com.family.service.TokenService;

import cn.lfy.base.model.User;
import cn.lfy.base.service.UserService;
import cn.lfy.common.cache.RedisClient;
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
	
	@Autowired
	private RedisClient redisClient;
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/login")
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
		
		AccessToken accessToken = tokenService.token(currentUser);
		Message.Builder builder = Message.newBuilder();
		builder.data(accessToken);
		return builder.build();
	}
	
	@RequestMapping("/refresh_token")
	@ResponseBody
	public Object refreshToken(HttpServletRequest request) {
		String refreshToken = request.getParameter("access_token");
		Validators.notEmpty(refreshToken, ErrorCode.PARAM_ILLEGAL, "access_token");
		AccessToken accessToken = tokenService.refreshToken(refreshToken);
		Validators.notNull(accessToken, ErrorCode.ACCESS_TOKEN_INVALID);
		return Message.newBuilder().data(accessToken).build();
	}
	
	@RequestMapping("/redis")
	@ResponseBody
	public Object redis(HttpServletRequest request) {
		redisClient.set("test", "test");
		String value = redisClient.get("test");
		return Message.newBuilder().data(value).build();
	}
}
