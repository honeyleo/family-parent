package com.family.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.common.dao.NewsHomeImgDAO;
import com.family.common.model.NewsHomeImg;
import com.family.common.service.NewsHomeImgService;

@Service
public class NewsHomeImgServiceImpl implements NewsHomeImgService {

	@Autowired
	private NewsHomeImgDAO newsHomeImgDAO;
	
	@Override
	public int insert(NewsHomeImg record) {
		return newsHomeImgDAO.insert(record);
	}

	@Override
	public NewsHomeImg getImgsByNewsHomeId(Long newsHomeId) {
		return newsHomeImgDAO.getImgsByNewsHomeId(newsHomeId);
	}

	@Override
	public int delete(Long id) {
		return newsHomeImgDAO.deleteByPrimaryKey(id);
	}

}
