package com.family.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.family.common.model.UserDetail;
import com.family.common.service.UserService;
import com.family.model.CurrentUser;

@Component
public class UserProxyService {

	@Autowired
	private UserService userService;
	
	public CurrentUser getCurrentUser(long uid) {
		return null;
	}
	
	public UserDetail getUserDetail(long id) {
		return userService.getUserDetail(id);
	}
}
