package com.family.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.common.model.UserContributionRecord;

public interface UserContributionDAO {

	/**
	 * 添加用户贡献值收支记录
	 * @param entity
	 * @return
	 */
	int addUserContributionRecord(UserContributionRecord entity);
	/**
	 * 获取用户贡献值记录列表
	 * @param userId
	 * @param inOrOutType 1-收入；2-支出；0-全部；
	 * @return
	 */
	List<UserContributionRecord> getUserContributionRecordList(@Param("userId") Long userId, @Param("inOrOutType") Integer inOrOutType);
	/**
	 * 获取用户贡献值记录
	 * @param userId 
	 * @param type 1-创建家谱；2-首页文章；3-资讯文章；4-求助消息；5-家族基金；6-用户推荐；
	 * @param inOrOutType 1-收入；2-支出
	 * @return
	 */
	List<UserContributionRecord> getUserContributionRecord(@Param("userId")Long userId, @Param("type") Integer type, @Param("inOrOutType") Integer inOrOutType);
	
}
