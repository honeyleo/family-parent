package com.family.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.common.model.GuidePage;
import com.family.common.service.GuidePageService;
import com.google.common.collect.Lists;

import cn.lfy.common.model.Message;

@Controller
public class GuideController {

	@Value("${fileserver.image.url}")
	private String imageUrl;
	
	@Autowired
	private GuidePageService guidePageService;
	
	@RequestMapping("/guide")
	@ResponseBody
	public Object guide() {
		Message.Builder builder = Message.newBuilder("/guide");
		List<GuidePage> list = guidePageService.getAvariableGuidePage();
		List<String> imgs = Lists.newArrayList();
		for(GuidePage guide : list) {
			imgs.add(imageUrl + guide.getImg());
		}
		builder.put("list", imgs);
		return builder.build();
	}
}
