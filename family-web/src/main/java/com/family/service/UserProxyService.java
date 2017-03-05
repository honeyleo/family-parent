package com.family.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.family.common.model.UserDetail;
import com.family.common.model.UserDetailDTO;
import com.family.common.service.UserDetailService;
import com.family.common.service.UserService;
import com.family.model.AccessToken;
import com.family.model.CurrentUser;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.lfy.base.model.User;
import cn.lfy.common.cache.RedisClient;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.utils.MessageDigestUtil;
import cn.lfy.common.utils.RedisKey;
import cn.lfy.common.utils.Strings;
import cn.lfy.common.utils.Validators;

@Component
public class UserProxyService {

	private static final int EXPIRE_CURRENT_USER = 7*24*60*60;
	
	private static final Logger LOG = LoggerFactory.getLogger(UserProxyService.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private RedisClient redisClient;
	
	@Autowired
	private TokenService tokenService;
	
	public CurrentUser getCurrentUser(long uid) {
		String key = RedisKey.currentUserKey(uid);
		String value = redisClient.get(key);
		CurrentUser currentUser = null;
		if(StringUtils.isNotBlank(value)) {
			try {
				currentUser = JSON.parseObject(value, CurrentUser.class);
			} catch(Throwable t) {
				LOG.error("解析CurrentUser JSON数据错误.uid={}", uid);
			}
			if(currentUser != null) {
				return currentUser;
			}
		} 
		User user = userService.findById(uid);
		if(user != null) {
			UserDetail userDetail = getUserDetail(uid);
			currentUser = refreshToCache(user, userDetail, "");
		}
		return currentUser;
	}
	
	/**
	 * 获取用户信息列表
	 * @param uids
	 * @return
	 */
	public Map<Long, CurrentUser> getCurrentUsers(Collection<Long> uids) {
		Map<Long, CurrentUser> map = Maps.newLinkedHashMap();
		String[] keys = new String[uids.size()];
		Iterator<Long> it = uids.iterator();
		int i = 0;
		while(it.hasNext()) {
			keys[i] = RedisKey.currentUserKey(it.next());
			i++;
		}
		try {
			List<String> valueList = redisClient.mget(keys);
			for(String value : valueList) {
				if(StringUtils.isNotBlank(value)) {
					try {
						CurrentUser currentUser = JSON.parseObject(value, CurrentUser.class);
						map.put(currentUser.getId(), currentUser);
					} catch(Throwable t) {
						LOG.error("解析CurrentUser JSON Data Error");
					}
				}
			}
		} catch(Throwable t) {
			LOG.error("From Cache mget error.");
		}
		it = uids.iterator();
		List<Long> notInCacheUid = Lists.newArrayList();
		while(it.hasNext()) {
			long uid = it.next();
			if(!map.containsKey(uid)) {
				notInCacheUid.add(uid);
			}
		}
		if(!notInCacheUid.isEmpty()) {
			List<UserDetailDTO> list =  userDetailService.getUserDetailDTOList(notInCacheUid);
			Iterator<UserDetailDTO> it2 = list.iterator();
			while(it2.hasNext()) {
				CurrentUser currentUser = refreshToCache(it2.next(), "");
				map.put(currentUser.getId(), currentUser);
			}
		}
		return map;
	}
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	public AccessToken login(String username, String password, String ip) {
		User user = userService.findByUsername(username);
		Validators.notNull(user, ErrorCode.ERROR_USERNAME_OR_PASSWORD);
		
		password = MessageDigestUtil.getSHA256(password + user.getSalt());
		Validators.isFalse(!Strings.slowEquals(password, user.getPassword()), 
				ErrorCode.ERROR_USERNAME_OR_PASSWORD);
		UserDetail userDetail = getUserDetail(user.getId());
		CurrentUser currentUser = refreshToCache(user, userDetail, ip);
		AccessToken accessToken = tokenService.token(currentUser);
		
		return accessToken;
	}
	
	public CurrentUser refreshToCache(User user, UserDetail userDetail, String ip) {
		CurrentUser currentUser = new CurrentUser();
		currentUser.setId(user.getId());
		currentUser.setUsername(user.getUsername());
		currentUser.setNickname(user.getNickname());
		currentUser.setGender(userDetail.getGender());
		currentUser.setAvatar(userDetail.getAvatar());
		currentUser.setPhone(user.getPhone());
		currentUser.setCredit(userDetail.getCredit());
		currentUser.setContribution(userDetail.getContribution());
		currentUser.setZibei(userDetail.getZibei());
		currentUser.setRegTime(user.getCreateTime().getTime()/1000);
		currentUser.setIp(ip);
		currentUser.setSurname(user.getSurname());
		currentUser.setName(user.getName());
		currentUser.setCert(user.getCert());
		String key = RedisKey.currentUserKey(currentUser.getId());
		try {
			redisClient.setex(key, JSON.toJSONString(currentUser), EXPIRE_CURRENT_USER);
		} catch(Throwable t) {
			LOG.error("CurrentUser refreshToCache Error.{}", user.getId());
		}
		return currentUser;
	}
	
	public CurrentUser refreshToCache(UserDetailDTO user, String ip) {
		CurrentUser currentUser = new CurrentUser();
		currentUser.setId(user.getId());
		currentUser.setUsername(user.getUsername());
		currentUser.setNickname(user.getNickname());
		currentUser.setGender(user.getGender());
		currentUser.setAvatar(user.getAvatar());
		currentUser.setPhone(user.getPhone());
		currentUser.setCredit(user.getCredit());
		currentUser.setContribution(user.getContribution());
		currentUser.setZibei(user.getZibei());
		currentUser.setRegTime(user.getCreateTime().getTime()/1000);
		currentUser.setIp(ip);
		currentUser.setSurname(user.getSurname());
		currentUser.setName(user.getName());
		String key = RedisKey.currentUserKey(currentUser.getId());
		try {
			redisClient.setex(key, JSON.toJSONString(currentUser), EXPIRE_CURRENT_USER);
		} catch(Throwable t) {
			LOG.error("CurrentUser refreshToCache Error.{}", user.getId());
		}
		return currentUser;
	}
	
	public UserDetail getUserDetail(long id) {
		return userDetailService.selectByPrimaryKey(id);
	}
}
