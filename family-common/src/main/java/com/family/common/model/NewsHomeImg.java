package com.family.common.model;

import java.io.Serializable;

public class NewsHomeImg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1321173091553139651L;
	
	private Long id;
	
	private String fileName;
	
	private String originalFileName;
	
	private String path;
	
	private Long newsHomeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getNewsHomeId() {
		return newsHomeId;
	}

	public void setNewsHomeId(Long newsHomeId) {
		this.newsHomeId = newsHomeId;
	}
	
	

}
