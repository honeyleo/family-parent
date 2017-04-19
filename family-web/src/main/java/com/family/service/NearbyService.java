package com.family.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.model.CurrentUser;
import com.google.common.collect.Lists;

import cn.lfy.common.cache.RedisClient;
import cn.lfy.common.utils.RedisKey;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.params.geo.GeoRadiusParam;

@Service
public class NearbyService {

	@Autowired
	private RedisClient redisClient;
	
	@Autowired
	private UserProxyService userProxyService;
	
	public List<CurrentUser> list(CurrentUser currentUser, double lng, double lat, int start, int limit) {
		
		List<CurrentUser> list = Lists.newArrayList();
		
		String key = RedisKey.nearbySurnamePeopleKey(currentUser.getSurname());
		redisClient.geoadd(key, lng, lat, String.valueOf(currentUser.getId()));
		
		List<GeoRadiusResponse> geoRadiusResponseList = redisClient.georadius(key, String.valueOf(currentUser.getId()), 200000D,GeoUnit.M, GeoRadiusParam.geoRadiusParam().withDist()); 
		
		if(geoRadiusResponseList == null) {
			return list;
		}
		List<Long> userIdList = Lists.newArrayList();
		for(GeoRadiusResponse geoRadiusResponse : geoRadiusResponseList) {
			String userId = geoRadiusResponse.getMemberByString();
			userIdList.add(Long.parseLong(userId));
		}
		Map<Long, CurrentUser> map = userProxyService.getCurrentUsers(userIdList);
		for(GeoRadiusResponse geoRadiusResponse : geoRadiusResponseList) {
			String userId = geoRadiusResponse.getMemberByString();
			CurrentUser user = map.get(Long.parseLong(userId));
			if(user != null && !userId.equals(String.valueOf(currentUser.getId()))) {
				Double distance = geoRadiusResponse.getDistance();
				user.setDistance(distance.intValue());
				list.add(user);
			}
		}
		return list;
	}
}
