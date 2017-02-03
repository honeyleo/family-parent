package com.family.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GuideController {

	@RequestMapping("/guide")
	@ResponseBody
	public Object guide() {
		return null;
	}
}
