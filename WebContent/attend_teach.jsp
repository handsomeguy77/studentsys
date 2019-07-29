<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考勤管理(教师)</title>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
	
	<script type="text/javascript">
		$(function(){
			$("#sel_course").val("${courseValue}");
		})
	</script>
	
	<div>
		<h1 style="position: relative;left:10%">教师考勤</h1>
		<div>
			<form id="fd" action="${pageContext.request.contextPath}/attend" method="post">
				<input type="hidden" name="method" value="showTeam"/>
				<label>请选择课程:</label>
				<select id="sel_course" name="courseName">
					<option value="">--请选择--</option>
					<c:forEach items="${courses}" var="course">
						<option value="${course.className}">${course.className}</option>
					</c:forEach>
				</select>
				
				<label style="margin-left: 10px;">请选择班级:</label>
				<select id="sel_team" name="teamName">
					<c:forEach items="${teamlist}" var="team">
						<option value="${team.teamName}">${team.teamName}</option>
					</c:forEach>
				</select>
				
				<a style="margin-left: 10px;" id="btn" href="#" onclick="start($('#time').text(),$('#time').text())" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">开始考勤</a>
			</form>
			
			<div style="width:300px;position: relative;left:150px;top:50px;">
				<h1>倒计时开始</h1>
				<div style="border: 1px solid red;width:200px;"><h1 style="text-align: center; color: red" id="time">30</h1></div>
				<div id="kqm" style="width: 200px;margin-top: 20px;display: none;">
					<h1>请输入考勤码</h1>
					<div><h1 id="num" style="text-align: center;color: red;" id="time"></h1></div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$("#sel_course").change(function(){
			$("#fd").submit();
		})
		
		//倒计时
		function start(startTime,time){
			//开始时，先提交表单，保存 课程名 和 班级名
			//刚开始的时候，发ajax获取考勤码
			if(startTime==time){
				$.ajax({
					url:"${pageContext.request.contextPath}/attend?method=getKqm",
					type:"post",
					data:$("#fd").serialize(),
					success:function(data){
						$("#num").text(data);
						$("#kqm").css("display","block");
					}
				})
			}
			if(time===0){
				//时间到销毁考勤码
				$.ajax({
					url:"${pageContext.request.contextPath}/attend?method=endKq",
					type:"post",
					success:function(data){
						$('#time').text(startTime);
						$("#kqm").css("display","none");
					}
				})
				//1秒之后跳转到考勤信息页面
				setTimeout(function(){
					window.location="${pageContext.request.contextPath}/attend_teach_show.jsp";
				},1000)
				return ;
			}else{
				$('#time').text(time);
				setTimeout(function(){
					start(startTime,time-1);
				},1000)
			}
		}
	</script>
</body>
</html>