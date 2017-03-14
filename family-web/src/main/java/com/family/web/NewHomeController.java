package com.family.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.common.enums.NewsType;
import com.family.common.model.Comment;
import com.family.common.model.NewsHome;
import com.family.common.service.CommentService;
import com.family.common.service.NewsHomeService;
import com.family.model.CurrentUser;
import com.family.service.UserProxyService;
import com.google.common.collect.Lists;

import cn.lfy.common.model.Message;
import cn.lfy.common.web.BaseController;

@Controller
public class NewHomeController extends BaseController {

	@Value("${domain.name}")
	private String domainName;
	
	@Value("${fileserver.image.url}")
	private String imageUrl;
	
	@Autowired
	private NewsHomeService newsHomeService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserProxyService userproxyService;
	/**
	 * 新闻列表
	 * @param type
	 * @param start
	 * @param limit
	 * @param lastUpdateTime
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/{news_type}/list")
	@ResponseBody
	public Object list(@PathVariable("news_type") String newsType, 
			@RequestParam(name = "type", defaultValue = "1") int type, 
			@RequestParam(name = "start", defaultValue = "0") int start, 
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "last_update_time", defaultValue = "0") long lastUpdateTime,
			HttpServletRequest request) {
		List<NewsHome> list = newsHomeService.list(NewsType.parse(newsType), type, start, limit + 1);
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
			dto.put("comments", newsHome.getComments());
			List<String> imgs = getImgsList(newsHome.getImgs(), imageUrl);
			dto.put("imgs", imgs);
			dto.put("detail_url", domainName + "/app/" + newsType + "/detail/" + newsHome.getId() + ".html");
			jsonList.add(dto);
		}
		Message.Builder builder = Message.newBuilder("/app/" + newsType + "/list");
		JSONObject data = new JSONObject();
		data.put("more", more);
		data.put("newestCount", count);
		data.put("data", jsonList);
		builder.data(data);
		return builder.build();
	}
	
	/**
	 * 评论列表
	 * @param newsId
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/{news_type}/detail/{newsId}")
	@ResponseBody
	public Object detail(@PathVariable("news_type") String newsType, 
			@PathVariable("newsId") long newsId, 
			HttpServletRequest request) {
		NewsHome newsHome = newsHomeService.getById(newsId);
		Message.Builder builder = Message.newBuilder("/app/" + newsType + "/detail/" + newsId);
		if(newsHome != null) {
			JSONObject dto = new JSONObject();
			dto.put("id", newsHome.getId());
			dto.put("title", newsHome.getTitle());
			dto.put("intro", newsHome.getIntro());
			dto.put("type", newsHome.getType());
			dto.put("imgShowMode", newsHome.getImgShowMode());
			dto.put("createTime", newsHome.getCreateTime());
			dto.put("content", htmlContentImageAppendDomain(newsHome.getContent(), imageUrl));
			dto.put("comments", newsHome.getComments());
			List<String> imgs = getImgsList(newsHome.getImgs(), imageUrl);
			dto.put("imgs", imgs);
			builder.data(dto);
		}
		return builder.build();
	}
	/**
	 * 评论详情
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/{news_type}/detail/{newsId}.html")
	public String detailHtml(@PathVariable("news_type") String newsType, 
			@PathVariable("newsId") long newsId, 
			HttpServletRequest request) {
		NewsHome newsHome = newsHomeService.getById(newsId);
		if(newsHome != null) {
			JSONObject dto = new JSONObject();
			dto.put("id", newsHome.getId());
			dto.put("title", newsHome.getTitle());
			dto.put("intro", newsHome.getIntro());
			dto.put("type", newsHome.getType());
			dto.put("imgShowMode", newsHome.getImgShowMode());
			dto.put("createTime", newsHome.getCreateTime());
			dto.put("content", htmlContentImageAppendDomain(newsHome.getContent(), imageUrl));
			dto.put("comments", newsHome.getComments());
			List<String> imgs = getImgsList(newsHome.getImgs(), imageUrl);
			dto.put("imgs", imgs);
			request.setAttribute("news", dto);
		}
		return "/news/detail";
	}
	/**
	 * 评论列表
	 * @param newsId
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/{news_type}/comments")
	@ResponseBody
	public Object comments(@PathVariable("news_type") String newsType, 
			@RequestParam(name = "newsId", defaultValue = "0") long newsId, 
			@RequestParam(name = "start") int start, 
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			HttpServletRequest request) {
		List<Comment> list = commentService.list(newsId, start, limit + 1);
		boolean more = isMore(list, limit);
		if(more) {
			list = list.subList(0, limit);
		}
		Message.Builder builder = Message.newBuilder("/app/news_home/comments");
		JSONObject data = new JSONObject();
		JSONArray array = new JSONArray();
		for(Comment comment : list) {
			JSONObject dto = new JSONObject();
			dto.put("id", comment.getId());
			dto.put("newsId", comment.getNewsId());
			dto.put("content", comment.getContent());
			dto.put("createTime", comment.getCreateTime());
			CurrentUser user = userproxyService.getCurrentUser(comment.getUserId());
			dto.put("username", user.getNickname());
			dto.put("userId", comment.getUserId());
			array.add(dto);
		}
		data.put("more", more);
		data.put("list", array);
		builder.data(data);
		return builder.build();
	}
	/**
	 * 评论新闻
	 * @param newsId
	 * @param content
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/{news_type}/comment")
	@ResponseBody
	public Object comment(@PathVariable("news_type") String newsType, 
			@RequestParam(name = "newsId", defaultValue = "0") long newsId, 
			@RequestParam(name = "content") String content, 
			CurrentUser user,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/news_home/comment");
		Comment record = new Comment();
		record.setContent(content);
		record.setNewsId(newsId);
		record.setType(1);
		record.setUserId(user.getId());
		record.setCreateTime(System.currentTimeMillis()/1000);
		commentService.insert(record);
		return builder.build();
	}
	
	/**
	 * 收藏新闻
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/{news_type}/favor")
	@ResponseBody
	public Object favor(@PathVariable("news_type") String newsType, 
			@RequestParam(name = "newsId", defaultValue = "0") long newsId, 
			CurrentUser user,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/news_home/favor");
		commentService.favor(user.getId(), newsId);
		return builder.build();
	}
}
