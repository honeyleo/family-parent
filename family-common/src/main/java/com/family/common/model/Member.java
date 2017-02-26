package com.family.common.model;

import java.io.Serializable;

public class Member implements Serializable {

	private static final long serialVersionUID = 8473464107515598298L;

	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别：0-保密；1-男；2-女
	 */
	private Integer gender;
	/**
	 * 生日
	 */
	private Long birthday;
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
	 * 字辈
	 */
	private String zibei;
	/**
	 * 头像
	 */
	private String avatar;
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
	
	public static Member newDefaultInstance(Long id) {
		Member detail = new Member();
		detail.setId(id);
		detail.setGender(0);
		detail.setBirthday(0L);
		detail.setBirthplaceCountryId(0L);
		detail.setBirthplaceProvinceId(0L);
		detail.setBirthplaceCityId(0L);
		detail.setBirthplaceCountyId(0L);
		detail.setZibei("");
		detail.setAvatar("");
		detail.setRanking(0);
		detail.setNativePlaceCountryId(0L);
		detail.setNativePlaceProvinceId(0L);
		detail.setNativePlaceCityId(0L);
		detail.setNativePlaceCountyId(0L);
		detail.setAncestralCountryId(0L);
		detail.setAncestralProvinceId(0L);
		detail.setAncestralCityId(0L);
		detail.setAncestralCountyId(0L);
		return detail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	

}
