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


<script language="javascript" type="text/javascript">

function checkform()
{
	 
	

	if (document.getElementById('jobnoid').value=="")
	{
		alert("工号不能为空");
		return false;
	}
	
	if (document.getElementById('nameid').value=="")
	{
		alert("姓名不能为空");
		return false;
	}
	
	if (document.getElementById('ageid').value=="")
	{
		alert("年龄不能为空");
		return false;
	}
	
	if (document.getElementById('phoneid').value=="")
	{
		alert("手机号码名不能为空");
		return false;
	}
	
	//验证手机格式
	valid=/^0?1[3,4,5,6,7,8,9][0,1,2,3,4,5,6,7,8,9]\d{8}$/;  
	if(!valid.test(document.getElementById('phoneid').value)){
		alert("请输入正确的手机号，如13000000000");
		return false;
	}
	 
	return true;
	
}


</script>

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
								<label class="control-label" for="focusedInput">姓名</label>
								<div class="controls">
								  <input  type='text' value="${bean.name }"  name='name' id='nameid' class="input-xlarge focused"/>
								</div>
							  </div>
							  
							  
							 
							  
							  
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">性别</label>
								<div class="controls">
								  <select name="sex" class="input-xlarge focused">
								 	<option value="男" <c:if test="${bean.sex=='男' }">selected</c:if> >男</option>
								 	<option value="女" <c:if test="${bean.sex=='女' }">selected</c:if> >女</option>
								  </select>
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">年龄</label>
								<div class="controls">
								  <input  type='text' value="${bean.age }"  name='age' id='ageid' class="input-xlarge focused"/>
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">手机号码</label>
								<div class="controls">
								  <input  type='text' value="${bean.phone }"  name='phone' id='phoneid' class="input-xlarge focused"/>
								</div>
							  </div>

							  
							  
							  <div class="form-actions">
								<button class="btn btn-success btn-sm"  type="submit">&nbsp;&nbsp;提&nbsp;&nbsp;交&nbsp;&nbsp;</button>
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;              

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

