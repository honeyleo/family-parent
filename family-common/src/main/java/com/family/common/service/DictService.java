package com.family.common.service;

import java.util.List;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.Dict;

public interface DictService {
    /**
     * 根据条件查询记录总数
     */
    int countByCriteria(Criteria criteria);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Dict record);

    /**
     * 根据条件查询记录集
     */
    List<Dict> getByCriteria(Criteria criteria);
    
    /**
     * 根据主键查询记录
     */
    Dict getById(Long id);
    
    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByIdSelective(Dict record);
    
    /**
     * 根据主键删除记录
     */
    int delete(Long id);

}