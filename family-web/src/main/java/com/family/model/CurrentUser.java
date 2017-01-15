package com.family.model;

import java.io.Serializable;

public class CurrentUser implements Serializable {

	private static final long serialVersionUID = 1390406588484721951L;

	private long id;
	
	private String username;
	
	private String nickname;
	
	private String phone;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
