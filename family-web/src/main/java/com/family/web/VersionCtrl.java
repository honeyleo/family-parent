package com.family.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.common.model.Version;
import com.family.common.service.VersionService;
import com.family.model.CurrentUser;

import cn.lfy.common.model.Message;

@Controller
public class VersionCtrl {

	private static final Logger LOG = LoggerFactory.getLogger(VersionCtrl.class);
	
	@Autowired
	private VersionService versionService;
	
	@RequestMapping("/version/check_update")
	@ResponseBody
	public Object publish(CurrentUser user, 
			@RequestParam(name = "channel", defaultValue = "0") String channel,
			@RequestParam(name = "type") String type,
			@RequestParam(name = "versionCode") int versionCode,
			@RequestParam(name = "versionName", defaultValue = "1.0") String versionName,
			HttpServletRequest request) {
		LOG.info("version_check_update_{}_{}_{}_{}", new Object[]{type, versionName, versionCode, channel});
		Message.Builder builder = Message.newBuilder("/version/check_update");
		Version version = versionService.getLatestVersion(type, versionCode);
		if(version != null) {
			builder.putData("hasUpdate", true);
			builder.putData("update", version);
		} else {
			builder.putData("hasUpdate", false);
		}
		return builder.build();
	}
}
