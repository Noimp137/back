package com.entity;

//班级信息
public class Classinfo {

	private Integer id;//主键
	
	private String college;//学院
	
	private String major;//专业
	
	private String sessioninfo;//届
	
	private String classname;//班级名
	
	private int stunumber;//学生人数
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSessioninfo() {
		return sessioninfo;
	}

	public void setSessioninfo(String sessioninfo) {
		this.sessioninfo = sessioninfo;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public int getStunumber() {
		return stunumber;
	}

	public void setStunumber(int stunumber) {
		this.stunumber = stunumber;
	}

	
	
	
	
}
