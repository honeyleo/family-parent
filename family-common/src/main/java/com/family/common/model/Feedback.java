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
	/**
	 * 反馈状态：0-未处理；1-已处理；-1-已删除
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
