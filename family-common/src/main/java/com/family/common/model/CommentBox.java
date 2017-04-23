package com.family.common.model;

import java.io.Serializable;

/**
 * 评论的点赞、收藏容器
 * @author honeyleo
 *
 */
public class CommentBox implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3838750512802645317L;

	private Long id;
	
	private Long userId;
	/**
	 * 1-点赞；2-收藏；
	 */
	private Integer type;
	
	private Long commentId;
	
	private Long createTime;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
}
