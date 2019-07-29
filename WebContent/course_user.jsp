<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
	<h1 style="position: relative;left:10%">学生课程表</h1>
	<table id="dg"></table>
	
	
	<script type="text/javascript">
	//数据表格的加载
	$('#dg').datagrid({
	    url:'${pageContext.request.contextPath}/course',
	    columns:[[
			{field:'className',title:'课程名',width:100},
			{field:'teachName',title:'教师名',width:100},
			{field:'term',title:'学期',width:100},
			{field:'subjectName',title:'专业名',width:100}
	    ]],
	    queryParams: {
			method:'showCourse_user',
			subjectId:'${student.subjectId}'
		},
		pagination:true,
		pageSize:3,
		pageList:[3,6,9,12,15]
	});
	
	</script>

</body>
</html>