package com.family.common.service;

import java.util.List;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.PageInfo;
import cn.lfy.base.model.User;

public interface UserService {
    /**
     * 根据条件查询记录总数
     */
    int countByCriteria(Criteria criteria);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    Long add(User record);

    /**
     * 根据条件查询记录集
     */
    List<User> findListByCriteria(Criteria criteria);
    
    /**
     * 根据条件查询记录集
     */
    PageInfo<User> findListByCriteria(Criteria criteria, int pageNo, int pageSize);

    /**
     * 根据主键查询记录
     */
    User findById(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByIdSelective(User record);
    
    /**
     * 更加登录名查询
     * @param username
     * @return
     */
    User findByUsername(String username);
    
    /**
     * 通过手机号或者姓名搜索
     * @param query
     * @return
     */
    List<User> search(String query);
    
    /**
     * 获取今天注册数
     * @return
     */
    Long getTodayRegisterCount();
    /**
     * 获取总注册数
     * @return
     */
    Long getTotalRegisterCount();
    
}