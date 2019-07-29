<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>布置作业(教师模块)</title>
</head>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<style>
	#dv{ 
		background:url('skin/images/task/task_teach_add.jpg') no-repeat;
		background-size:100% 100%;
		width:1300px;
		height:600px;
	}
</style>
<body> 

	<c:if test="${teacher.courseName=='-1'}">
		<h1>您是辅导员，没有相关的教学课程!!!</h1>
	</c:if>
	<c:if test="${teacher.courseName!='-1'}">
		<div id="dv">
			<div style="position: relative;top:20px;left:200px;">
				<div style="position: absolute;left:22px;"><h1 style="font-size:28px;">添加课程</h1></div>
				<div style="position: absolute;top:70px;">
					<form id="fd" method="post" action="${pageContext.request.contextPath}/task">
						<input type="hidden" name="method" value="add_task_save"/>
						<input type="hidden" name="teachId" value="${teacher.id }"/>
						 <div>
							<label>课程名:</label>&nbsp;
							<select id="sel" class="easyui-combobox" name="className">
								<!-- 该教师没有的课程就不列出来 -->
								<c:forEach items="${courses}" var="course">
									<c:if test="${teacher.courseName.indexOf(course.className)!=-1}">
										<option value="${course.id}">${course.className}</option>
									</c:if>
								</c:forEach>
								
							</select>
					    </div>
					    <div>
							<label>作业名:</label>&nbsp;
							<input class="easyui-textbox" type="text" name="taskName"  />
					    </div>
					    <div>
					    	<label>开始日期:</label>
					    	<input class="easyui-textbox" type="date" name="startDate"/>
					    </div>
					    <div>
					    	<label>结束日期:</label>
					    	<input class="easyui-textbox" type="date" name="stopDate"/>
					    </div>
					    <div style="margin-left:140px; margin-top: 5px;">
					    	<a class="easyui-linkbutton" href="javascript:$('#fd')[0].submit();" id="btn">确认</a>
					    </div>
					</form>
				</div>
			</div>
			
		</div>
	</c:if>
	
</body>
</html>