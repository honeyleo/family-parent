package com.family.common.model;

public class AppealThankDTO {

	private Long id;
	
	private String title;
	
	private String label;
	
	private String content;
	
	private Integer contribution;
	
	private Long createTime;

	public AppealThankDTO(Long id, String title, String label, String content,
			Integer contribution, Long createTime) {
		super();
		this.id = id;
		this.title = title;
		this.label = label;
		this.content = content;
		this.contribution = contribution;
		this.createTime = createTime;
	}

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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getContribution() {
		return contribution;
	}

	public void setContribution(Integer contribution) {
		this.contribution = contribution;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	
}
