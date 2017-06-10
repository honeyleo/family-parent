package com.family.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.common.dao.UserContributionDAO;
import com.family.common.model.UserContributionRecord;
import com.family.common.service.UserContributionService;

@Service
public class UserContributionServiceImpl implements UserContributionService {

	@Autowired
	private UserContributionDAO userContributionDAO;
	
	@Override
	public List<UserContributionRecord> getUserContributionRecordList(
			long userId, int inOrOutType) {
		return userContributionDAO.getUserContributionRecordList(userId, inOrOutType);
	}

}
