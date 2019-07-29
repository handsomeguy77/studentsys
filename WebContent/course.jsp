<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>所有课程信息</title>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>

<script type="text/javascript">
	$(function(){
		if('${msg}'!=''){
			$.messager.show({
				title:'提示',
				msg:'${msg}',
				timeout:5000,
				showType:'show'
			});
		}
	})
</script>
<body>
	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="add()">添加课程</a>
	<table id="dg"></table>
	
	<div id="dd" class="easyui-dialog" style="width:400px;height:200px"
		data-options="title:'添加',buttons:'#bb',modal:true,closed:true">
		<form id="fd" action="${pageContext.request.contextPath}/course" method="post">
			<input type="hidden" name="method" value="add"/>
			<div>
				<label>课程名:</label>
				<input class="easyui-textbox"  type="text" name="className"/>
		    </div>
		     <div>
				<label>学期:</label>&nbsp;
				<select id="sel" class="easyui-combobox" name="subjectId">
					<c:forEach items="${subjects}" var="subject">
						<option value="${subject.id}">${subject.subjectName}</option>
					</c:forEach> 
				</select>
		    </div>
		     <div>
				<label>专业:</label>&nbsp;
				<select id="sel" class="easyui-combobox" name="term">
					<c:forEach items="${terms}" var="term">
						<option value="${term}">${term}</option>
					</c:forEach>
				</select>
		    </div>
		</form>
	</div>
	<div id="bb">
		<a href="javascript:document.getElementById('fd').submit();" class="easyui-linkbutton">确定</a>
		<a href="#" class="easyui-linkbutton" onclick="cancer()">取消</a>
	</div>
	
	
	<script type="text/javascript">
	
		//数据表格的加载
		$('#dg').datagrid({
		    url:'${pageContext.request.contextPath}/course',
		    columns:[[
				{field:'className',title:'课程名',width:100},
				{field:'teachName',title:'教师名',width:100},
				{field:'term',title:'学期',width:100},
				{field:'subjectName',title:'专业名',width:100}
		    ]],
		    queryParams: {
				method:'showCourse'
			},
			pagination:true,
			pageSize:3,
			pageList:[3,6,9,12,15]
		});
	
		//弹出层的取消功能
		function cancer(){
			$("#dd").dialog('close',true);
		}
		
		//添加功能
		function add(){
			$("#dd").dialog('open');
		}
	</script>

</body>
</html>