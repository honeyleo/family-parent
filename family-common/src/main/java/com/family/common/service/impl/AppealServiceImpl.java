package com.family.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.common.dao.AppealDAO;
import com.family.common.model.Appeal;
import com.family.common.service.AppealService;

@Service
public class AppealServiceImpl implements AppealService {

	@Autowired
	private AppealDAO appealDAO;

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
}
