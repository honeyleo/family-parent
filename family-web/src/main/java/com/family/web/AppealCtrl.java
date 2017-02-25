package com.family.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
import cn.lfy.common.utils.UUIDUtil;

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
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/appeal/publish");
		Appeal record = new Appeal();
		record.setContent(content);
		record.setPhone(StringUtil.isBlank(phone) ? user.getPhone() : phone);
		record.setUserId(user.getId());
		record.setLng(lng);
		record.setLat(lat);
		Map<String, Object> data = new HashMap<String, Object>();
		 CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator<String> iter=multiRequest.getFileNames();
            List<String> imgs = Lists.newArrayList();
            while(iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null) {
                    try {
                    	String fileName = UUIDUtil.uuid();
            			ResourceIdentifier dest = resourceManager.processResource("avatar_image", file.getBytes(), fileName);
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
}
