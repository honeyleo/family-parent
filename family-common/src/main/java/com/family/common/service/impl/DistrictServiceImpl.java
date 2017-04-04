package com.family.common.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.family.common.dao.DistrictDAO;
import com.family.common.service.DistrictService;

import cn.lfy.base.model.District;
import cn.lfy.common.cache.RedisClient;
import cn.lfy.common.utils.RedisKey;

@Service
public class DistrictServiceImpl implements DistrictService, InitializingBean {

	@Autowired
	private DistrictDAO districtDAO;

	@Autowired
	private RedisClient redisClient;
	
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

	@Override
	public void afterPropertiesSet() throws Exception {
		List<District> list = districtDAO.all();
		String[] keysvalues = new String[list.size()*2];
		int i = 0;
		for(District district : list) {
			String key = RedisKey.districtKey(district.getId());
			String value = JSON.toJSONString(district);
			keysvalues[i++] = key;
			keysvalues[i++] = value;
		}
		redisClient.mset(keysvalues);
	}
	
	public District getByCache(Long id) {
		if(id == null) {
			return null;
		}
		String key = RedisKey.districtKey(id);
		String value = redisClient.get(key);
		District district = null;
		if(StringUtils.isNotBlank(value)) {
			district = JSON.parseObject(value, District.class);
		}
		if(district == null) {
			district = get(id);
		}
		return district;
	}
}
