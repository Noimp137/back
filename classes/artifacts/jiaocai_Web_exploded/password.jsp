<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	
	<meta charset="utf-8">
	<title>教材管理系统</title>
	

	<%@ include file="css.jsp" %>



<script language="javascript" type="text/javascript">

function checkform()
{
	 
	

	if (document.getElementById('password1id').value=="")
	{
		alert("原密码不能为空");
		return false;
	}
	if (document.getElementById('password2id').value=="")
	{
		alert("新密码不能为空");
		return false;
	}
	
	if (document.getElementById('password2id').value.length<6)
	{
		alert("新密码长度必须大于6位");
		return false;
	}
	if (document.getElementById('password2id').value != document.getElementById('password3id').value)
	{
		alert("新密码与新密码确认不一致");
		return false;
	}	 
	return true;
	
}


</script>


</head>

<body>
		<!-- topbar starts -->
		
	<%@ include file="head.jsp" %>
	
	<!-- topbar ends -->
	
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			
			<%@ include file="menu.jsp" %>
			
			<!--/span-->
			<!-- left menu ends -->
			
			
			
			<div id="content" class="span10">
			<!-- content starts -->
			


			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i>个人密码</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal"  action="password2.do" method="post"  onsubmit="return checkform()">
							<fieldset>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">原密码</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='password' placeholder='请输入原密码' name="password1" id="password1id"  >
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">新密码</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='password' placeholder='请输入新密码' name="password2" id="password2id"  >
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">确认新密码</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='password' placeholder='请输入确认新密码' name="password3" id="password3id"  >
								</div>
							  </div>
					
							  <div class="form-actions">
								 <button class="btn btn-primary"  type="submit">&nbsp;&nbsp;提&nbsp;&nbsp;交&nbsp;&nbsp;</button>
  									&nbsp;&nbsp;&nbsp;&nbsp;
  									<button class="btn btn-warning"  type="reset">&nbsp;&nbsp;重&nbsp;&nbsp;置&nbsp;&nbsp;</button>
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

	<%@ include file="footer.jsp" %>
	
		
</body>
</html>

