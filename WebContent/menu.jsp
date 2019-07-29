<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>menu</title>
<link rel="stylesheet" href="skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="skin/css/menu.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language='javascript'>
	var curopenItem = '1';
</script>
<script language="javascript" type="text/javascript"
	src="skin/js/frame/menu.js"></script>
<script type="text/javascript" src="js/jquery-1.8.1.js"></script>
<base target="main" />
</head>
<body target="main">
	<table  width='99%' height="100%" border='0' cellspacing='0' cellpadding='0' >
        <tr><td style='padding-left:3px;padding-top:8px' valign='top' id="menuss">

<dl class='bitem'>
<dt onclick=showHide("items1_1")><b>考勤管理</b></dt>

<dd style='display:block' class='sitem' id=items1_1>
<ul class='sitemu' id=0>
<li><a href='${pageContext.request.contextPath}/attend_user.jsp' target='main'>考勤/考勤信息</a> </li>
</ul>
</dd>
</dl>

<dl class='bitem'><dt onclick=showHide('items2_1')><b>作业管理</b></dt><dd style='display:none' class='sitem' id=items2_1><ul class='sitemu' id=1>
<li><a href='${pageContext.request.contextPath}/task_user_show.jsp' target='main'>作业信息</a> </li>
<li><a href='message-give.jsp' target='main'>提交作业</a> </li>
</ul></dd></dl>

<dl class='bitem'><dt onclick=showHide("items3_1")><b>课程管理</b></dt><dd style='display:none' class='sitem' id=items3_1><ul class='sitemu' id=2>
<li><a href='${pageContext.request.contextPath}/course?method=show' target='main'>所有专业课程信息</a> </li>
<li><a href='${pageContext.request.contextPath}/course_user.jsp' target='main'>课程信息</a> </li>
</ul></dd></dl>

     
		</td>
		</tr>
	</table>
</body>
</html>