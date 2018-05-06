package com.family.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.family.common.dao.CommentDAO;
import com.family.common.dao.NewsHomeDAO;
import com.family.common.enums.NewsType;
import com.family.common.model.Comment;
import com.family.common.model.NewsHome;
import com.family.common.model.UserNewsFavor;
import com.family.common.service.CommentService;
import com.family.common.service.NewsHomeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.lfy.common.web.BaseController;

@Service
public class CommentServiceImpl implements CommentService {

	@Value("${domain.name}")
	private String domainName;
	
	@Value("${fileserver.image.url}")
	private String imageUrl;
	
	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private NewsHomeService newsHomeService;
	
	@Autowired
	private NewsHomeDAO newsHomeDAO;
	
	public int delete(Long id) {
		return commentDAO.delete(id);
	}

	public int insert(Comment record) {
		int ret = commentDAO.insert(record);
		if(ret > 0) {
			newsHomeService.incrComments(record.getNewsId());
		}
		return ret;
	}

	public Comment get(Long id) {
		return commentDAO.get(id);
	}

	public int update(Comment record) {
		return commentDAO.update(record);
	}

	public List<Comment> list(long newsId, int start, int limit) {
		return commentDAO.list(newsId, start, limit);
	}

	@Override
	public int favor(long userId, long newsId) {
		Comment record = new Comment();
		record.setType(2);
		record.setContent("");
		record.setUserId(userId);
		record.setNewsId(newsId);
		record.setCreateTime(System.currentTimeMillis()/1000);
		return commentDAO.insert(record);
	}
	
	public Boolean isFavor(long userId, long newsId) {
		return commentDAO.isFavor(userId, newsId);
	}
	public Comment getFavor(long userId, long newsId) {
		return commentDAO.getFavor(userId, newsId);
	}
	public List<UserNewsFavor> getUserNewsFavorList(long userId, int start, int limit) {
		List<Comment> commentList = commentDAO.getFavorListByUserId(userId, start, limit + 1);
		Map<Long, Comment> map = Maps.newHashMap();
		for(Comment comment : commentList) {
			map.put(comment.getNewsId(), comment);
		}
		if(map.isEmpty()) {
			return Lists.newArrayList();
		}
		List<UserNewsFavor> list = Lists.newArrayList();
		List<NewsHome> newsHomeList = newsHomeDAO.getNewsIntroListByIds(map.keySet());
		for(NewsHome newsHome : newsHomeList) {
			Comment comment = map.get(newsHome.getId());
			UserNewsFavor favor = new UserNewsFavor();
			favor.setId(comment.getId());
			favor.setNewsId(newsHome.getId());
			NewsType newsType = NewsType.parse(newsHome.getNewsType());
			favor.setNewsType(newsType.getLabel());
			favor.setType(newsHome.getType());
			favor.setTitle(newsHome.getTitle());
			favor.setIntro(newsHome.getIntro());
			favor.setImgShowMode(newsHome.getImgShowMode());
			favor.setImgs(BaseController.getImgsList(newsHome.getImgs(), imageUrl));
			favor.setCreateTime(comment.getCreateTime());
			favor.setNewsCreateTime(newsHome.getCreateTime());
			favor.setDetail_url(domainName + "/app/" + newsType.getLabel() + "/detail/" + newsHome.getId() + ".html");
			list.add(favor);
		}
		return list;
	}
	
}
