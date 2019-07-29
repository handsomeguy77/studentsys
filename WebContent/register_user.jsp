<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<form name="form2" action="${pageContext.request.contextPath}/user" method="post">
<input type="hidden" name="method" value="register">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;学生注册&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">学生性名：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input name="stuName"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">性别：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="radio" id="radio1" name="gender" value="男"/>
		<label for="radio1">男</label>
		<input type="radio" id="radio2" name="gender" value="女"/>
		<label for="radio2">女</label>
	</td>
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
	<td align="right" bgcolor="#FAFAF1" height="22">年龄：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input name="age"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">学号：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input name="stuNum"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">班级：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"><input name="teamName"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">专业：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="subjectId" style="height:20px;">
			<option value="-1">--请选择--</option>
			<c:forEach items="${subjects}" var="subject">
				<option value="${subject.id}">${subject.subjectName}</option>
			</c:forEach>
		</select>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<input type="submit" class="coolbg" value="保存" />
	<a href="login.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>