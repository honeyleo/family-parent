package com.family.common.model;

import java.io.Serializable;

/**
 * 用户贡献记录表
 * @author honeyleo
 *
 */
public class UserContributionRecord implements Serializable {

	private static final long serialVersionUID = -5504151702407900118L;

	private Long id;
	
	private Long userId;
	
	private Integer type;
	
	private Integer inOrOutType;
	
	private Integer contribution;
	
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

	public Integer getInOrOutType() {
		return inOrOutType;
	}

	public void setInOrOutType(Integer inOrOutType) {
		this.inOrOutType = inOrOutType;
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
