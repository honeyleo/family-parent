package com.family.common.service;

import java.util.Collection;
import java.util.List;

public interface CommentBoxService {

	/**
	 * 用户给评论点赞
	 * @param userId
	 * @param commentId
	 * @return
	 */
	int praise(long userId, long commentId);
    /**
     * 通过用户ID、类型、评论Id列表获取评论Id列表
     * @param userId
     * @param type
     * @param list
     * @return
     */
    List<Long> getCommentIdList(long userId, int type, Collection<Long> list);
}