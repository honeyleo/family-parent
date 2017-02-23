package com.family.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lfy.base.model.Criteria;

import com.family.common.model.NewsHome;

public interface NewsHomeDAO {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(NewsHome record);

    /**
     * 根据条件查询记录集
     */
    List<NewsHome> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    NewsHome selectByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(NewsHome record);
    /**
     * 首頁新聞列表
     * @param type
     * @param start
     * @param limit
     * @return
     */
    List<NewsHome> list(@Param("type")int type, @Param("start")int start, @Param("limt")int limit);
    /**
     * 最新新聞數量
     * @param type
     * @param lastUpdateTime
     * @return
     */
    int getNewestCount(@Param("type")int type, @Param("lastUpdateTime")long lastUpdateTime);
    /**
     * 评论数加1
     * @param id
     * @return
     */
    int incrComments(long id);
}