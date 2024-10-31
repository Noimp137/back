package com.util;

import java.util.List;

/**
 * Title: Pager
 * Description: 分页工具
 */
public class Pager {
	
	
	
	private int page = 1; // 当前页

	 public int totalPages = 0; // 总页数

	 private int pageRecorders;// 每页5条数据

	 private int totalRows = 0; // 总数据数

	 private int pageStartRow = 0;// 每页的起始数

	 private int pageEndRow = 0; // 每页显示数据的终止数

	 private boolean hasNextPage = false; // 是否有下一页

	 private boolean hasPreviousPage = false; // 是否有前一页

	 @SuppressWarnings("unchecked")
	private List list;
	 
	 @SuppressWarnings("unchecked")
	public Pager(List list, int pageRecorders) {
		 init(list, pageRecorders);// 通过对象集，记录总数划分
	}
	 
	 /** *//**
	  * 初始化list，并告之该list每页的记录数
	  * @param list
	  * @param pageRecorders
	  */
	  @SuppressWarnings("unchecked")
	public void init(List list, int pageRecorders) {
	  this.pageRecorders = pageRecorders;
	  this.list = list;
	  totalRows = list.size();
	  // it = list.iterator();
	  hasPreviousPage = false;
	  if ((totalRows % pageRecorders) == 0) {
	  totalPages = totalRows / pageRecorders;
	  } else {
	  totalPages = totalRows / pageRecorders + 1;
	  }

	  if (page >= totalPages) {
	  hasNextPage = false;
	  } else {
	  hasNextPage = true;
	  }

	  if (totalRows < pageRecorders) {
	  this.pageStartRow = 0;
	  this.pageEndRow = totalRows;
	  } else {
	  this.pageStartRow = 0;
	  this.pageEndRow = pageRecorders;
	  }
	  }
	  
	// 判断要不要分页
	  public boolean isNext() {
	  return list.size() > 5;
	  }

	  public void setHasPreviousPage(boolean hasPreviousPage) {
	  this.hasPreviousPage = hasPreviousPage;
	  }

	  public String toString(int temp) {
	  String str = Integer.toString(temp);
	  return str;
	  }

	  public void description() {

	  String description = "共有数据数:" + this.getTotalRows() +

	  "共有页数: " + this.getTotalPages() + 

	  "当前页数为:" + this.getPage() +

	  " 是否有前一页: " + this.isHasPreviousPage() +

	  " 是否有下一页:" + this.isHasNextPage() +

	  " 开始行数:" + this.getPageStartRow() +

	  " 终止行数:" + this.getPageEndRow();

	  System.out.println(description);
	  }

	  @SuppressWarnings("unchecked")
	public List getNextPage() {
	  page = page + 1;

	  disposePage();

	  System.out.println("用户凋用的是第" + page + "页");
	  this.description();
	  return getObjects(page);
	  }

	  /** *//**
	  * 处理分页
	  */
	  private void disposePage() {

	  if (page == 0) {
	  page = 1;
	  }

	  if ((page - 1) > 0) {
	  hasPreviousPage = true;
	  } else {
	  hasPreviousPage = false;
	  }

	  if (page >= totalPages) {
	  hasNextPage = false;
	  } else {
	  hasNextPage = true;
	  }
	  }

	  @SuppressWarnings("unchecked")
	public List getPreviousPage() {

	  page = page - 1;

	  if ((page - 1) > 0) {
	  hasPreviousPage = true;
	  } else {
	  hasPreviousPage = false;
	  }
	  if (page >= totalPages) {
	  hasNextPage = false;
	  } else {
	  hasNextPage = true;
	  }
	  this.description();
	  return getObjects(page);
	  }

	  /** *//**
	  * 获取第几页的内容
	  * 
	  * @param page
	  * @return
	  */
	  @SuppressWarnings("unchecked")
	public List getObjects(int page) {
	  if (page == 0)
	  this.setPage(1);
	  else
	  this.setPage(page);
	  this.disposePage();
	  if (page * pageRecorders < totalRows) {// 判断是否为最后一页
	  pageEndRow = page * pageRecorders;
	  pageStartRow = pageEndRow - pageRecorders;
	  } else {
	  pageEndRow = totalRows;
	  pageStartRow = pageRecorders * (totalPages - 1);
	  }

	  List objects = null;
	  if (!list.isEmpty()) {
	  objects = list.subList(pageStartRow, pageEndRow);
	  }
	  //this.description();
	  return objects;
	  }

