package com.family.web;

import java.util.Date;
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
import com.family.common.service.CommentBoxService;
import com.family.common.service.CommentService;
import com.family.common.service.NewsHomeService;
import com.family.model.CurrentUser;
import com.family.service.UserProxyService;
import com.google.common.collect.Lists;

import cn.lfy.common.model.Message;
import cn.lfy.common.utils.DateUtils;
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
	
	@Autowired
	private CommentBoxService commentBoxService;
	
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
	public Object list(CurrentUser currentUser,
			@PathVariable("news_type") String newsType, 
			@RequestParam(name = "type", defaultValue = "1") int type, 
			@RequestParam(name = "start", defaultValue = "0") int start, 
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "last_update_time", defaultValue = "0") long lastUpdateTime,
			HttpServletRequest request) {
		NewsType newsTypeEnum = NewsType.parse(newsType);
		String surname = null;
		if(newsTypeEnum == NewsType.NEWS_CONSULT) {
			surname = currentUser.getSurname();
		}
		List<NewsHome> list = newsHomeService.list(surname, newsTypeEnum, type, start, limit + 1);
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
			dto.put("detail_url", domainName + "/app/" + newsType + "/news-detail/" + newsHome.getId() + ".html");
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
	 * 评论详情
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/{news_type}/news-detail/{newsId}.html")
	public String newsDetailHtml(
			CurrentUser currentUser,
			@PathVariable("news_type") String newsType, 
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
			request.setAttribute("isFavor", commentService.isFavor(currentUser.getId(), newsId));
			request.setAttribute("access_token", getAccessToken(request));
			request.setAttribute("currentUser", currentUser);
		}
		return "/news/news-detail";
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
			CurrentUser currentUser,
			@RequestParam(name = "newsId", defaultValue = "0") long newsId, 
			@RequestParam(name = "start") int start, 
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			HttpServletRequest request) {
		List<Comment> list = commentService.list(newsId, start, limit + 1);
		boolean more = isMore(list, limit);
		if(more) {
			list = list.subList(0, limit);
		}
		Message.Builder builder = Message.newBuilder("/app/" + newsType + "/comments");
		List<Long> commentIdList = Lists.newArrayList();
		for(Comment comment : list) {
			commentIdList.add(comment.getId());
		}
		
		List<Long> containCommentIdList = commentBoxService.getCommentIdList(currentUser.getId(), 1, commentIdList);
		JSONObject data = new JSONObject();
		JSONArray array = new JSONArray();
		for(Comment comment : list) {
			JSONObject dto = new JSONObject();
			dto.put("id", comment.getId());
			dto.put("newsId", comment.getNewsId());
			dto.put("content", comment.getContent());
			dto.put("praiseCount", comment.getPraiseCount());
			boolean isPraised = containCommentIdList.contains(comment.getId());
			dto.put("isPraised", isPraised);
			dto.put("createTime", DateUtils.date2String2(new Date(comment.getCreateTime() * 1000)));
			CurrentUser user = userproxyService.getCurrentUser(comment.getUserId());
			dto.put("username", user.getUsername());
			dto.put("nickname", user.getNickname());
			dto.put("userId", comment.getUserId());
			dto.put("avatar", user.getAvatar());
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
		Message.Builder builder = Message.newBuilder("/app/" + newsType + "/comment");
		Comment record = new Comment();
		record.setContent(content);
		record.setNewsId(newsId);
		record.setType(1);
		record.setUserId(user.getId());
		record.setCreateTime(System.currentTimeMillis()/1000);
		record.setPraiseCount(0);
		commentService.insert(record);
		builder.putData("createTime", DateUtils.date2String2(new Date(record.getCreateTime()*1000)));
		builder.putData("praiseCount", 0);
		return builder.build();
	}
	
	/**
	 * 评论新闻
	 * @param newsId
	 * @param content
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/{news_type}/praise")
	@ResponseBody
	public Object praise(@PathVariable("news_type") String newsType, 
			@RequestParam(name = "commentId", defaultValue = "0") long commentId, 
			CurrentUser user,
			HttpServletRequest request) {
		Message.Builder builder = Message.newBuilder("/app/" + newsType + "/praise");
		int ret = commentBoxService.praise(user.getId(), commentId);
		if(ret == 2) {
			builder.setRet(2).setMsg("已赞过，不要重复点赞");
		}
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
		Message.Builder builder = Message.newBuilder("/app/" + newsType + "/favor");
		if(commentService.isFavor(user.getId(), newsId)) {
			builder.setMsg("已收藏过，不需重复收藏");
			return builder.build();
		}
		commentService.favor(user.getId(), newsId);
		return builder.build();
	}
}
