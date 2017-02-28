package com.family.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.family.common.model.Appeal;
import com.family.common.service.AppealService;
import com.family.model.CurrentUser;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import cn.lfy.common.filehandler.ResourceIdentifier;
import cn.lfy.common.filehandler.ResourceManager;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.model.Message;
import cn.lfy.common.utils.PathFormat;

@Controller
public class AppealCtrl extends BaseController {

	@Autowired
	private AppealService appealService;
	
	@Autowired
	private ResourceManager resourceManager;
	
	@RequestMapping("/app/appeal/publish")
	@ResponseBody
	public Object publish(CurrentUser user, 
			@RequestParam(name = "content") String content,
			@RequestParam(name = "lng") double lng,
			@RequestParam(name = "lat") double lat,
			@RequestParam(name = "phone") String phone,
			@RequestParam MultipartFile[] file,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/appeal/publish");
		Appeal record = new Appeal();
		record.setContent(content);
		record.setPhone(StringUtil.isBlank(phone) ? user.getPhone() : phone);
		record.setUserId(user.getId());
		record.setLng(lng);
		record.setLat(lat);
		record.setStatus(0);
		long time = System.currentTimeMillis()/1000;
		record.setCreateTime(time);
		record.setUpdateTime(time);
		Map<String, Object> data = new HashMap<String, Object>();
		 CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)) {
            List<String> imgs = Lists.newArrayList();
            for(MultipartFile f : file) {
            	if(f != null) {
                    try {
                    	String originFileName = f.getOriginalFilename();
                    	String suffix = getSuffixByFilename(originFileName);
                    	originFileName = originFileName.substring(0,
            					originFileName.length() - suffix.length());
            			String savePath = "{yyyy}/{mm}/{dd}/{time}{rand:6}" + suffix;
                    	String fileName = PathFormat.parse(savePath, originFileName);
            			ResourceIdentifier dest = resourceManager.processResource("appeal_image", f.getBytes(), fileName);
            			imgs.add(dest.getRelativePath());
            		} catch (IOException e) {
            			throw new ApplicationException(ErrorCode.SERVER_ERROR);
            		}
                }
            }
            record.setImgs(Joiner.on(",").join(imgs));
        }
        appealService.insert(record);
		return builder.data(data).build();
	}
	
	@RequestMapping("/app/appeal/mylist")
	@ResponseBody
	public Object mylist(CurrentUser user, 
			@RequestParam(name = "start") int start,
			@RequestParam(name = "limit") int limit,
			HttpServletRequest request) {
		List<Appeal> list = appealService.list(user.getId(), start, limit + 1);
		boolean more = isMore(list, limit);
		if(more) {
			list = list.subList(0, limit);
		}
		JSONObject data = new JSONObject();
		data.put("more", more);
		data.put("list", list);
		return Message.newBuilder("/app/appeal/mylist").data(data).build();
	}
}
