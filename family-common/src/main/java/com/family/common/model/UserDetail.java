package com.family.common.model;

import java.io.Serializable;

public class UserDetail implements Serializable {

	private static final long serialVersionUID = 8473464107515598298L;

	private Long id;
	/**
	 * 性别：1-男；2-女
	 */
	private Integer gender;
	/**
	 * 生日
	 */
	private Long birthday;
	/**
	 * 出生地
	 */
	private String birthplace;
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
	 * 余额
	 */
	private Double balance;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 星座
	 */
	private Integer constellation;
	/**
	 * 行业
	 */
	private String industry;
	/**
	 * 工作领域
	 */
	private String jobField;
	/**
	 * 公司
	 */
	private String company;
	/**
	 * 工作地
	 */
	private String workplace;
	/**
	 * 常出没地
	 */
	private String hangOut;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 排行
	 */
	private Integer ranking;
	/**
	 * 祖籍
	 */
	private String ancestral;
	
	public static UserDetail newDefaultInstance(Long id) {
		UserDetail detail = new UserDetail();
		detail.setId(id);
		detail.setGender(0);
		detail.setBirthday(0L);
		detail.setBirthplace("");
		detail.setCredit(0);
		detail.setContribution(0);
		detail.setZibei("");
		detail.setBalance(0D);
		detail.setAvatar("");
		detail.setConstellation(0);
		detail.setIndustry("");
		detail.setJobField("");
		detail.setCompany("");
		detail.setWorkplace("");
		detail.setHangOut("");
		detail.setTel("");
		detail.setRanking(0);
		detail.setAncestral("");
		return detail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
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
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
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
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getJobField() {
		return jobField;
	}
	public void setJobField(String jobField) {
		this.jobField = jobField;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	public String getHangOut() {
		return hangOut;
	}
	public void setHangOut(String hangOut) {
		this.hangOut = hangOut;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getRanking() {
		return ranking;
	}
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	public String getAncestral() {
		return ancestral;
	}
	public void setAncestral(String ancestral) {
		this.ancestral = ancestral;
	}
}
