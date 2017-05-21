package com.family.common.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;

public class UserDetailDTODecorate extends UserDetailDTO {

	private static final long serialVersionUID = 6418582293510788859L;
	
	private List<Phone> phoneList;
	
	private List<Company> companyList;
	
	@JSONField(serialize = false)
	UserDetailDTO userDetailDTO;
	
	public UserDetailDTODecorate(UserDetailDTO userDetailDTO) {
		this.userDetailDTO = userDetailDTO;
		if(StringUtils.isNotBlank(userDetailDTO.getPhones())) {
			phoneList = JSON.parseArray(userDetailDTO.getPhones(), Phone.class);
		}
		if(StringUtils.isNotBlank(userDetailDTO.getCompany())) {
			try {
				companyList = JSON.parseArray(userDetailDTO.getCompany(), Company.class);
			} catch (Exception e) {
				companyList = Lists.newArrayList();
				Company company = new Company();
				company.setCompany(userDetailDTO.getCompany());
				company.setMain(true);
				companyList.add(company);
			}
		}
	}
	
	public Long getId() {
		return userDetailDTO.getId();
	}

	public void setId(Long id) {
		userDetailDTO.setId(id);
	}

	public String getUsername() {
		return userDetailDTO.getUsername();
	}

	public void setUsername(String username) {
		userDetailDTO.setUsername(username);
	}

	public String getNickname() {
		return userDetailDTO.getNickname();
	}

	public void setNickname(String nickname) {
		userDetailDTO.setNickname(nickname);
	}

	public String getEmail() {
		return userDetailDTO.getEmail();
	}

	public void setEmail(String email) {
		userDetailDTO.setEmail(email);
	}

	public String getPhone() {
		return userDetailDTO.getPhone();
	}

	public void setPhone(String phone) {
		userDetailDTO.setPhone(phone);
	}

	public Integer getState() {
		return userDetailDTO.getState();
	}

	public void setState(Integer state) {
		userDetailDTO.setState(state);
	}

	public Date getCreateTime() {
		return userDetailDTO.getCreateTime();
	}

	public void setCreateTime(Date createTime) {
		userDetailDTO.setCreateTime(createTime);
	}

	public String getSurname() {
		return userDetailDTO.getSurname();
	}

	public void setSurname(String surname) {
		userDetailDTO.setSurname(surname);
	}

	public String getName() {
		return userDetailDTO.getName();
	}

	public void setName(String name) {
		userDetailDTO.setName(name);
	}

	public Integer getGender() {
		return userDetailDTO.getGender();
	}

	public void setGender(Integer gender) {
		userDetailDTO.setGender(gender);
	}

	public Long getBirthday() {
		return userDetailDTO.getBirthday();
	}

	public void setBirthday(Long birthday) {
		userDetailDTO.setBirthday(birthday);
	}

	public Long getBirthplaceCountryId() {
		return userDetailDTO.getBirthplaceCountryId();
	}

	public void setBirthplaceCountryId(Long birthplaceCountryId) {
		userDetailDTO.setBirthplaceCountryId(birthplaceCountryId);
	}

	public Long getBirthplaceProvinceId() {
		return userDetailDTO.getBirthplaceProvinceId();
	}

	public void setBirthplaceProvinceId(Long birthplaceProvinceId) {
		userDetailDTO.setBirthplaceProvinceId(birthplaceProvinceId);
	}

	public Long getBirthplaceCityId() {
		return userDetailDTO.getBirthplaceCityId();
	}

	public void setBirthplaceCityId(Long birthplaceCityId) {
		userDetailDTO.setBirthplaceCityId(birthplaceCityId);
	}

	public Long getBirthplaceCountyId() {
		return userDetailDTO.getBirthplaceCountyId();
	}

	public void setBirthplaceCountyId(Long birthplaceCountyId) {
		userDetailDTO.setBirthplaceCountyId(birthplaceCountyId);
	}

	public Integer getCredit() {
		return userDetailDTO.getCredit();
	}

	public void setCredit(Integer credit) {
		userDetailDTO.setCredit(credit);
	}

	public Integer getContribution() {
		return userDetailDTO.getContribution();
	}

	public void setContribution(Integer contribution) {
		userDetailDTO.setContribution(contribution);
	}

	public String getZibei() {
		return userDetailDTO.getZibei();
	}

	public void setZibei(String zibei) {
		userDetailDTO.setZibei(zibei);
	}

	public String getAvatar() {
		return userDetailDTO.getAvatar();
	}

	public void setAvatar(String avatar) {
		userDetailDTO.setAvatar(avatar);
	}

	public Integer getConstellation() {
		return userDetailDTO.getConstellation();
	}

