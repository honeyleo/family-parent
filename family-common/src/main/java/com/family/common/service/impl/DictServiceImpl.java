package com.family.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.common.dao.DictDAO;
import com.family.common.service.DictService;

import cn.lfy.base.model.Criteria;
import cn.lfy.base.model.Dict;

@Service
public class DictServiceImpl implements DictService {

	@Autowired
	private DictDAO dictDAO;
	
	@Override
	public int countByCriteria(Criteria criteria) {
		return dictDAO.countByExample(criteria);
	}

	@Override
	public int insert(Dict record) {
		return dictDAO.insert(record);
	}

	@Override
	public List<Dict> getByCriteria(Criteria criteria) {
		return dictDAO.selectByExample(criteria);
	}

	@Override
	public Dict getById(Long id) {
		return dictDAO.selectByPrimaryKey(id);
	}

	@Override
	public int updateByIdSelective(Dict record) {
		record.setUpdateTime(System.currentTimeMillis()/1000);
		return dictDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Long id) {
		return dictDAO.deleteByPrimaryKey(id);
	}

}
