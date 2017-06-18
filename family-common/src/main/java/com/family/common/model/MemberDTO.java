package com.family.common.model;

import java.util.Set;

import com.google.common.collect.Sets;

public class MemberDTO implements Comparable<MemberDTO> {

	/**
	 * 儿子列表
	 */
	private Set<MemberDTO> childrens = Sets.newTreeSet();
	/**
	 * 配偶列表
	 */
	private Set<MemberDTO> spouses = Sets.newTreeSet();
	
	private Long id;
	/**
	 * 关联的用户ID
	 */
	private Long userId;
	/**
	 * 创建者用户ID
	 */
	private Long creatorUserId;
	/**
	 * 父亲ID
	 */
	private Long fatherId;
	/**
	 * 母亲ID
	 */
	private Long motherId;
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
	/**
	 * 0-已故；1-健在
	 */
	private Integer alive;
	/**
	 * 逝世时间
	 */
	private Long dieTime;
	/**
	 * 1-离异；0-未离异
	 */
	private Integer divorced;
	/**
	 * 个人简介
	 */
	private String intro;
	
	public Set<MemberDTO> getChildrens() {
		return childrens;
	}
	public void setChildrens(Set<MemberDTO> childrens) {
		this.childrens = childrens;
	}
	public Set<MemberDTO> getSpouses() {
		return spouses;
	}
	public void setSpouses(Set<MemberDTO> spouses) {
		this.spouses = spouses;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCreatorUserId() {
		return creatorUserId;
	}
	public void setCreatorUserId(Long creatorUserId) {
		this.creatorUserId = creatorUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getFatherId() {
		return fatherId;
	}
	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}
	public Long getMotherId() {
		return motherId;
	}
	public void setMotherId(Long motherId) {
		this.motherId = motherId;
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
	public Integer getAlive() {
		return alive;
	}
	public void setAlive(Integer alive) {
		this.alive = alive;
	}
	
	public Long getDieTime() {
		return dieTime;
	}
	public void setDieTime(Long dieTime) {
		this.dieTime = dieTime;
	}
	
	public Integer getDivorced() {
		return divorced;
	}
	public void setDivorced(Integer divorced) {
		this.divorced = divorced;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	@Override
	public int compareTo(MemberDTO o) {
		if(this.getRanking() > o.getRanking()) {
			return 1;
		} else if(this.getRanking() < o.getRanking()) {
			return -1;
		} else {
			if(this.getId() > o.getId()) {
				return 1;
			} else if(this.getId() < o.getId()) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
}
