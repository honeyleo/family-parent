package com.family.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.family.common.model.Appeal;
import com.family.common.model.AppealThank;
import com.family.common.model.AppealThankDTO;
import com.family.common.service.AppealService;
import com.family.model.CurrentUser;
import com.family.service.UserProxyService;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import cn.lfy.common.filehandler.ResourceIdentifier;
import cn.lfy.common.filehandler.ResourceManager;
import cn.lfy.common.framework.exception.ApplicationException;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.model.Message;
import cn.lfy.common.web.BaseController;

@Controller
public class AppealCtrl extends BaseController {

	@Value("${fileserver.image.url}")
	private String imageUrl;
	
	@Autowired
	private AppealService appealService;
	
	@Autowired
	private ResourceManager resourceManager;
	
	@Autowired
	private UserProxyService userProxyService;
	
	@RequestMapping("/app/appeal/publish")
	@ResponseBody
	public Object publish(CurrentUser user, 
			@RequestParam(name = "content") String content,
			@RequestParam(name = "lng", required = false, defaultValue = "0") double lng,
			@RequestParam(name = "lat", required = false, defaultValue = "0") double lat,
			@RequestParam(name = "address", required = false, defaultValue = "") String address,
			@RequestParam(name = "fullAddress", required = false, defaultValue = "") String fullAddress,
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
		record.setAddress(address);
		record.setFullAddress(fullAddress);
		record.setStatus(0);
		long time = System.currentTimeMillis()/1000;
		record.setCreateTime(time);
		record.setUpdateTime(time);
		 CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)) {
            List<String> imgs = Lists.newArrayList();
            for(MultipartFile f : file) {
            	if(f != null) {
                    try {
                    	String originFileName = f.getOriginalFilename();
                    	String fileName = getNewFileName(originFileName);
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
        record.setImgsList(getImgsList(record.getImgs(), imageUrl));
		return builder.data(record).build();
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
		for(Appeal record : list) {
			record.setImgsList(getImgsList(record.getImgs(), imageUrl));
        	record.setImgs(null);
		}
		JSONObject data = new JSONObject();
		data.put("more", more);
		data.put("list", list);
		return Message.newBuilder("/app/appeal/mylist").data(data).build();
	}
	
	@RequestMapping("/app/appeal/list")
	@ResponseBody
	public Object list(CurrentUser user, 
			@RequestParam(name = "start") int start,
			@RequestParam(name = "limit") int limit,
			HttpServletRequest request) {
		List<Appeal> list = appealService.familyAppealList(user.getId(), start, limit + 1);
		boolean more = isMore(list, limit);
		if(more) {
			list = list.subList(0, limit);
		}
		List<JSONObject> wrapperList = Lists.newArrayList();
		if(!list.isEmpty()) {
			List<Long> userIdList = Lists.newArrayList();
			for(Appeal appeal : list) {
				userIdList.add(appeal.getUserId());
			}
			Map<Long, CurrentUser> map = userProxyService.getCurrentUsers(userIdList);
			for(Appeal appeal : list) {
				JSONObject obj = new JSONObject();
				appeal.setImgsList(getImgsList(appeal.getImgs(), imageUrl));
				obj.put("appeal", appeal);
				CurrentUser cu = map.get(appeal.getUserId());
				obj.put("user", cu);
				wrapperList.add(obj);
			}
		}
		JSONObject data = new JSONObject();
		data.put("more", more);
		data.put("list", wrapperList);
		return Message.newBuilder("/app/appeal/list").data(data).build();
	}
	
	@RequestMapping("/app/appeal/cancel")
	@ResponseBody
	public Object cancel(CurrentUser user, 
			@RequestParam(name = "id") long id,
			HttpServletRequest request) {
		boolean success = appealService.delete(id) > 0 ? true : false;
		return Message.newBuilder("/app/appeal/cancel").putData("success", success).build();
	}
	
	@RequestMapping("/app/appeal/help")
    @ResponseBody
    public Object help(CurrentUser user, 
            @RequestParam(name = "id") long id,
            HttpServletRequest request) {
        int ret = appealService.addHelp(id, user.getId());
        return Message.newBuilder("/app/appeal/help").putData("success", ret > 0).build();
    }
	
	@RequestMapping("/app/appeal/thank_peoples")
    @ResponseBody
    public Object thankPeoples(CurrentUser user, 
            @RequestParam(name = "appealId") long appealId,
            HttpServletRequest request) {
        List<Long> thankPeopleList = appealService.getThankPeopleList(appealId);
        Map<Long, CurrentUser> currentUsers = userProxyService.getCurrentUsers(thankPeopleList);
        return Message.newBuilder("/app/appeal/thank_peoples").putData("list", currentUsers.values()).build();
    }
	
	@RequestMapping("/app/appeal/thank")
    @ResponseBody
    public Object thank(CurrentUser user, 
            @RequestParam(name = "appealId") long appealId,
            @RequestParam(name = "thankUserIds") long[] thankUserId,
            @RequestParam(name = "contribution", defaultValue = "0") int contribution,
            HttpServletRequest request) {
        int ret = appealService.thank(user.getId(), appealId, thankUserId, contribution);
        return Message.newBuilder("/app/appeal/thank").putData("success", ret > 0).build();
    }
	
	@RequestMapping("/app/appeal/receive_thanks")
    @ResponseBody
    public Object receiveThanks(CurrentUser user, 
            HttpServletRequest request) {
        List<AppealThank> receiveThanks = appealService.getReceiveThanks(user.getId());
        List<AppealThankDTO> list = Lists.newArrayList();
        for(AppealThank thank : receiveThanks) {
        	CurrentUser u = userProxyService.getCurrentUser(thank.getUserId());
        	AppealThankDTO dto = new AppealThankDTO(thank.getId(), "谢谢您", "获得" + thank.getContribution() + "贡献值", 
        			"感谢" + u.getNickname() + "对我的帮助，希望家族里面多一些正能量，特以" + thank.getContribution() + "贡献值作为答谢。", thank.getContribution(), thank.getCreateTime());
        	list.add(dto);
        	appealService.updateAppealThankForReaded(thank.getId());
        }
        return Message.newBuilder("/app/appeal/receive_thanks").putData("list", list).build();
    }
}
