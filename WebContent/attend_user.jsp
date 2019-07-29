<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考勤/考勤信息(学生)</title>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
	<!-- session域中的考勤码不为空的情况，说明老师刚刚进行了签到 -->
	<c:if test="${not empty num}">
		<div style="position: relative;left:30%">
			<h1>请输入验证码</h1>
			<div>
				<input id="inp" style="height: 50px;font-size:30px;width:200px;" type="text" />
				<a style="margin-left: 10px;" onclick="cli()" id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
			</div>
			<div id="dv" style="display: none">
				<h1>考勤信息</h1>
			</div>
		</div>
		<table id="dg"></table>
	</c:if>
	<c:if test="${empty num}">
		<h1 style="position: relative;left:30%">您暂无课程需要进行考勤</h1>
	</c:if>
	
	
	<script type="text/javascript">
		function cli(){
			if($("#inp").val()=="${num}"){
				$("#dv").css("display","block");
				//数据表格的加载
				$('#dg').datagrid({
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
						method:'stuSign',
						stuNum:'${student.stuNum}'
					},
					pagination:true,
					pageSize:3,
					pageList:[3,6,9,12,15]
				});
				
			}else{
				alert("考勤码不正确,请重新输入");
			}
		}
	</script>
</body>
</html>