package com.entity;

//教材
public class Book {

	private Integer id;//主键
	
	private String bnumber;//教材编号
	
	private String bcategory;//教材类别
	
	private String bname;//教材名称
	
	private String author;//作者
	
	private String press;//出版社
	
	private String price;//单价(元)
	
	private String pic;//教材封面
	
	private String intro;//教材介绍
	
	private Integer courseid;//所属课程id
	
	private String cname;//所属课程

	private Integer kucunsl;//库存数量
	
	private Integer tobeordered;//待订购数量
	
	private Integer ordered;//已订购数量
	
	
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public Integer getKucunsl() {
		return kucunsl;
	}

	public void setKucunsl(Integer kucunsl) {
		this.kucunsl = kucunsl;
	}

	public Integer getTobeordered() {
		return tobeordered;
	}

	public void setTobeordered(Integer tobeordered) {
		this.tobeordered = tobeordered;
	}

	public Integer getOrdered() {
		return ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}
	
	
	
	
	
	
}
