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

<input type="hidden"  name="username" value="${username }" />
                      
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
               
                  <input type="text" class="form-control"  placeholder="请输入用户名" name="username" value="${username }">
             &nbsp; 

                <button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>查询</button>
    
              </form>
						
						<table class="table table-striped table-bordered ">
						  <thead>
							  <tr>
								  <th>用户名</th>
								  <th>密码</th>
								  <th>用户角色</th>
								  <th>姓名</th>
								  <th>添加时间</th>
								
							  </tr>
						  </thead>   
						  
						  <tbody>
						  <c:forEach items="${list}"  var="bean">
							<tr>
								<td>${bean.username }</td>
								<td>${bean.password }</td>
								<td>
								<c:if test="${bean.role==2 }">教师</c:if>
								<c:if test="${bean.role==3 }">学生</c:if>
								</td>
								<td>${bean.name }</td>
								<td>${bean.createtime }</td>
								
							</tr>
							</c:forEach>
							
							
							<tr>
								<td colspan="10">${pagerinfo }</td>
								
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

