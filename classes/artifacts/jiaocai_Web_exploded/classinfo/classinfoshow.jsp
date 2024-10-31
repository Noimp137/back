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
								<label class="control-label" for="focusedInput">学院</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' readonly="readonly" value="${bean.college }"  name='college' id='collegeid' />
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">专业</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text'  readonly="readonly" value="${bean.major }"  name='major' id='majorid' />
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">届</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text'  readonly="readonly" value="${bean.sessioninfo }"  name='sessioninfo' id='sessioninfoid' />
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">班级名</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text'  readonly="readonly" value="${bean.classname }" name='classname' id='classnameid' />
								</div>
							  </div>
							  
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">学生人数</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' readonly="readonly" value="${bean.stunumber }"  name='stunumber' id='stunumberid' />
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

