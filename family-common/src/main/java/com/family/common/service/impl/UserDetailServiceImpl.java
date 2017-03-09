package com.family.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.common.dao.UserDetailDAO;
import com.family.common.model.UserDetail;
import com.family.common.model.UserDetailDTO;
import com.family.common.model.UserDetailDTODecorate;
import com.family.common.service.UserDetailService;

import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.utils.Validators;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserDetailDAO userDetailDAO;

	public int deleteByPrimaryKey(Long id) {
		return userDetailDAO.deleteByPrimaryKey(id);
	}

	public int insert(UserDetail record) {
		return userDetailDAO.insert(record);
	}

	public UserDetail selectByPrimaryKey(Long id) {
		return userDetailDAO.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(UserDetail record) {
		return userDetailDAO.updateByPrimaryKeySelective(record);
	}

	public int updateAvatar(Long id, String avatar) {
		return userDetailDAO.updateAvatar(id, avatar);
	}

	@Override
	public int updateMy(Long id, UserDetail userDetail) {
		UserDetail detail = userDetailDAO.selectByPrimaryKey(id);
		Validators.isFalse(detail == null, ErrorCode.NOT_EXIST, "用户信息");
		userDetail.setId(id);
		int ret = userDetailDAO.updateByPrimaryKeySelective(userDetail);
		return ret;
	}

	@Override
	public List<UserDetailDTO> getUserDetailDTOList(List<Long> userIdList) {
		return userDetailDAO.getUserDetailDTOList(userIdList);
	}

	@Override
	public UserDetailDTO getUserDetailDTO(Long id) {
		UserDetailDTO userDetailDTO = userDetailDAO.getUserDetailDTO(id);
		UserDetailDTODecorate userDetailDTODecorate = new UserDetailDTODecorate(userDetailDTO);
		return userDetailDTODecorate;
	}
	
	
}
