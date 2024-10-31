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
	 
	

	
	
	if (document.getElementById('bcategoryid').value=="")
	{
		alert("教材类别不能为空");
		return false;
	}
	
	if (document.getElementById('bnameid').value=="")
	{
		alert("教材名称不能为空");
		return false;
	}
	
	
	
	if (document.getElementById('uploadfileid').value!="")
	{
		var FileName = document.getElementById('uploadfileid').value;
                        	
        var extension=new String (FileName.substring(FileName.lastIndexOf(".")+1,FileName.length));

							
        if(!(extension=="jpg"  || extension=="png")){
			
			alert("请上传jpg或者png格式教材封面");
			
			return false;
	
		}
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
						<form class="form-horizontal" action="${url }" method="post" onsubmit="return checkform()" enctype="multipart/form-data" >
							<fieldset>
							
							
							<div class="control-group">
								<label class="control-label" for="focusedInput">所属课程</label>
								<div class="controls">
								  <select name="courseid" class="input-xlarge focused">
								  <c:forEach items="${courselist}" var="course">
								   <option value="${course.id }" <c:if test="${bean.courseid==course.id }">selected</c:if> >${course.cname }</option>
								  </c:forEach>
								  </select>
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">教材编号</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.bnumber }" readonly="readonly"  name='bnumber' id='bnumberid' />
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">教材类别</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.bcategory }"  name='bcategory' id='bcategoryid' />
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">教材名称</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.bname }"  name='bname' id='bnameid' />
								</div>
							  </div>
							  
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">教材封面</label>
								<div class="controls">
								   <img src="<%=basePath %>/${bean.pic }" alt="img" width="80" height="80">
								</div>
							  </div>
							  
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">重新上传封面</label>
								<div class="controls">
								  <input class="input-xlarge focused"  type="file"  name="uploadfile" id="uploadfileid"  />
								</div>
							  </div>
							  
							  
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">作者</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.author }"  name='author' id='authorid' />
								</div>
							  </div>
							  
							  
							
							  
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">出版社</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.press }"  name='press' id='pressid' />
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">单价(元)</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.price }"  name='price' id='priceid' />
								</div>
							  </div>
							  
							 
							  
							 
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">教材介绍</label>
								<div class="controls">
								  <textarea class="input-xlarge textarea"  name="intro"  style="width: 500px; height: 150px">${bean.intro }</textarea>
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

