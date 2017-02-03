package com.family.common.model;

import java.io.Serializable;

/**
 * 首页新闻
 * @author honeyleo
 *
 */
public class NewsHome implements Serializable {

	private static final long serialVersionUID = -2176671739863591332L;

	private Long id;
	
	private String title;
	
	private String intro;
	
	private String content;
	
	private Integer type;
	
	private Integer imgShowMode;
	
	private Long userId;
	
	private Long createTime;
	
	private Long updateTime;
	
	private String imgs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public Integer getImgShowMode() {
		return imgShowMode;
	}

	public void setImgShowMode(Integer imgShowMode) {
		this.imgShowMode = imgShowMode;
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

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
}
