<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
	</head>
	<body>
	当前空闲的节点IP有：【47.95.194.16】【39.107.83.2】【39.106.194.129】<br/>
		<form action="${pageContext.request.contextPath }/CalculateStartServlet" method="post">
			<input class="btn" type="submit" value="submit" />${message }<br/>
            host:${host }<br/>
            ip1:${ip1 }<br/>
            ip2:${ip2 }<br/>

		</form>
	</body>
</html>