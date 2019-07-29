<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考勤信息(教师)</title>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
	<h1 style="position: relative;left:30%">考勤信息</h1>
	<table id="dg"></table>
	
	<div id="dv" style="display: none;">
		<h1 style="position: relative;left:25%">这一次考勤的详细信息</h1>
		<table id="dg2"></table>
	</div>
	<script type="text/javascript">
		//数据表格的加载
		$('#dg').datagrid({
		    url:'${pageContext.request.contextPath}/attend',
		    columns:[[
				{field:'className',title:'班级名',width:100},
				{field:'courseName',title:'课程名',width:100},
				{field:'teachName',title:'教师名',width:100},
				{field:'missNum',title:'缺勤人数',width:100},
				{field:'attendNum',title:'出勤人数',width:100},
				{field:'sumNum',title:'总人数',width:100},
				{field:'date',title:'签到时间',width:150}
		    ]],
		    queryParams: {
				method:'showAttend',
				teachName:'${teacher.teachName}'
			},
			pagination:true,
			pageSize:3,
			pageList:[3,6,9,12,15],
			onClickRow:function(rowIndex,rowData){
				$("#dv").css("display","block");
				$('#dg2').datagrid({
				    url:'${pageContext.request.contextPath}/attend',
				    columns:[[
				    	{field:'stuName',title:'学生姓名',width:100},
						{field:'stuNum',title:'学号',width:100},
						{field:'className',title:'班级',width:100},
						{field:'courseName',title:'课程名',width:100},
						{field:'teachName',title:'教师名',width:100},
						{field:'startTime',title:'开始时间',width:150},
						{field:'status',title:'签到状态',width:100},
						{field:'date',title:'签到时间',width:150}
				    ]],
				    queryParams: {
						method:'showAttend_xq',
						attendId:rowData.id
					},
					pagination:true,
					pageSize:5,
					pageList:[5,10,15,20],
				});
			}
		});
	
	</script>
</body>
</html>