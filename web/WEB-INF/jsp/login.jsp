<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>登录界面</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath }/LoginServlet" method="post">
			用户：<input class="inputtext" type="text" name="username" />
			密码：<input class="inputtext" type="password" name="password" />
			<input class="btn" type="button" value="注册" onclick="" />
			<input class="btn" type="submit" value="登录" />
		</form>
	</body>
</html>