	public void setConstellation(Integer constellation) {
		userDetailDTO.setConstellation(constellation);
	}

	public Long getIndustry() {
		return userDetailDTO.getIndustry();
	}

	public void setIndustry(Long industry) {
		userDetailDTO.setIndustry(industry);
	}

	public Long getJobField() {
		return userDetailDTO.getJobField();
	}

	public void setJobField(Long jobField) {
		userDetailDTO.setJobField(jobField);
	}

	public String getCompany() {
		return userDetailDTO.getCompany();
	}

	public void setCompany(String company) {
		userDetailDTO.setCompany(company);
	}

	public Long getWorkplaceCountryId() {
		return userDetailDTO.getWorkplaceCountryId();
	}

	public void setWorkplaceCountryId(Long workplaceCountryId) {
		userDetailDTO.setWorkplaceCountryId(workplaceCountryId);
	}

	public Long getWorkplaceProvinceId() {
		return userDetailDTO.getWorkplaceProvinceId();
	}

	public void setWorkplaceProvinceId(Long workplaceProvinceId) {
		userDetailDTO.setWorkplaceProvinceId(workplaceProvinceId);
	}

	public Long getWorkplaceCityId() {
		return userDetailDTO.getWorkplaceCityId();
	}

	public void setWorkplaceCityId(Long workplaceCityId) {
		userDetailDTO.setWorkplaceCityId(workplaceCityId);
	}

	public String getTel() {
		return userDetailDTO.getTel();
	}

	public void setTel(String tel) {
		userDetailDTO.setTel(tel);
	}

	public String getPhones() {
		return userDetailDTO.getPhones();
	}

	public void setPhones(String phones) {
		userDetailDTO.setPhones(phones);
	}

	public Integer getRanking() {
		return userDetailDTO.getRanking();
	}

	public void setRanking(Integer ranking) {
		userDetailDTO.setRanking(ranking);
	}

	public Long getNativePlaceCountryId() {
		return userDetailDTO.getNativePlaceCountryId();
	}

	public void setNativePlaceCountryId(Long nativePlaceCountryId) {
		userDetailDTO.setNativePlaceCountryId(nativePlaceCountryId);
	}

	public Long getNativePlaceProvinceId() {
		return userDetailDTO.getNativePlaceProvinceId();
	}

	public void setNativePlaceProvinceId(Long nativePlaceProvinceId) {
		userDetailDTO.setNativePlaceProvinceId(nativePlaceProvinceId);
	}

	public Long getNativePlaceCityId() {
		return userDetailDTO.getNativePlaceCityId();
	}

	public void setNativePlaceCityId(Long nativePlaceCityId) {
		userDetailDTO.setNativePlaceCityId(nativePlaceCityId);
	}

	public Long getNativePlaceCountyId() {
		return userDetailDTO.getNativePlaceCountyId();
	}

	public void setNativePlaceCountyId(Long nativePlaceCountyId) {
		userDetailDTO.setNativePlaceCountyId(nativePlaceCountyId);
	}

	public Long getAncestralCountryId() {
		return userDetailDTO.getAncestralCountryId();
	}

	public void setAncestralCountryId(Long ancestralCountryId) {
		userDetailDTO.setAncestralCountryId(ancestralCountryId);
	}

	public Long getAncestralProvinceId() {
		return userDetailDTO.getAncestralProvinceId();
	}

	public void setAncestralProvinceId(Long ancestralProvinceId) {
		userDetailDTO.setAncestralProvinceId(ancestralProvinceId);
	}

	public Long getAncestralCityId() {
		return userDetailDTO.getAncestralCityId();
	}

	public void setAncestralCityId(Long ancestralCityId) {
		userDetailDTO.setAncestralCityId(ancestralCityId);
	}

	public Long getAncestralCountyId() {
		return userDetailDTO.getAncestralCountyId();
	}

	public void setAncestralCountyId(Long ancestralCountyId) {
		userDetailDTO.setAncestralCountyId(ancestralCountyId);
	}

	public List<Phone> getPhoneList() {
		return phoneList;
	}
	
	public List<Company> getCompanyList() {
		return companyList;
	}

	public List<Interest> getInterestList() {
		return userDetailDTO.getInterestList();
	}

	public void setInterestList(List<Interest> interestList) {
		userDetailDTO.setInterestList(interestList);
	}

	public static void main(String[] args) {
		UserDetailDTO user = new UserDetailDTO();
		user.setId(12L);
		user.setPhones("[{\"phone\":\"123\"}]");
		
		UserDetailDTODecorate ud = new UserDetailDTODecorate(user);
		
		System.out.println(JSON.toJSONString(ud));
	}
}
