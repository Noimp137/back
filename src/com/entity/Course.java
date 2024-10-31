package com.entity;

//课程
public class Course {

	private Integer id;//主键
	
	private String cname;//课程名
	
	private String credit;//学分
	
	private String classhours;//学时
	
	private String introduction;//课程简介

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getClasshours() {
		return classhours;
	}

	public void setClasshours(String classhours) {
		this.classhours = classhours;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
	
	
	
	
}
