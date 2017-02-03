package com.family.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.family.model.AccessToken;
import com.family.model.CurrentUser;
import com.family.model.TokenValue;

import cn.lfy.common.cache.RedisClient;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.utils.RedisKey;
import cn.lfy.common.utils.UUIDUtil;
import cn.lfy.common.utils.Validators;

@Service
public class TokenService {

	public static final int TOKEN_EXPIRES_IN = 7*24*60*60;
	
	@Autowired
	private RedisClient redisClient;
	
	@Autowired
	private UserProxyService userProxyService;
	
	/**
	 * 根据当前登录用户生成token
	 * @param currentUser
	 * @return
	 */
	public AccessToken token(CurrentUser currentUser) {
		
		TokenValue tokenValue = new TokenValue();
		tokenValue.setU(currentUser.getId());
		tokenValue.setI(currentUser.getIp());
		tokenValue.setR(UUIDUtil.uuid());
		tokenValue.setT(System.currentTimeMillis()/1000);
		tokenValue.setL(UUIDUtil.uuid());
		
		String token = tokenValue.value();
		
		String status = redisClient.setex(RedisKey.tokenKey(token), String.valueOf(currentUser.getId()), TOKEN_EXPIRES_IN);
		
		Validators.isFalse(!"OK".equalsIgnoreCase(status), ErrorCode.ERROR);
		
		String status2 = redisClient.setex(RedisKey.currentUserKey(currentUser.getId()), JSON.toJSONString(currentUser), 7*24*60*60);
		if(!"OK".equalsIgnoreCase(status2)) {
			redisClient.del(token);
			throw ApplicationException.newInstance(ErrorCode.ERROR);
		}
		AccessToken accessToken = new AccessToken();
		accessToken.setAccessToken(token);
		accessToken.setExpiresIn(TOKEN_EXPIRES_IN - 1);
		accessToken.setUserId(currentUser.getId());
		return accessToken;
	}
	/**
	 * 验证access_token
	 * @param accessToken
	 */
	public void verifyAccessToken(String accessToken) throws ApplicationException {
		String uidString = redisClient.get(RedisKey.tokenKey(accessToken));
		Validators.notEmptyAndNumeric(uidString, ErrorCode.ACCESS_TOKEN_INVALID);
	}
	
	/**
	 * 验证access_token
	 * @param accessToken
	 */
	public CurrentUser getUser(String accessToken) throws ApplicationException {
		String uidString = redisClient.get(RedisKey.tokenKey(accessToken));
		Validators.notEmptyAndNumeric(uidString, ErrorCode.ACCESS_TOKEN_INVALID);
		CurrentUser currentUser = userProxyService.getCurrentUser(Long.parseLong(uidString));
		return currentUser;
	}
	/**
	 * 刷新access_token
	 * @param accessToken
	 * @return
	 */
	public AccessToken refreshToken(String accessToken) {
		String uidString = redisClient.get(RedisKey.tokenKey(accessToken));
		Validators.notEmptyAndNumeric(uidString, ErrorCode.ACCESS_TOKEN_INVALID);
		TokenValue tokenValue = TokenValue.valueOf(accessToken);
		Validators.notNull(tokenValue, ErrorCode.ACCESS_TOKEN_INVALID);
		AccessToken token = null;
		if(System.currentTimeMillis()/1000 - tokenValue.getT() < 24*60*60) {
			CurrentUser currentUser = userProxyService.getCurrentUser(Long.parseLong(uidString));
			token = token(currentUser);
		} else {
			token = new AccessToken();
			token.setAccessToken(accessToken);
			int t = (int)(System.currentTimeMillis()/1000 - tokenValue.getT());
			token.setExpiresIn(TOKEN_EXPIRES_IN - 1 - t);
			token.setUserId(Long.parseLong(uidString));
		}
		return token;
	}
}
