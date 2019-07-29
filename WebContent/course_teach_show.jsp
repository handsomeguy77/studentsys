<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>课程信息</title>
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
		<h1 style="position: relative;left:10%">课程信息</h1>
		<table id="dg"></table>
	</c:if>
	
	

	<script type="text/javascript">
		//数据表格的加载
		$('#dg').datagrid({
		    url:'${pageContext.request.contextPath}/teach',
		    columns:[[
				{field:'className',title:'课程名',width:100},
				{field:'teachName',title:'教师名',width:100},
				{field:'term',title:'开课学期',width:100},
				{field:'subjectName',title:'专业名',width:100}
		    ]],
		    queryParams: {
				method:'showCourse_teach',
				teachName:'${teacher.teachName}'
			},
			pagination:true,
			pageSize:3,
			pageList:[3,6,9,12,15]
		});
	
	</script>
</body>
</html>