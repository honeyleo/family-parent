package com.family.common.model;

import java.io.Serializable;

public class Comment implements Serializable {

	private static final long serialVersionUID = 4697266494596814763L;

	private Long id;
	
	private String content;
	/**
	 * 1-评论；2-收藏；
	 */
	private Integer type;
	
	private Long newsId;
	
	private Long userId;
	/**
	 * 单位（秒）
	 */
	private Long createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
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