	  @SuppressWarnings("unchecked")
	public List getFistPage() {
	  if (this.isNext()) {
	  return list.subList(0, pageRecorders);
	  } else {
	  return list;
	  }
	  }

	  public boolean isHasNextPage() {
	  return hasNextPage;
	  }


	  public void setHasNextPage(boolean hasNextPage) {
	  this.hasNextPage = hasNextPage;
	  }


	  @SuppressWarnings("unchecked")
	public List getList() {
	  return list;
	  }


	  @SuppressWarnings("unchecked")
	public void setList(List list) {
	  this.list = list;
	  }


	  public int getPage() {
	  return page;
	  }


	  public void setPage(int page) {
	  this.page = page;
	  }


	  public int getPageEndRow() {
	  return pageEndRow;
	  }


	  public void setPageEndRow(int pageEndRow) {
	  this.pageEndRow = pageEndRow;
	  }


	  public int getPageRecorders() {
	  return pageRecorders;
	  }


	  public void setPageRecorders(int pageRecorders) {
	  this.pageRecorders = pageRecorders;
	  }


	  public int getPageStartRow() {
	  return pageStartRow;
	  }


	  public void setPageStartRow(int pageStartRow) {
	  this.pageStartRow = pageStartRow;
	  }


	  public int getTotalPages() {
	  return totalPages;
	  }


	  public void setTotalPages(int totalPages) {
	  this.totalPages = totalPages;
	  }


	  public int getTotalRows() {
	  return totalRows;
	  }


	  public void setTotalRows(int totalRows) {
	  this.totalRows = totalRows;
	  }


	  public boolean isHasPreviousPage() {
	  return hasPreviousPage;
	  }
	
	
	//标准列表分页，用于后台支持条件查询
	public static String getPagerNormal(int total, int pagesize, int pagenum,String pageurl,String info) {
		int count = total / pagesize;
		if (total % pagesize > 0) {
			count++;
		}
		if(pageurl.indexOf("?")>-1){
			pageurl = pageurl + "&";
		}else{
			pageurl = pageurl + "?";
		}
		StringBuffer buf = new StringBuffer();
		buf.append(info+"&nbsp;&nbsp;");
		buf.append(pagenum+"/"+ count +"&nbsp;&nbsp;");
		if (pagenum == 1) {
			buf.append("<SPAN style='color:#CCCCCC'>【首页】</SPAN><SPAN style='color:#CCCCCC'>【上一页】</SPAN>&nbsp;&nbsp;");
		} else {
			buf.append("【<a href='javascript:void(0);'  onclick='paging(1)' >首页</a>】【<a href='javascript:void(0);'  onclick='paging("+(pagenum - 1)+")'>上一页</a>】");
		}
		int bound1 = ((pagenum - 2) <= 0) ? 1 : (pagenum - 2);
		int bound2 = ((pagenum + 2) >= count) ? count : (pagenum + 2);
		for (int i = bound1; i <= bound2; i++) {
			if (i == pagenum) {
				buf.append("<SPAN style='color:#FF0000'>" + i
						+ "</SPAN>&nbsp;&nbsp;");
			} else {
				buf.append("<a href='javascript:void(0);'  onclick='paging("+(i)+")'>" + i
						+ "</a>&nbsp;&nbsp;");
			}
		}
		if (bound2 < count) {
			buf.append("<SPAN>...</SPAN>");
		}
		if (pagenum == count||count==0) {
			buf.append("<SPAN style='color:#CCCCCC'>【下一页】</SPAN><SPAN style='color:#CCCCCC'>【尾页】</SPAN>");
		} else {
			buf.append("【<a href='javascript:void(0);' onclick='paging("+(pagenum + 1)+")'>下一页</a>】【<a href='javascript:void(0);' onclick='paging("+(count)+")'>尾页</a>】");
		}
		return buf.toString();
	}
}
