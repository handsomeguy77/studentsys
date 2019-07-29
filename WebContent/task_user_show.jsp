<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>作业信息(学生)</title>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
	<h1 style="position: relative;left:10%">作业信息</h1>
	<table id="dg"></table>
	
	<script type="text/javascript">
		//数据表格的加载
		$('#dg').datagrid({
		    url:'${pageContext.request.contextPath}/user',
		    columns:[[
				{field:'taskName',title:'作业名',width:100},
				{field:'courseName',title:'课程名',width:100},
				{field:'teachName',title:'课程名',width:100},
				{field:'date',title:'发布日期',width:100},
				{field:'startDate',title:'开始日期',width:100},
				{field:'stopDate',title:'结束日期',width:100}
		    ]],
		    queryParams: {
				method:'showtask',
				userId:'${student.id}'
			},
			pagination:true,
			pageSize:3,
			pageList:[3,6,9,12,15]
		});
	
	</script>
</body>
</html>