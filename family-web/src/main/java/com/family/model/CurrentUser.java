package com.family.model;

import java.io.Serializable;

public class CurrentUser implements Serializable {

	private static final long serialVersionUID = 1390406588484721951L;

	private long id;
	
	private String surname;
	
	private String name;
	
	private String username;
	
	private String nickname;
	
	private int gender;
	
	private String avatar;
	
	private String phone;
	
	private int credit;
	
	private int contribution;
	
	private String zibei;
	
	private String ip;
	
	private long regTime;

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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getContribution() {
		return contribution;
	}

	public void setContribution(int contribution) {
		this.contribution = contribution;
	}

	public String getZibei() {
		return zibei;
	}

	public void setZibei(String zibei) {
		this.zibei = zibei;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getRegTime() {
		return regTime;
	}

	public void setRegTime(long regTime) {
		this.regTime = regTime;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
