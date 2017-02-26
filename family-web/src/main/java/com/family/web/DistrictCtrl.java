package com.family.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.family.common.service.DistrictService;

import cn.lfy.base.model.District;
import cn.lfy.common.model.Message;

@Controller
public class DistrictCtrl extends BaseController {

	@Autowired
	private DistrictService districtService;
	/**
	 * 地区树
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/district/all")
	@ResponseBody
	public Object all(HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/district/all");
		List<District> list = districtService.all();
		JSONObject data = new JSONObject();
		data.put("list", list);
		builder.data(data);
		return builder.build();
	}
	
	/**
	 * 地区树
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/district/list")
	@ResponseBody
	public Object list(@RequestParam(name = "pid", defaultValue = "0") Long pid,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/district/list");
		List<District> list = districtService.list(pid);
		JSONObject data = new JSONObject();
		data.put("list", list);
		builder.data(data);
		return builder.build();
	}
}
