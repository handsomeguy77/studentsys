<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>更改密码(学生)</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>



<div class="panel-body" style="background:url('skin/images/user/edit_user_pdw.jpg');background-size:100%;height:600px;">

	<h1 style="text-align: center;font-size: 28px;">密码修改</h1>
	<div style="width:100%;text-align:center">
		<form id="fd" method="post">
			<input name="method" value="edit_pwd" type="hidden"/>
			<input name="username" value="${student.username }" type="hidden"/>
		    <div>
				<label>旧密码:</label>&nbsp;
				<input class="easyui-textbox" type="password" name="old_pwd"  />
		    </div>
		    <div>
				<label>新密码:</label>&nbsp;
				<input class="easyui-textbox" id="new_pwd" type="password" name="new_pwd"  />
		    </div>
		    <div>
				<label>确认密码:</label>
				<input class="easyui-textbox" id="check_pwd" type="password" name="check_pwd" />
		    </div>
		    <div style="margin-top:5px;">
		    	<a class="easyui-linkbutton" onclick="cli()" id="btn">确认</a>
		    	<a class="easyui-linkbutton" href="javascript:$('#fd')[0].reset();">重置</a>
		    </div>
		</form>
	</div>
</div>
  
<script>
	function cli(){
		console.log($("#new_pwd").val()+":"+$("#check_pwd").val())
		if($("#new_pwd").val()!=$("#check_pwd").val()){
			$.messager.alert("警告",'两次密码不一致！！');
			$("#fd").form('reset');
		}else{
			$('#fd').form('submit', {
				url: '${pageContext.request.contextPath}/user',
				success: function(data){
					if(data){
						$.messager.show({
							title:'提示',
							msg:'修改成功,3秒后跳转重新登录',
							timeout:3000,
							showType:'show'
						});
						setTimeout(function(){
							parent.window.location="${pageContext.request.contextPath}/login.jsp";
						},3000)
					}else{
						$.messager.alert("警告","旧密码错误，请确认后重新输入！！");
					}
				}
			});
		}
		
	}

</script>
</body>
</html>