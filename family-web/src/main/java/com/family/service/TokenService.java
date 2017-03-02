package com.family.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.model.AccessToken;
import com.family.model.CurrentUser;
import com.family.model.TokenValue;

import cn.lfy.common.cache.RedisClient;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.utils.MessageDigestUtil;
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
		tokenValue.setL(MessageDigestUtil.getMD5(String.valueOf(currentUser.getId())));
		
		String token = tokenValue.value();
		String key = RedisKey.tokenKey(currentUser.getId());
		
		Long status = redisClient.hset(key, tokenValue.getL(), token);
		Validators.isFalse(status == null, ErrorCode.ERROR);
		redisClient.expire(key, TOKEN_EXPIRES_IN);
		
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
	public CurrentUser verifyAccessToken(String accessToken) throws ApplicationException {
		TokenValue tokenValue = verify(accessToken);
		CurrentUser currentUser = userProxyService.getCurrentUser(tokenValue.getU());
		return currentUser;
	}
	
	private TokenValue verify(String accessToken) {
		TokenValue tokenValue = TokenValue.valueOf(accessToken);
		String key = RedisKey.tokenKey(tokenValue.getU());
		Validators.notNull(tokenValue, ErrorCode.ACCESS_TOKEN_INVALID);
		String redisAccessToken = redisClient.hget(RedisKey.tokenKey(tokenValue.getU()), tokenValue.getL());
		Validators.notNull(redisAccessToken, ErrorCode.ACCESS_TOKEN_INVALID);
		Validators.isFalse(!redisAccessToken.equals(accessToken), ErrorCode.ACCESS_TOKEN_INVALID);
		
		long time = System.currentTimeMillis()/1000;
		if(time - tokenValue.getT() > TOKEN_EXPIRES_IN) {
			redisClient.hdel(key, tokenValue.getL());
		}
		Validators.isFalse(time - tokenValue.getT() > TOKEN_EXPIRES_IN, ErrorCode.ACCESS_TOKEN_INVALID);
		
		return tokenValue;
	}
	
	/**
	 * 刷新access_token
	 * @param accessToken
	 * @return
	 */
	public AccessToken refreshToken(String accessToken) {
		TokenValue tokenValue = verify(accessToken);
		AccessToken token = null;
		if(System.currentTimeMillis()/1000 - tokenValue.getT() < 24*60*60) {
			token = new AccessToken();
			token.setAccessToken(accessToken);
			int t = (int)(System.currentTimeMillis()/1000 - tokenValue.getT());
			token.setExpiresIn(TOKEN_EXPIRES_IN - 1 - t);
			token.setUserId(tokenValue.getU());
		} else {
			CurrentUser currentUser = userProxyService.getCurrentUser(tokenValue.getU());
			token = token(currentUser);
		}
		return token;
	}
}
