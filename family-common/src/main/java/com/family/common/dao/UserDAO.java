package com.family.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.User;

public interface UserDAO {
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
    int insert(User record);

    /**
     * 根据条件查询记录集
     */
    List<User> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    User selectByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(User record);
    
    /**
     * 根据登录名查询，username唯一
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 更新为已认证
     * @param id
     * @return
     */
    int updateCerted(Long id);
    /**
     * 通过手机号或者姓名搜索
     * @param query
     * @return
     */
    List<User> search(@Param("query")String query);
}