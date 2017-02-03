package com.family.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lfy.base.model.User;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.model.Message;
import cn.lfy.common.utils.MessageDigestUtil;
import cn.lfy.common.utils.RequestUtil;
import cn.lfy.common.utils.Strings;
import cn.lfy.common.utils.UUIDUtil;
import cn.lfy.common.utils.Validators;

import com.family.common.service.UserService;
import com.family.model.AccessToken;
import com.family.model.CurrentUser;
import com.family.service.TokenService;
import com.family.service.VerifyCodeService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private VerifyCodeService verifyCodeService;
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
		
		AccessToken accessToken = tokenService.token(currentUser);
		Message.Builder builder = Message.newBuilder();
		builder.data(accessToken);
		return builder.build();
	}
	
	@RequestMapping("oauth/refresh_token")
	@ResponseBody
	public Object refreshToken(HttpServletRequest request) {
		String refreshToken = request.getParameter("access_token");
		Validators.notEmpty(refreshToken, ErrorCode.PARAM_ILLEGAL, "access_token");
		AccessToken accessToken = tokenService.refreshToken(refreshToken);
		Validators.notNull(accessToken, ErrorCode.ACCESS_TOKEN_INVALID);
		return Message.newBuilder().data(accessToken).build();
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public Object register(@RequestParam(name = "phone") String phone, 
			@RequestParam(name = "code") String code, 
			@RequestParam(name = "password") String password, 
			HttpServletRequest request) {
		User user = userService.findByUsername(phone);
		Validators.isFalse(user != null, ErrorCode.EXIST);
		verifyCodeService.verifyCodeAndDel("REG", phone, code);
		User record = new User();
		record.setUsername(phone);
		String salt = UUIDUtil.salt();
		record.setSalt(salt);
		record.setPassword(MessageDigestUtil.getSHA256(password + salt));
		record.setPhone(phone);
		record.setNickname("");
		record.setState(1);
		record.setEmail("");
		record.setCreateTime(new Date());
		userService.add(record);
		Message.Builder builder = Message.newBuilder();
		return builder.build();
	}
}
