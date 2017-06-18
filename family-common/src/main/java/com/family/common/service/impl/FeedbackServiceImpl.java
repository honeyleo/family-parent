package com.family.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.common.dao.FeedbackDAO;
import com.family.common.model.Feedback;
import com.family.common.service.FeedbackService;

import cn.lfy.common.page.Page;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDAO dao;
	
	@Override
	public int delete(Long id) {
		return dao.delete(id);
	}
	
	@Override
	public int insert(Feedback record) {
		if(record.getStatus() == null) {
			record.setStatus(0);
		}
		if(record.getImages() == null) {
			record.setImages("");
		}
		int ret = dao.insert(record);
		return ret;
	}
	
	@Override
	public Feedback get(Long id) {
		return dao.get(id);
	}
	
	@Override
	public int update(Feedback record) {
		return dao.update(record);
	}

	@Override
	public Page<Feedback> list(Integer status, int pageIndex, int pageSize) {
		Page<Feedback> page = dao.list(status, pageIndex, pageSize);
		return page;
	}

}
