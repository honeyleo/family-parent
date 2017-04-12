package com.family.common.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.family.common.dao.MemberDAO;
import com.family.common.dao.UserDAO;
import com.family.common.dao.UserDetailDAO;
import com.family.common.model.Company;
import com.family.common.model.Member;
import com.family.common.model.Phone;
import com.family.common.model.UserDetail;
import com.family.common.model.UserDetailDTO;
import com.family.common.model.UserDetailDTODecorate;
import com.family.common.service.UserDetailService;
import com.google.common.collect.Lists;

import cn.lfy.base.model.User;
import cn.lfy.common.framework.exception.ErrorCode;
import cn.lfy.common.utils.Validators;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Value("${fileserver.image.url}")
	private String imageUrl;
	
	@Autowired
	private UserDetailDAO userDetailDAO;
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private MemberDAO memberDAO;

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
		Validators.isFalse(detail == null, ErrorCode.VALUE_NOT_EXIST, "用户信息");
		userDetail.setId(id);
		int ret = userDetailDAO.updateByPrimaryKeySelective(userDetail);
		if(ret > 0) {
			detail = userDetailDAO.selectByPrimaryKey(id);
			int credit = getCredit(detail);
			userDetailDAO.updateCredit(id, credit);
		}
		return ret;
	}
	private int getCredit(UserDetail userDetail) {
		int credit = 0;
		if(userDetail.getConstellation() != null && userDetail.getConstellation().intValue() != 0) {
			credit += 2;
		}
		if(userDetail.getGender() != null && userDetail.getGender().intValue() != 0) {
			credit += 1;
		}
		if(userDetail.getIndustry() != null && userDetail.getIndustry().intValue() != 0) {
			credit += 2;
		}
		if(userDetail.getJobField() != null && userDetail.getJobField().intValue() != 0) {
			credit += 2;
		}
		if(StringUtils.isNotBlank(userDetail.getCompany())) {
			try {
				List<Company> companies = JSON.parseArray(userDetail.getCompany(), Company.class);
				Iterator<Company> it = companies.iterator();
				while(it.hasNext()) {
					it.next();
					credit += 1;
				}
			}catch (Exception e) {
				Company company = new Company();
				company.setCompany(userDetail.getCompany());
				company.setMain(true);
				List<Company> list = Lists.newArrayList();
				list.add(company);
				credit += 1;
			}
		}
		if(userDetail.getWorkplaceCountryId() != null && userDetail.getWorkplaceCountryId().intValue() != 0) {
			credit += 2;
		}
		if(StringUtils.isNotBlank(userDetail.getPhones())) {
			List<Phone> phones = JSON.parseArray(userDetail.getPhones(), Phone.class);
			Iterator<Phone> it = phones.iterator();
			while(it.hasNext()) {
				it.next();
				credit += 2;
			}
		}
		if(StringUtils.isNotBlank(userDetail.getZibei())) {
			credit += 3;
		}
		if(userDetail.getBirthday() != null && userDetail.getBirthday() > 0) {
			credit += 2;
		}
		if(userDetail.getRanking() != null && userDetail.getRanking() > 0) {
			credit += 2;
		}
		if(userDetail.getRanking() != null && userDetail.getRanking() > 0) {
			credit += 2;
		}
		if(userDetail.getBirthplaceCountryId() != null && userDetail.getBirthplaceCountryId().intValue() != 0) {
			credit += 4;
		}
		if(userDetail.getNativePlaceCountryId() != null && userDetail.getNativePlaceCountryId().intValue() != 0) {
			credit += 4;
		}
		if(userDetail.getAncestralProvinceId() != null && userDetail.getAncestralProvinceId().intValue() != 0) {
			credit += 4;
		}
		if(StringUtils.isNotBlank(userDetail.getAvatar())) {
			credit += 8;
		}
		User user = userDAO.selectByPrimaryKey(userDetail.getId());
		if(user != null && user.getCert() == 1) {
			credit += 25;
		}
		Member self = memberDAO.getSelf(userDetail.getId());
		if(self != null) {
			credit +=20;
		}
		return credit;
	}
	@Override
	public List<Phone> updatePhones(Long id, String phones) {
		UserDetail detail = userDetailDAO.selectByPrimaryKey(id);
		Validators.isFalse(detail == null, ErrorCode.VALUE_NOT_EXIST, "用户信息");
		userDetailDAO.updatePhones(id, phones);
		List<Phone> list = JSON.parseArray(phones, Phone.class);
		return list;
	}

	@Override
	public List<UserDetailDTO> getUserDetailDTOList(List<Long> userIdList) {
		List<UserDetailDTO> list = userDetailDAO.getUserDetailDTOList(userIdList);
		return list;
	}

	@Override
	public UserDetailDTO getUserDetailDTO(Long id) {
		UserDetailDTO userDetailDTO = userDetailDAO.getUserDetailDTO(id);
		if(userDetailDTO.getAvatar() != null) {
			userDetailDTO.setAvatar(imageUrl + userDetailDTO.getAvatar());
		}
		UserDetailDTODecorate userDetailDTODecorate = new UserDetailDTODecorate(userDetailDTO);
		return userDetailDTODecorate;
	}
	
	
}
