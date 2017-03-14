package com.family.common.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import cn.lfy.common.utils.AgeUtil;

public class UserDetailDTO implements Serializable {

	private static final long serialVersionUID = 5553027590594336009L;
	
	/**
	 * 
	 */
	private boolean isFriend;
	
	private Long id;
	/**
     * 登录名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 公司的个人邮箱，邮件提醒功能
     */
    private String email;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 数据状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 姓氏
     */
    private String surname = "";
    /**
     * 名
     */
    private String name = "";
	/**
	 * 性别：0-保密；1-男；2-女
	 */
	private Integer gender;
	/**
	 * 生日
	 */
	private Long birthday;
	/**
	 * 年龄
	 */
	private int age;
	/**
	 * 出生地（国家）
	 */
	private Long birthplaceCountryId; 
	/**
	 * 出生地（省）
	 */
	private Long birthplaceProvinceId;
	/**
	 * 出生地（市）
	 */
	private Long birthplaceCityId;
	/**
	 * 出生地（区/县）
	 */
	private Long birthplaceCountyId;
	/**
	 * 出生地详细地址
	 */
	private String birthplaceAddress;
	/**
	 * 信誉
	 */
	private Integer credit;
	/**
	 * 贡献
	 */
	private Integer contribution;
	/**
	 * 字辈
	 */
	private String zibei;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 星座：1-水瓶座；2-双鱼座；3-白羊座；4-金牛座；5-双子座；6-巨蟹座；
	 * 7-狮子座；8-处女座；9-天秤座；10-天蝎座；11-射手座；12-摩羯座
	 */
	private Integer constellation;
	/**
	 * 行业
	 */
	private Long industry;
	/**
	 * 工作领域
	 */
	private Long jobField;
	/**
	 * 公司
	 */
	private String company;
	/**
	 * 工作地
	 */
	private Long workplaceCountryId;
	/**
	 * 工作地（省）
	 */
	private Long workplaceProvinceId;
	/**
	 * 工作地（市）
	 */
	private Long workplaceCityId;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * JSON对象[{"phone":"18028761234","main":"true"},{"phone":"18028761235","main":"false"}]
	 */
	@JSONField(serialize = false)
	private String phones;
	/**
	 * 排行
	 */
	private Integer ranking;
	/**
	 * 籍贯（国家）
	 */
	private Long nativePlaceCountryId;
	/**
	 * 籍贯（省）
	 */
	private Long nativePlaceProvinceId;
	/**
	 * 籍贯（市）
	 */
	private Long nativePlaceCityId;
	/**
	 * 籍贯（县）
	 */
	private Long nativePlaceCountyId;
	/**
	 * 祖籍（国家）
	 */
	private Long ancestralCountryId;
	/**
	 * 祖籍（省）
	 */
	private Long ancestralProvinceId;
	/**
	 * 祖籍（市）
	 */
	private Long ancestralCityId;
	/**
	 * 祖籍（县）
	 */
	private Long ancestralCountyId;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		age = AgeUtil.getAge(birthday);
		return age;
	}

	public Long getBirthplaceCountryId() {
		return birthplaceCountryId;
	}

	public void setBirthplaceCountryId(Long birthplaceCountryId) {
		this.birthplaceCountryId = birthplaceCountryId;
	}

	public Long getBirthplaceProvinceId() {
		return birthplaceProvinceId;
	}

	public void setBirthplaceProvinceId(Long birthplaceProvinceId) {
		this.birthplaceProvinceId = birthplaceProvinceId;
	}

	public Long getBirthplaceCityId() {
		return birthplaceCityId;
	}

	public void setBirthplaceCityId(Long birthplaceCityId) {
		this.birthplaceCityId = birthplaceCityId;
	}

	public Long getBirthplaceCountyId() {
		return birthplaceCountyId;
	}

	public void setBirthplaceCountyId(Long birthplaceCountyId) {
		this.birthplaceCountyId = birthplaceCountyId;
	}

	public String getBirthplaceAddress() {
		return birthplaceAddress;
	}

	public void setBirthplaceAddress(String birthplaceAddress) {
		this.birthplaceAddress = birthplaceAddress;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Integer getContribution() {
		return contribution;
	}

	public void setContribution(Integer contribution) {
		this.contribution = contribution;
	}

	public String getZibei() {
		return zibei;
	}

	public void setZibei(String zibei) {
		this.zibei = zibei;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getConstellation() {
		return constellation;
	}

	public void setConstellation(Integer constellation) {
		this.constellation = constellation;
	}

	public Long getIndustry() {
		return industry;
	}

	public void setIndustry(Long industry) {
		this.industry = industry;
	}

	public Long getJobField() {
		return jobField;
	}

	public void setJobField(Long jobField) {
		this.jobField = jobField;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Long getWorkplaceCountryId() {
		return workplaceCountryId;
	}

	public void setWorkplaceCountryId(Long workplaceCountryId) {
		this.workplaceCountryId = workplaceCountryId;
	}

	public Long getWorkplaceProvinceId() {
		return workplaceProvinceId;
	}

	public void setWorkplaceProvinceId(Long workplaceProvinceId) {
		this.workplaceProvinceId = workplaceProvinceId;
	}

	public Long getWorkplaceCityId() {
		return workplaceCityId;
	}

	public void setWorkplaceCityId(Long workplaceCityId) {
		this.workplaceCityId = workplaceCityId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public Long getNativePlaceCountryId() {
		return nativePlaceCountryId;
	}

	public void setNativePlaceCountryId(Long nativePlaceCountryId) {
		this.nativePlaceCountryId = nativePlaceCountryId;
	}

	public Long getNativePlaceProvinceId() {
		return nativePlaceProvinceId;
	}

	public void setNativePlaceProvinceId(Long nativePlaceProvinceId) {
		this.nativePlaceProvinceId = nativePlaceProvinceId;
	}

	public Long getNativePlaceCityId() {
		return nativePlaceCityId;
	}

	public void setNativePlaceCityId(Long nativePlaceCityId) {
		this.nativePlaceCityId = nativePlaceCityId;
	}

	public Long getNativePlaceCountyId() {
		return nativePlaceCountyId;
	}

	public void setNativePlaceCountyId(Long nativePlaceCountyId) {
		this.nativePlaceCountyId = nativePlaceCountyId;
	}

	public Long getAncestralCountryId() {
		return ancestralCountryId;
	}

	public void setAncestralCountryId(Long ancestralCountryId) {
		this.ancestralCountryId = ancestralCountryId;
	}

	public Long getAncestralProvinceId() {
		return ancestralProvinceId;
	}

	public void setAncestralProvinceId(Long ancestralProvinceId) {
		this.ancestralProvinceId = ancestralProvinceId;
	}

	public Long getAncestralCityId() {
		return ancestralCityId;
	}

	public void setAncestralCityId(Long ancestralCityId) {
		this.ancestralCityId = ancestralCityId;
	}

	public Long getAncestralCountyId() {
		return ancestralCountyId;
	}

	public void setAncestralCountyId(Long ancestralCountyId) {
		this.ancestralCountyId = ancestralCountyId;
	}

	public boolean getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
	
}
