package com.family.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.family.common.dao.AppealDAO;
import com.family.common.dao.UserContributionDAO;
import com.family.common.dao.UserDetailDAO;
import com.family.common.model.Appeal;
import com.family.common.model.AppealHelp;
import com.family.common.model.AppealThank;
import com.family.common.model.UserContributionRecord;
import com.family.common.model.UserDetail;
import com.family.common.service.AppealService;

import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.utils.Validators;

@Service
public class AppealServiceImpl implements AppealService {

	@Autowired
	private AppealDAO appealDAO;

	@Autowired
	private UserDetailDAO userDetailDAO;
	
	@Autowired
	private UserContributionDAO userContributionDAO;
	
	public int delete(Long id) {
		return appealDAO.delete(id);
	}

	public int insert(Appeal record) {
		if(record.getAddress() == null) {
			record.setAddress("");
		}
		if(record.getFullAddress() == null) {
			record.setFullAddress("");
		}
		return appealDAO.insert(record);
	}

	public Appeal get(Long id) {
		return appealDAO.get(id);
	}

	public int update(Appeal record) {
		return appealDAO.update(record);
	}

	public List<Appeal> list(long userId, int start, int limit) {
		return appealDAO.list(userId, start, limit);
	}

	@Override
	public List<Appeal> familyAppealList(long userId, int start, int limit) {
		return appealDAO.familyAppealList(userId, start, limit);
	}

    @Override
    public int addHelp(long appealId, long userId) {
    	AppealHelp entity = new AppealHelp();
    	entity.setAppealId(appealId);
    	entity.setUserId(userId);
    	entity.setStatus(1);
    	entity.setContribution(0);
        return appealDAO.addHelp(entity);
    }

    @Override
    @Transactional
    public int thank(long refUserId, long appealId, long[] thankUserIds, int contribution) {
    	Appeal appeal = appealDAO.get(appealId);
    	Validators.isFalse(appeal == null, ErrorCode.VALUE_NOT_EXIST, "求助");
    	Validators.isFalse(refUserId != appeal.getUserId(), ErrorCode.PERMISSION_DENIED);
    	UserDetail userDetail = userDetailDAO.selectByPrimaryKey(refUserId);
    	Validators.isFalse(userDetail == null, ErrorCode.VALUE_NOT_EXIST, "用户");
    	int total = contribution * thankUserIds.length;
    	Validators.isFalse(userDetail.getContribution() < total, ErrorCode.VALUE_NOT_ENOUGH, "贡献值");
        for(long userId : thankUserIds) {
        	AppealThank entity = new AppealThank();
        	entity.setAppealId(appealId);
        	entity.setUserId(userId);
        	entity.setStatus(0);
        	entity.setContribution(contribution);
        	entity.setCreateTime(System.currentTimeMillis() / 1000);
        	int ret = appealDAO.addThank(entity);
        	if(ret > 0) {
        		appealDAO.updateContribution(contribution, appealId, userId);
        	}
        	userDetailDAO.addContribution(userId, contribution);
        	UserContributionRecord record = new UserContributionRecord();
        	record.setUserId(userId);
        	record.setType(4);
        	record.setInOrOutType(1);
        	record.setContribution(contribution);
        	record.setCreateTime(System.currentTimeMillis() / 1000);
        	userContributionDAO.addUserContributionRecord(record);
        }
        userDetailDAO.minusContribution(refUserId, total);
        UserContributionRecord outRecord = new UserContributionRecord();
        outRecord.setUserId(refUserId);
        outRecord.setType(4);
        outRecord.setInOrOutType(2);
        outRecord.setContribution(contribution);
        outRecord.setCreateTime(System.currentTimeMillis() / 1000);
        userContributionDAO.addUserContributionRecord(outRecord);
        return 1;
    }

    @Override
    public List<Long> getThankPeopleList(long appealId) {
        return appealDAO.getThankPeopleList(appealId);
    }

	@Override
	public List<AppealThank> getReceiveThanks(long userId) {
		return appealDAO.getReceiveThanks(userId);
	}

	@Override
	public int updateAppealThankForReaded(Long id) {
		return appealDAO.updateAppealThankForReaded(id);
	}
	
	
}
