<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath }/BlockServlet" method="post">
			hash：<input class="inputtext" type="text" name="blockid" />
			user：<input class="inputtext" type="text" name="userid" />
			<input class="btn" type="submit" value="submit" />
		</form>
	</body>
</html>