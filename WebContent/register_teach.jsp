<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加用户</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">
	$(function(){
		if("${msg}"!=""){
			$.messager.show({
				title:'提示',
				msg:"${msg}",
				timeout:5000,
				showType:'slide'
			});
		}
	})
</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:用户管理>>用户注册
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" action="${pageContext.request.contextPath}/teach" method="post">
<input type="hidden" name="method" value="register">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px" id="table">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;教师注册&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">教师性名：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input name="teachName"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">用户名：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input name="username"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">密码：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input name="password"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">职位：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="zw" id="sel" style="height:20px;" onclick="cli()">
			<option value="教师">教师</option>
			<option value="辅导员">辅导员</option>
		</select>
	</td>
</tr>

<tr>
	<td align="right" bgcolor="#FAFAF1" height="22" id="td">
		<label id="label_course">选择课程:</label>
		<label id="label_team" style="display: none">选择班级:</label>
	</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="courseName" style="height:20px;" id="sel_course">
			<option value="-1">--请输入--</option>
			<c:forEach items="${courses}" var="course">
				<option value="${course.className }">${course.className}</option>
			</c:forEach>
		</select>
		<select name="teamName" style="height:20px;display: none" id="sel_team">
			<option value="-1">--请输入--</option>
			<c:forEach items="${students}" var="student">
				<option value="${student.teamName }">${student.teamName }</option>
			</c:forEach>
		</select>
	</td>
</tr>

<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<input type="submit" class="coolbg" value="保存" />
	<a href="login_teach.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  
  <script type="text/javascript">
  	function cli(){
  		//每次点击都会选中请输入这个option
  		$("#sel_course").val("-1");
  		$("#sel_team").val("-1");
  		if($("#sel").val()=='教师'){
  			$("#label_course").css('display','block');
  			$("#sel_course").css('display','block');
  			$("#sel_team").css('display','none');
  			$("#label_team").css('display','none');
  		}else if($("#sel").val()=='辅导员'){
  			$("#label_course").css('display','none');
  			$("#sel_course").css('display','none');
  			$("#sel_team").css('display','block');
  			$("#label_team").css('display','block');
  		}
  	}
  	
  </script>

</body>
</html>