package com.family.common.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.family.common.dao.InterestDAO;
import com.family.common.model.Interest;
import com.family.common.model.UserInterest;
import com.family.common.service.InterestService;
import com.google.common.collect.Sets;

@Service
public class InterestServiceImpl implements InterestService {

	@Autowired
	private InterestDAO interestDAO;

	public int delete(Long id) {
		return interestDAO.delete(id);
	}

	public int insert(Interest record) {
		return interestDAO.insert(record);
	}

	public Interest get(Long id) {
		return interestDAO.get(id);
	}

	public int update(Interest record) {
		return interestDAO.update(record);
	}

	public List<Interest> all() {
		return interestDAO.all();
	}

	public List<Interest> list(Long pid) {
		return interestDAO.list(pid);
	}
	@Transactional
	public int updateUserInterest(Long userId, Long[] interestId) {
		if(interestId != null && interestId.length > 0) {
			List<UserInterest> list = interestDAO.getUserInterests(userId);
			Set<Long> old = Sets.newHashSet();
			for(UserInterest ui : list) {
				old.add(ui.getInterestId());
			}
			Set<Long> now = Sets.newHashSet(interestId);
			Set<Long> delSet = Sets.difference(old, now);
			Set<Long> newSet = Sets.difference(now, old);
			if(!newSet.isEmpty()) {
				interestDAO.addUserInterests(userId, newSet);
			}
			if(!delSet.isEmpty()) {
				interestDAO.deleteUserInterests(userId, delSet);
			}
			return 1;
		}
		return 0;
	}

	@Override
	public List<Interest> getUserInterests(Long userId) {
		return interestDAO.getUserInterestList(userId);
	}
}
