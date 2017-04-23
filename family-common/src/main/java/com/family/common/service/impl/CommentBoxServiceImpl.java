package com.family.common.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.family.common.dao.CommentBoxDAO;
import com.family.common.dao.CommentDAO;
import com.family.common.model.CommentBox;
import com.family.common.service.CommentBoxService;
import com.google.common.collect.Lists;

@Service
public class CommentBoxServiceImpl implements CommentBoxService {

	@Autowired
	private CommentBoxDAO commentBoxDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Override
	@Transactional
	public int praise(long userId, long commentId) {
		if(commentBoxDAO.isPraised(userId, commentId)) {
			return 2;
		}
		CommentBox record = new CommentBox();
		record.setUserId(userId);
		record.setType(1);
		record.setCommentId(commentId);
		record.setCreateTime(System.currentTimeMillis()/1000);
		commentBoxDAO.insert(record);
		int ret = commentDAO.incrPraiseCount(commentId);
		return ret;
	}

	@Override
	public List<Long> getCommentIdList(long userId, int type, Collection<Long> list) {
		if(list == null || list.isEmpty()) {
			return Lists.newArrayList();
		}
		return commentBoxDAO.getCommentIdList(userId, type, list);
	}

}
