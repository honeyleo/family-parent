package com.family.common.model;

import java.io.Serializable;

/**
 * 反馈实体类
 * @author honeyleo
 *
 */
public class Feedback implements Serializable {

	private static final long serialVersionUID = -8291860458193480541L;

	private Long id;
	
	private String description;
	
	private String images;
	
	private Long userId;
	
	private Long createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
}
