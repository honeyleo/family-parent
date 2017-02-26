package com.family.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.common.dao.DistrictDAO;
import com.family.common.service.DistrictService;

import cn.lfy.base.model.District;

@Service
public class DistrictServiceImpl implements DistrictService{

	@Autowired
	private DistrictDAO districtDAO;

	public int delete(Long id) {
		return districtDAO.delete(id);
	}

	public int insert(District record) {
		return districtDAO.insert(record);
	}

	public District get(Long id) {
		return districtDAO.get(id);
	}

	public int update(District record) {
		return districtDAO.update(record);
	}

	public List<District> all() {
		return districtDAO.all();
	}

	@Override
	public List<District> list(Long pid) {
		return districtDAO.list(pid);
	}
}
