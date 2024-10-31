package com.entity;

//订购教材
public class Orders {

	private Integer id;//主键
	
	private Integer bookid;//教材id
	
	private String bnumber;//教材编号
	
	private String bcategory;//教材类别
	
	private String bname;//教材名称
	
	private String press;//出版社
	
	private Integer ordernumber;//订购数量
	
	private String ctime;//订购时间
	
	private String status;//订购状态 未确认/已确认
	
	private String storageStatus;//入库状态  未入库/已入库
	
	private String storagetime;//入库时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBnumber() {
		return bnumber;
	}

	public void setBnumber(String bnumber) {
		this.bnumber = bnumber;
	}

	public String getBcategory() {
		return bcategory;
	}

	public void setBcategory(String bcategory) {
		this.bcategory = bcategory;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public Integer getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
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

	public String getStorageStatus() {
		return storageStatus;
	}

	public void setStorageStatus(String storageStatus) {
		this.storageStatus = storageStatus;
	}

	public String getStoragetime() {
		return storagetime;
	}

	public void setStoragetime(String storagetime) {
		this.storagetime = storagetime;
	}

	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	
	
	
	
	
}
