<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>编辑</title>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
<script type="text/javascript">
	
	$(function(){
		$("#sel").combobox('setValue',"${student.subjectId}");
	})
	
</script>

<div class="panel-body" style="background:url('skin/images/user/edit_user.jpg');background-size:100%;height:600px;">
	<div style="position: relative;top:10%">
		<h1 style="font-size: 28px;position:relative;left:8%;">信息修改</h1>
		<div style="margin-left:30px;">
			<form id="fd" method="post" action="${pageContext.request.contextPath}/user">
				<input name="method" value="edit_sure" type="hidden"/>
				<input name="id" value="${student.id}" type="hidden"/>
			    <div>
					<label>姓名:</label>&nbsp;
					<input class="easyui-textbox" type="text" name="stuName" value="${student.stuName}" />
			    </div>
			    <div>
					<label>性别:</label>&nbsp;
					<input class="easyui-textbox"  type="text" name="gender" value="${student.gender }"/>
			    </div>
			    <div>
					<label>用户名:</label>
					<input class="easyui-textbox"  type="text" name="username" value="${student.username}"/>
			    </div>
			    <div>
					<label>年龄:</label>&nbsp;
					<input class="easyui-textbox"  type="text" name="age" value="${student.age}"/>
			    </div>
			    <div>
					<label>学号:</label>&nbsp;
					<input class="easyui-textbox"  type="text" name="stuNum" value="${student.stuNum }"/>
			    </div>
			    <div>
					<label>班级:</label>&nbsp;
					<input class="easyui-textbox"  type="text" name="teamName" value="${student.teamName }"/>
			    </div>
			    <div>
					<label>专业:</label>&nbsp;
					<select id="sel" class="easyui-combobox" name="subjectId">
						<c:forEach items="${subjects}" var="subject">
							<option value="${subject.id}">${subject.subjectName}</option>
						</c:forEach>
					</select>
			    </div>
			    
			    <div style="position: absolute; ;left:100px;">
			    	<a class="easyui-linkbutton" href="javascript:$('#fd')[0].submit();" id="btn" style="margin-right:20px">确认</a>
			    	<a class="easyui-linkbutton" href="javascript:$('#fd')[0].reset();">重置</a>
			    </div>
			</form>
		</div>
	</div>
</div>

</body>
</html>