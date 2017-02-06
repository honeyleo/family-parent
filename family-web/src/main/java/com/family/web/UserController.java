package com.family.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.common.model.UserDetail;
import com.family.model.CurrentUser;
import com.family.service.UserProxyService;

import cn.lfy.common.model.Message;

@Controller
@RequestMapping("/app/user")
public class UserController {

	@Autowired
	private UserProxyService userProxyService;
	
	@RequestMapping("/me")
	@ResponseBody
	public Object me(CurrentUser user, HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user/me");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("basic", user);
		UserDetail mine = userProxyService.getUserDetail(user.getId());
		data.put("mine", mine);
		return builder.data(data).build();
	}
}
