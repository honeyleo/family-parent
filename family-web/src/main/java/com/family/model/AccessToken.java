package com.family.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class AccessToken implements Serializable {

	private static final long serialVersionUID = -8837048117497965517L;

	@JSONField(name = "access_token")
	private String accessToken;
	@JSONField(name = "token_type")
	private String tokenType = "bearer";
	@JSONField(name = "refresh_token")
	private String refreshToken;
	@JSONField(name = "expires_in")
	private int expiresIn;
	@JSONField(name = "user_id")
	private long userId;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
