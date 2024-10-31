<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
	
	<meta charset="utf-8">
	<title>教材管理系统</title>
	

	<%@ include file="../css.jsp" %>

</head>

<body>

<script>  


function  paging(pagenum){

	form2.action = form2.action+"?pagenum="+pagenum;
	form2.submit();

}
 
</script>

<form action="${pageurl }" method="post"  name="form2">

<input type="hidden"  name="classname" value="${classname }" />
                      
</form>


		<!-- topbar starts -->
		
	<%@ include file="../head.jsp" %>
	
	<!-- topbar ends -->
	
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			
			<%@ include file="../menu.jsp" %>
			
			<!--/span-->
			<!-- left menu ends -->
			
			
			
			<div id="content" class="span10">
			<!-- content starts -->
			

			
			
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i>${title }</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						</div>
					</div>
					<div class="box-content">
			
			 <form class="form-inline" action="${url }" method="post">
                <input type="text" class="form-control"  placeholder="请输入教师" name="name" value="${name }">
             &nbsp; 
             <input type="text" class="form-control"  placeholder="请输入教材编号" name="bnumber" value="${bnumber }">
             &nbsp; 
             <input type="text" class="form-control"  placeholder="请输入教材名称" name="bname" value="${bname }">
             &nbsp; 
				
                <button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>查询</button>
              
              </form>
						
						<table class="table table-striped table-bordered ">
						  <thead>
							  <tr>
							  	  <th>教师</th>
								  <th>教材课程</th>
								  <th>教材编号</th>
								  <th>教材名称</th>
								
								  <th>所属班级</th>
								  <th>领取数量</th>
								  <th>领取时间</th>
								  <th>操作</th>
							  </tr>
						  </thead>   
						  
						  <tbody>
						  <c:forEach items="${list}"  var="bean">
							<tr>
								<td>${bean.name }</td>
								<td>${bean.cname }</td>
								<td>${bean.bnumber }</td>
								<td>${bean.bname }</td>
							
								<td>${bean.classname }</td>
								<td>${bean.snumber }</td>
								<td>${bean.receivetime }</td>
								<td>
								
							
          		
				<a href="${url2 }show.do?id=${bean.id }" class="btn btn-warning btn-sm"><i class="fa fa-eye"></i>查看详情</a>
          		
          		
								</td>
							</tr>
							</c:forEach>
							
							
							<tr>
								<td colspan="15">${pagerinfo }</td>
								
							</tr>
							
							
							
							
							
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			
			</div><!--/row-->

			
			
			
			
			
    
					<!-- content ends -->
			</div>
			
			
		
		
		</div><!--/fluid-row-->
				
		<hr>

		<footer>
			
		</footer>
		
	</div><!--/.fluid-container-->

	<%@ include file="../footer.jsp" %>
	
		
</body>
</html>

