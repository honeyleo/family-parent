package com.family.service;

import org.springframework.stereotype.Service;

import com.family.model.AccessToken;
import com.family.model.CurrentUser;

import cn.lfy.common.framework.exception.ApplicationException;

@Service
public class UserProxyService {

	
	/**
	 * 根据当前登录用户生成token
	 * @param currentUser
	 * @return
	 */
	public AccessToken token(CurrentUser currentUser) {
		return null;
	}
	/**
	 * 验证access_token
	 * @param accessToken
	 */
	public void verifyAccessToken(String accessToken) throws ApplicationException {
		
	}
	/**
	 * 通过refresh_token获取access_token
	 * @param refreshToken
	 * @return
	 */
	public AccessToken getAccessTokenByRefreshToken(String refreshToken) {
		return null;
	}
}
