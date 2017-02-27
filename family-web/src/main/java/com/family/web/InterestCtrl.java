package com.family.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.family.common.model.Interest;
import com.family.common.service.InterestService;
import com.family.model.CurrentUser;

import cn.lfy.common.model.Message;

@Controller
public class InterestCtrl extends BaseController {

	@Autowired
	private InterestService InterestService;
	/**
	 * 树
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/interest/all")
	@ResponseBody
	public Object all(HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/interest/all");
		List<Interest> list = InterestService.all();
		JSONObject data = new JSONObject();
		data.put("list", list);
		builder.data(data);
		return builder.build();
	}
	
	/**
	 * 列表
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/interest/list")
	@ResponseBody
	public Object list(@RequestParam(name = "pid", defaultValue = "0") Long pid,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/interest/list");
		List<Interest> list = InterestService.list(pid);
		JSONObject data = new JSONObject();
		data.put("list", list);
		builder.data(data);
		return builder.build();
	}
	
	/**
	 * 列表
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/user_interest/update")
	@ResponseBody
	public Object userInterestUpdate(CurrentUser user,
			Long[] interestId,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/user_interest/update");
		InterestService.updateUserInterest(user.getId(), interestId);
		return builder.build();
	}
}
