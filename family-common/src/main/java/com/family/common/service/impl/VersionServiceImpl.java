package com.family.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.common.dao.VersionDAO;
import com.family.common.model.Version;
import com.family.common.service.VersionService;

import cn.lfy.common.page.Page;

@Service
public class VersionServiceImpl implements VersionService {

	@Autowired
	private VersionDAO versionDAO;
	
	@Override
	public int delete(Long id) {
		return versionDAO.delete(id);
	}
	
	@Override
	public int insert(Version record) {
		if(record.getDigest() == null) {
			record.setDigest("");
		}
		if(record.getSize() == null) {
			record.setSize(0);
		}
		int ret = versionDAO.insert(record);
		return ret;
	}
	
	@Override
	public Version get(Long id) {
		return versionDAO.get(id);
	}
	
	@Override
	public int update(Version record) {
		return versionDAO.update(record);
	}

	@Override
	public Page<Version> list(int pageIndex, int pageSize) {
		Page<Version> page = versionDAO.list(pageIndex, pageSize);
		return page;
	}

	@Override
	public Version getLatestVersion(String type, Integer versionCode) {
		return versionDAO.getLatestVersion(type, versionCode);
	}

}
