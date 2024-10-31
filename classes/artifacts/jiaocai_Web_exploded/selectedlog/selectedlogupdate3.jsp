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
								<label class="control-label" for="focusedInput">提交教师</label>
								<div class="controls">
								 <input class="input-xlarge focused" type='text' value="${bean.name }" readonly="readonly"   />
							
								
								</div>
							  </div>
							  
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">所属课程</label>
								<div class="controls">
								 <input class="input-xlarge focused" type='text' value="${bean.cname }" readonly="readonly"   />
							
								
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">教材编号</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.bnumber }" readonly="readonly"   />
								</div>
							  </div>
							  
		
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">教材名称</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.bname }" readonly="readonly"   />
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">所属学院</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.college }" readonly="readonly"   />
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">所属专业</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.major }" readonly="readonly"   />
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">所属届</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.sessioninfo }" readonly="readonly"   />
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">所属班级</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.classname }" readonly="readonly"   />
								</div>
							  </div>
							  
							  
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">教材选定数量</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.snumber }"  readonly="readonly" />
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">选定理由</label>
								<div class="controls">
								  <textarea class="input-xlarge textarea"  readonly="readonly" name="result"  style="width: 500px; height: 150px">${bean.result }</textarea>
								</div>
							  </div>
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">提交时间</label>
								<div class="controls">
								  <input class="input-xlarge focused" type='text' value="${bean.ctime }"  readonly="readonly" />
								</div>
							  </div>
							  
							  
							 <div class="control-group">
								<label class="control-label" for="focusedInput">审核结果</label>
								<div class="controls">
								  <select name="status" class="input-xlarge focused">
								  
								  <option value="审核通过">审核通过</option>
								  <option value="审核不通过">审核不通过</option>
								 
								  </select>
								</div>
							  </div>
							  
							  
							  <div class="control-group">
								<label class="control-label" for="focusedInput">审核反馈</label>
								<div class="controls">
								  <textarea class="input-xlarge textarea"   name="feedback"  style="width: 500px; height: 150px">${bean.feedback }</textarea>
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

