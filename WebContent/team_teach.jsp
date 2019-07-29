<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>班级信息</title>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
	<h1 style="position: relative;left:20%">学生信息</h1>
	
	<c:if test="${teacher.teamName!='-1'}">
		<table id="dg"></table>
	</c:if>
	
	<c:if test="${teacher.teamName=='-1'}">
		<table border="1" cellspacing="0" width="50%" height="150">
				<tr>
					<th >学生姓名</th>
					<th >性别</th>
					<th >用户名</th>
					<th >年龄</th>
					<th >学号</th>
					<th >班级</th>
					<th >专业</th>
				</tr>
				<tr><td colspan="7">您不是辅导员，暂无班级数据</td></tr>
		</table>
	</c:if>
	
	<script type="text/javascript">
	//数据表格的加载
	$('#dg').datagrid({
	    url:'${pageContext.request.contextPath}/teach',
	    columns:[[
			{field:'stuName',title:'学生姓名',width:100},
			{field:'gender',title:'性别',width:100},
			{field:'username',title:'用户名',width:100},
			{field:'age',title:'年龄',width:100},
			{field:'stuNum',title:'学号',width:100},
			{field:'teamName',title:'班级',width:100},
			{field:'subjectName',title:'专业',width:100}
	    ]],
	    queryParams: {
			method:'findByTeam',
			teamName:'${teacher.teamName}'
		},
		pagination:true,
		pageSize:5,
		pageList:[5,10,15,20]
	});
	
	</script>

</body>
</html>