package com.family.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.lfy.common.filehandler.ResourceIdentifier;
import cn.lfy.common.filehandler.ResourceManager;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.model.Message;
import cn.lfy.common.utils.UUIDUtil;

import com.family.common.model.UserDetail;
import com.family.common.service.UserService;
import com.family.model.CurrentUser;
import com.family.service.UserProxyService;

@Controller
@RequestMapping("/app/user")
public class UserController {

	@Autowired
	private UserProxyService userProxyService;
	
	@Autowired
	private ResourceManager resourceManager;
	
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping("/avatar")
	@ResponseBody
	public Object avatar(CurrentUser user, MultipartFile file, HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/user/avatar");
		Map<String, Object> data = new HashMap<String, Object>();
		String fileName = UUIDUtil.uuid() + ".jpg";
		try {
			ResourceIdentifier dest = resourceManager.processResource("avatar_image", file.getBytes(), fileName);
			data.put("avatar", dest.getUrl());
			if(dest != null) {
				userService.updateAvatar(user.getId(), dest.getUrl());
			}
		} catch (IOException e) {
			throw new ApplicationException(ErrorCode.SERVER_ERROR);
		}
		return builder.data(data).build();
	}
}
