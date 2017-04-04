package com.family.model;

import com.alibaba.fastjson.JSON;
import com.google.common.io.BaseEncoding;

public class TokenValue {
	/**
	 * 用户UID
	 */
	private long u;
	/**
	 * 登录序列
	 */
	private String l;
	/**
	 * 随机序列
	 */
	private String r;
	/**
	 * 用户请求IP
	 */
	private String i;
	/**
	 * 生成时间戳（秒）
	 */
	private long t;
	public long getU() {
		return u;
	}
	public void setU(long u) {
		this.u = u;
	}
	public String getL() {
		return l;
	}
	public void setL(String l) {
		this.l = l;
	}
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public String getI() {
		return i;
	}
	public void setI(String i) {
		this.i = i;
	}
	public long getT() {
		return t;
	}
	public void setT(long t) {
		this.t = t;
	}
	public String value() {
		String value = BaseEncoding.base64().encode(JSON.toJSONBytes(this));
		return value;
	}
	
	public static TokenValue valueOf(String token) {
		String json = new String(BaseEncoding.base64().decode(token));
		try {
			return JSON.parseObject(json, TokenValue.class);
		} catch(Throwable t) {
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			String token = new String(BaseEncoding.base64().decode("eyJpIjoiMjIzLjczLjIwNi4yNTIiLCJsIjoiNmY0OTIyZjQ1NTY4MTYxYThjZGY0YWQyMjk5ZjZkMjMiLCJyIjoiYWY2ODU5ZTM5OTIxNDVmYThlNTgzN2U2NmFjMzg2YjIiLCJ0IjoxNDkxMTUwOTUxLCJ1IjoxOH0="));
			System.out.println(token);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
