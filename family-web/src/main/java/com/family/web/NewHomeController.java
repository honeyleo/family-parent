package com.family.web;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.family.common.model.NewsHome;
import com.family.common.service.NewsHomeService;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import cn.lfy.common.model.Message;

@Controller
public class NewHomeController {

	@Autowired
	private NewsHomeService newsHomeService;
	
	@RequestMapping("/app/news_home/list")
	@ResponseBody
	public Object list(@RequestParam(name = "type", defaultValue = "1") int type, 
			@RequestParam(name = "start") int start, 
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "last_update_time", defaultValue = "0") long lastUpdateTime,
			HttpServletRequest request) {
		List<NewsHome> list = newsHomeService.list(type, start, limit + 1);
		int count = 0;
		if(start == 0 && lastUpdateTime > 0) {
			count = newsHomeService.getNewestCount(type, lastUpdateTime);
		}
		boolean more = isMore(list, limit);
		if(more) {
			list = list.subList(0, limit);
		}
		List<JSONObject> jsonList = Lists.newArrayList();
		for(NewsHome newsHome : list) {
			JSONObject dto = new JSONObject();
			dto.put("id", newsHome.getId());
			dto.put("title", newsHome.getTitle());
			dto.put("intro", newsHome.getIntro());
			dto.put("type", newsHome.getType());
			dto.put("imgShowMode", newsHome.getImgShowMode());
			dto.put("createTime", newsHome.getCreateTime());
			List<String> imgs = Lists.newArrayList();
			if(StringUtils.isNotBlank(newsHome.getImgs())) {
				Iterable<String> itb = Splitter.on(",").split(newsHome.getImgs());
				Iterator<String> it = itb.iterator();
				while(it.hasNext()) {
					String img = it.next();
					if(StringUtils.isNotBlank(img)) {
						imgs.add(img);
					}
				}
			}
			dto.put("imgs", imgs);
			dto.put("detail_url", "");
			jsonList.add(dto);
		}
		Message.Builder builder = Message.newBuilder("/app/news_home/list");
		JSONObject data = new JSONObject();
		data.put("more", more);
		data.put("newestCount", count);
		data.put("data", jsonList);
		builder.data(data);
		return builder.build();
	}
	
	protected boolean isMore(List<?> list, int limit ) {
		boolean more = false;
		if(list != null) {
			more = list.size() > limit;
		} else {
			more = false;
		}
		return more;
	}
}
