<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>我的信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>


<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;我的信息&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">教师名：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">${teacher.teachName}</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">用户名：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">${teacher.username}</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">密码：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">${teacher.password  }</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">职位：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">${teacher.zw  }</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">所教课程：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<c:if test="${teacher.courseName!='-1'}"><label>${teacher.courseName}</label></c:if>
		<c:if test="${teacher.courseName=='-1'}"><label>暂无</label></c:if>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">管理班级：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<c:if test="${teacher.teamName!='-1'}"><label>${teacher.teamName}</label></c:if>
		<c:if test="${teacher.teamName=='-1'}"><label>暂无</label></c:if>
	</td>
</tr>


</table>

</form>
  

</body>
</html>