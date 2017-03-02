package com.family.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.family.model.CurrentUser;
import com.family.service.NearbyService;
import com.family.web.core.BaseController;

import cn.lfy.common.model.Message;

@Controller
public class NearbyCtrl extends BaseController {

	@Autowired
	private NearbyService nearbyService;
	/**
	 * 附近的人
	 * <p>有效的经度介于 -180 度至 180 度之间。</p>
	 * <p>有效的纬度介于 -85.05112878 度至 85.05112878 度之间</p>
	 * @param user
	 * @param lng
	 * @param lat
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/nearby/list")
	@ResponseBody
	public Object list(CurrentUser currentUser, 
			@RequestParam(name = "lng") double lng,
			@RequestParam(name = "lat") double lat,
			@RequestParam(name = "start") int start,
			@RequestParam(name = "limit") int limit,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/nearby/list");
		List<CurrentUser> list = nearbyService.list(currentUser, lng, lat, start, limit);
		JSONObject data = new JSONObject();
		data.put("list", list);
		data.put("more", false);
		return builder.data(data).build();
	}
}
