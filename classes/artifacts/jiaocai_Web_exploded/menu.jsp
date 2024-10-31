<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="nav-header hidden-tablet">系统主菜单</li>

						<c:if test="${manage.role==1}">

						<li>
						<a class="ajax-link" href="classinfo/classinfolist.do"><i class="icon-leaf"></i><span class="hidden-tablet">班级信息管理</span></a>
						</li>
						
						<li>
						<a class="ajax-link" href="course/courselist.do"><i class="icon-bookmark"></i><span class="hidden-tablet">课程信息管理</span></a>
						</li>
			
						
						<li>
						<a class="ajax-link" href="teacher/teacherlist.do"><i class="icon-list"></i><span class="hidden-tablet">教师信息管理</span></a>
						</li>
						
						
						<li>
						<a class="ajax-link" href="book/booklist.do"><i class="icon-leaf"></i><span class="hidden-tablet">教材信息管理</span></a>
						</li>
						
						<li>
						<a class="ajax-link" href="selectedlog/selectedloglist2.do"><i class="icon-leaf"></i><span class="hidden-tablet">待审核选定教材</span></a>
						</li>
						
						
						<li>
						<a class="ajax-link" href="selectedlog/selectedloglist3.do"><i class="icon-leaf"></i><span class="hidden-tablet">选定教材查询</span></a>
						</li>
						
						
						<li>
						<a class="ajax-link" href="book/booklist3.do"><i class="icon-leaf"></i><span class="hidden-tablet">订购教材</span></a>
						</li>
						
						
						<li>
						<a class="ajax-link" href="orders/orderslist.do"><i class="icon-leaf"></i><span class="hidden-tablet">教材订购管理</span></a>
						</li>
		
						<li>
						<a class="ajax-link" href="orders/orderslist2.do"><i class="icon-leaf"></i><span class="hidden-tablet">教材入库管理</span></a>
						</li>
						
						<li>
						<a class="ajax-link" href="book/booklist4.do"><i class="icon-leaf"></i><span class="hidden-tablet">教材库存管理</span></a>
						</li>
						
						
						<li>
						<a class="ajax-link" href="selectedlog/selectedloglist4.do"><i class="icon-leaf"></i><span class="hidden-tablet">待领取教材</span></a>
						</li>
						
						<li>
						<a class="ajax-link" href="selectedlog/selectedloglist5.do"><i class="icon-leaf"></i><span class="hidden-tablet">领取记录查询</span></a>
						</li>
						
						

						</c:if>

						<c:if test="${manage.role==2}">

						<li>
						<a class="ajax-link" href="book/booklist2.do"><i class="icon-leaf"></i><span class="hidden-tablet">选定任课教材</span></a>
						</li>
						
						<li>
						<a class="ajax-link" href="selectedlog/selectedloglist.do"><i class="icon-leaf"></i><span class="hidden-tablet">我的选定教材</span></a>
						</li>
						
						<li>
						<a class="ajax-link" href="selectedlog/selectedloglist6.do"><i class="icon-leaf"></i><span class="hidden-tablet">领取记录查询</span></a>
						</li>

						</c:if>
						
					</ul>
					
					
					
					
				</div><!--/.well -->
</div>

