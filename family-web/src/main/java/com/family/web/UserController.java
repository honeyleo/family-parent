package com.family.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lfy.common.model.Message;

import com.family.common.model.UserDetail;
import com.family.model.CurrentUser;
import com.family.service.UserProxyService;

@Controller
@RequestMapping("/app/user")
public class UserController {

	@Autowired
	private UserProxyService userProxyService;
	
	@RequestMapping("/me")
	@ResponseBody
	public Object me(CurrentUser user, HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder();
		builder.put("basic", user);
		UserDetail mine = userProxyService.getUserDetail(user.getId());
		builder.put("mine", mine);
		return builder.build();
	}
}
