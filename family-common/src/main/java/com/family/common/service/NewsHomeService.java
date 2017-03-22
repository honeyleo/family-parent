package com.family.common.service;

import java.util.List;

import com.family.common.enums.NewsType;
import com.family.common.model.NewsHome;
import com.family.common.model.Surname;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.PageInfo;

public interface NewsHomeService {
    /**
     * 根据条件查询记录总数
     */
    int countByCriteria(Criteria criteria);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(NewsHome record);

    /**
     * 根据条件查询记录集
     */
    List<NewsHome> getByCriteria(Criteria criteria);
    
    /**
     * 根据主键查询记录
     */
    NewsHome getById(Long id);
    
    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByIdSelective(NewsHome record);
    
    /**
     * 根据主键删除记录
     */
    int delete(Long id);
    
    /**
     * 根据条件查询记录集
     */
    PageInfo<NewsHome> findListByCriteria(Criteria criteria, int pageNo, int pageSize);
    /**
     * 首頁新聞列表
     * @param newsType
     * @param type
     * @param start
     * @param limit
     * @return
     */
    List<NewsHome> list(String surname, NewsType newsType, int type, int start, int limit);
    /**
     * 最新新聞數量
     * @param type
     * @param lastUpdateTime
     * @return
     */
    int getNewestCount(int type, long lastUpdateTime);
    /**
     * 评论数加1
     * @param id
     * @return
     */
    int incrComments(long id);
    
    /**
     * 查询匹配的姓氏
     * @param surname
     * @return
     */
    List<Surname> getSurnameBySurname(String surname);

}