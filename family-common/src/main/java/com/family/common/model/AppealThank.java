package com.family.common.model;

import java.io.Serializable;

public class AppealThank implements Serializable {

	private static final long serialVersionUID = -3442627616094637369L;

	private Long id;
    
    private Long appealId;
    
    private Long userId;
    /**
     * 0-未读答谢；1-已读；
     */
    private Integer status;
    /**
     * 获得贡献值
     */
    private Integer contribution;
    
    private Long createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAppealId() {
		return appealId;
	}

	public void setAppealId(Long appealId) {
		this.appealId = appealId;
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
