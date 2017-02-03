package com.family.common.dao;

import com.family.common.model.NewsHomeImg;

public interface NewsHomeImgDAO {

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(NewsHomeImg record);

    /**
     * 查询记录
     */
    NewsHomeImg getImgsByNewsHomeId(Long newsHomeId);

}