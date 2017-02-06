package com.family.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.family.common.model.UserDetail;
import com.family.common.service.UserService;
import com.family.model.CurrentUser;

import cn.lfy.common.cache.RedisClient;
import cn.lfy.common.utils.RedisKey;

@Component
public class UserProxyService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisClient redisClient;
	
	public CurrentUser getCurrentUser(long uid) {
		String key = RedisKey.currentUserKey(uid);
		String value = redisClient.get(key);
		if(StringUtils.isNotBlank(value)) {
			CurrentUser user = JSON.parseObject(value, CurrentUser.class);
			return user;
		} else {
			
		}
		return null;
	}
	
	public UserDetail getUserDetail(long id) {
		return userService.getUserDetail(id);
	}
}
