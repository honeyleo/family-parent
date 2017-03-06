package com.family.common.model;

import java.io.Serializable;

public class GuidePage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5892180210897673856L;
	
	private Long id;
	
	private String img;
	
	private String url;
	/**
	 * 0-已下架；1-待发布；2-已发布
	 */
	private Integer state;
	
	private Long createTime;

	private Long updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
