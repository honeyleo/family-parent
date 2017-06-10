package com.family.common.service;

import java.util.List;

import com.family.common.model.Appeal;
import com.family.common.model.AppealThank;

/**
 * 求助消息服务接口
 * @author Leo.liao
 *
 */
public interface AppealService {

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
     * 新聞评论列表
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    List<Appeal> list(long userId, int start, int limit);
    
    /**
     * 求助消息列表列表
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    List<Appeal> familyAppealList(long userId, int start, int limit);
    /**
     * 用户对指定求助进行帮助
     * @param appealId
     * @param userId
     * @return
     */
    int addHelp(long appealId, long userId);
    /**
     * 答谢指定求助的帮助人
     * @param refUserId
     * @param appealId
     * @param thankUserIds
     * @param contribution
     * @return
     */
    int thank(long refUserId, long appealId, long[] thankUserIds, int contribution);
    /**
     * 获取答谢人列表
     * @param appealId
     * @return
     */
    List<Long> getThankPeopleList(long appealId);
    
    /**
     * 获取接收到的答谢
     * @param userId
     * @return
     */
    List<AppealThank> getReceiveThanks(long userId);
    /**
     * 更新为已读
     * @param id
     * @return
     */
    int updateAppealThankForReaded(Long id);
    
}