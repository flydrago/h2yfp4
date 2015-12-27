<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
</head>

<script type="text/javascript">
	
</script>
<body>
	<form action="<%=basePath%>/fp/upload2.htm" method="post" enctype="multipart/form-data">
		<%-- 类型enctype用multipart/form-data，这样可以把文件中的数据作为流式数据上传，不管是什么文件类型，均可上传。--%>
		请选择要上传的文件
		<input type="file" name="file" size="50"><br/>
		<input type="submit" value="提交">
	</form>
</body>
</html>
