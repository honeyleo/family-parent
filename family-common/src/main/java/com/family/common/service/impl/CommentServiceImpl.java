package com.family.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.common.dao.CommentDAO;
import com.family.common.model.Comment;
import com.family.common.service.CommentService;
import com.family.common.service.NewsHomeService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private NewsHomeService newsHomeService;
	
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
		record.setUserId(userId);
		record.setNewsId(newsId);
		record.setCreateTime(System.currentTimeMillis()/1000);
		return commentDAO.insert(record);
	}
	
	
}
