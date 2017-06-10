package com.family.common.model;

import java.io.Serializable;

/**
 * 求助的帮助实体类
 * @author honeyleo
 *
 */
public class AppealHelp implements Serializable {

    private static final long serialVersionUID = -5847909935039284687L;

    private Long id;
    
    private Long appealId;
    
    private Long userId;
    /**
     * -1-删除；0-初始；1-正常；
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
