<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>课程管理_课程添加</title>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
	<c:if test="${teacher.courseName=='-1'}">
		<h1>您是辅导员，没有相关的教学课程!!!</h1>
	</c:if>
	<c:if test="${teacher.courseName!='-1'}">
		<div class="panel-body" style="background:url('skin/images/teach/teach_course_add.jpg');background-size:100%;height:600px;">
			<div style="position: relative;top:10%">
				<h1 style="font-size: 28px;position:relative;left:8%;">添加课程</h1>
				<div style="margin-left:30px;">
					<form id="fd" method="post" action="${pageContext.request.contextPath}/teach">
						<input type="hidden" name="method" value="add_course"/>
						<input type="hidden" name="teacherId" value="${teacher.id }"/>
						<input type="hidden" name="teacheName" value="${teacher.teachName }"/>
						 <div>
							<label>课程名:</label>
							<select id="sel" class="easyui-combobox" name="courseName">
								<c:forEach items="${courses}" var="course">
									<!-- 该教师已有的课程就不列出来 -->
									<c:if test="${teacher.courseName.indexOf(course.className)==-1}">
										<option value="${course.className}">${course.className}</option>
									</c:if>
								</c:forEach>
								
							</select>
					    </div>
					    
					    <div style="position: absolute; ;left:170px;">
					    	<a class="easyui-linkbutton" href="javascript:$('#fd')[0].submit();" id="btn">确认</a>
					    </div>
					</form>
				</div>
			</div>
		</div>
	</c:if>

</body>
</html>