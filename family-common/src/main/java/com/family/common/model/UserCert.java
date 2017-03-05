package com.family.common.model;

import java.io.Serializable;

public class UserCert implements Serializable {

	private static final long serialVersionUID = 226963957237184278L;

	private Long id;
	
	private Long userId;
	
	private String name;
	
	/**
	 * 身份证号
	 */
	private String idCardNo;
	
	private String frontImg;
	
	private String backImg;
	
	private String handImg;
	/**
	 * 认证状态：0-初始；1-审核通过；2-审核不通过
	 */
	private Integer status;
	
	private Long createTime;
	
	private Long updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getFrontImg() {
		return frontImg;
	}

	public void setFrontImg(String frontImg) {
		this.frontImg = frontImg;
	}

	public String getBackImg() {
		return backImg;
	}

	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}

	public String getHandImg() {
		return handImg;
	}

	public void setHandImg(String handImg) {
		this.handImg = handImg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
}
