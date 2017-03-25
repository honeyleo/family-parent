package com.family.common.model;

import java.io.Serializable;
import java.util.List;
/**
 * 用户新闻收藏
 * @author honeyleo
 *
 */
public class UserNewsFavor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long newsId;
	
	private String newsType;
	
	private Integer type;
	
	private String title;
	
	private String intro;
	
	private List<String> imgs;
	
	private Integer imgShowMode;
	
	private Long createTime;
	
	private Long newsCreateTime;
	
	/**
	 * 评论数
	 */
	private Integer comments;
	
	private String detail_url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	public Integer getImgShowMode() {
		return imgShowMode;
	}

	public void setImgShowMode(Integer imgShowMode) {
		this.imgShowMode = imgShowMode;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getNewsCreateTime() {
		return newsCreateTime;
	}

	public void setNewsCreateTime(Long newsCreateTime) {
		this.newsCreateTime = newsCreateTime;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	
	
}
