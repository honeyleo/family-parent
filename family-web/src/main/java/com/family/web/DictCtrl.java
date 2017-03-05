package com.family.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.family.common.service.DictService;

import cn.lfy.base.model.Dict;
import cn.lfy.common.model.Message;
import cn.lfy.common.web.BaseController;

@Controller
public class DictCtrl extends BaseController {

	@Autowired
	private DictService dictService;
	
	/**
	 * type：INDUSTRY-行业；JOB_FIELD-工作领域；
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/dict/{type}/list")
	@ResponseBody
	public Object list(@PathVariable("type") String type, 
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/dict/" + type + "/list");
		List<Dict> list = dictService.list(type);
		JSONObject data = new JSONObject();
		data.put("list", list);
		builder.data(data);
		return builder.build();
	}
}
