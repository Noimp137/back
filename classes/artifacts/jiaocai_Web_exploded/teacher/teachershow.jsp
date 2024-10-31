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
						<h2><i class="icon-edit"></i>${title }</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" action="${url }" method="post" onsubmit="return checkform()">
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="focusedInput">工号</label>
								<div class="controls">
								  <input  type='text' readonly="readonly" value="${bean.jobno }"  name='name' id='nameid' class="input-xlarge focused"/>
								</div>
							  </div>
							  
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">姓名</label>
								<div class="controls">
								  <input  type='text' readonly="readonly" value="${bean.name }"  name='name' id='nameid' class="input-xlarge focused"/>
								</div>
							  </div>
							  
							  
							 
							  
							  
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">性别</label>
								<div class="controls">
								<input  type='text' readonly="readonly" value="${bean.sex }"  name='name' id='nameid' class="input-xlarge focused"/>
							
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">年龄</label>
								<div class="controls">
								  <input  type='text' value="${bean.age }" readonly="readonly"  name='age' id='ageid' class="input-xlarge focused"/>
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">手机号码</label>
								<div class="controls">
								  <input  type='text' value="${bean.phone }" readonly="readonly"  name='phone' id='phoneid' class="input-xlarge focused"/>
								</div>
							  </div>
							  
							  <div class="form-actions">
							            

  			<button class="btn btn-primary btn-sm"  type="button"  onclick="javascript:history.go(-1);">&nbsp;&nbsp;返&nbsp;&nbsp;回&nbsp;&nbsp;</button>
							  </div>
							</fieldset>
						  </form>
					
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
			
			
    
					<!-- content ends -->
			</div><!--/#content.span10-->
			
			
		
		
		</div><!--/fluid-row-->
				
		<hr>

		<footer>
			
		</footer>
		
	</div><!--/.fluid-container-->

	<%@ include file="../footer.jsp" %>
	
		
</body>
</html>

