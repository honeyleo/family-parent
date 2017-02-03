package com.family.common.service;

import com.family.common.model.NewsHomeImg;

public interface NewsHomeImgService {

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(NewsHomeImg record);

    /**
     * 根据newsHomeId查询记录
     */
    NewsHomeImg getImgsByNewsHomeId(Long newsHomeId);
    
    /**
     * 根据主键删除记录
     */
    int delete(Long id);
    
}