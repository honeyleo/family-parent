package com.family.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.Appeal;
import com.family.common.model.AppealHelp;
import com.family.common.model.AppealThank;

public interface AppealDAO {

    /**
     * 根据主键删除记录
     */
    int delete(Long id);

    /**
     * 保存记录
     */
    int insert(Appeal record);

    /**
     * 根据主键查询记录
     */
    Appeal get(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int update(Appeal record);
    /**
     * 用户求助消息列表列表
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    List<Appeal> list(@Param("userId")long userId, @Param("start")int start, @Param("limit")int limit);
    /**
     * 求助消息列表列表
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    List<Appeal> familyAppealList(@Param("userId")long userId, @Param("start")int start, @Param("limit")int limit);
    /**
     * 添加帮助
     * @param entity
     * @return
     */
    int addHelp(AppealHelp entity);
    /**
     * 获取答谢人列表
     * @param appealId
     * @return
     */
    List<Long> getThankPeopleList(Long appealId);
    /**
     * 更新贡献值
     * @param contribution
     * @param appealId
     * @param userId
     * @return
     */
    int updateContribution(@Param("contribution")Integer contribution, @Param("appealId")Long appealId, @Param("userId")Long userId);
    /**
     * 添加答谢
     * @param entity
     * @return
     */
    int addThank(AppealThank entity);
    /**
     * 获取用户接收到的答谢
     * @param userId
     * @return
     */
    List<AppealThank> getReceiveThanks(Long userId);
    /**
     * 更新感谢为已读
     * @param id
     * @return
     */
    int updateAppealThankForReaded(Long id);
}