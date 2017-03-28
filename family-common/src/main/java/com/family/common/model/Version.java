package com.family.common.model;

import java.io.Serializable;

public class Version implements Serializable {

	private static final long serialVersionUID = 1254584040784379783L;

	private Long id;
	/**
	 * ANDROD、IOS
	 */
	private String type;
	private String title;
	private String description;
	private String versionName;
	private Long versionCode;
	private Integer size;
	private String digest;
	private String url;
	private Integer state;
	private Long createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public Long getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(Long versionCode) {
		this.versionCode = versionCode;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
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
}
