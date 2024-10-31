package com.entity;

//教材选定记录
public class Selectedlog {

	private Integer id;//主键
	
	private Integer userid;//教师id
	
	private String username;//工号
	
	private String name;//姓名
	
	private Integer courseid;//所属课程id
	
	private String cname;//所属课程
	
	private Integer bookid;//教材id
	
	private String bnumber;//教材编号
	
	private String bname;//教材名称
	
	private Integer classinfoid;//班级id
	
	private String college;//学院
	
	private String major;//专业
	
	private String sessioninfo;//届
	
	private String classname;//班级名
	
	private int snumber;//选定数量
	
	private String ctime;//提交时间
	
	private String status;//审核状态  未提交/审核中/审核通过/审核不通过
	
	private String result;//选定理由
	
	private String stime;//审核时间
	
	private String feedback;//审核反馈
	
	private int receive;//领取状态 0表示未领取/1表示已领取
	
	private String receivetime;//领取时间
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public String getBnumber() {
		return bnumber;
	}

	public void setBnumber(String bnumber) {
		this.bnumber = bnumber;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Integer getClassinfoid() {
		return classinfoid;
	}

	public void setClassinfoid(Integer classinfoid) {
		this.classinfoid = classinfoid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public int getSnumber() {
		return snumber;
	}

	public void setSnumber(int snumber) {
		this.snumber = snumber;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
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

	public int getReceive() {
		return receive;
	}

	public void setReceive(int receive) {
		this.receive = receive;
	}

	public String getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(String receivetime) {
		this.receivetime = receivetime;
	}
	
	
	
	
	
	
	
	
}
