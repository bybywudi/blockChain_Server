<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
	</head>
	<body>
	当前空闲的节点IP有：【47.95.194.16】【39.107.83.2】【39.106.194.129】<br/>
	当前可以计算的问题有：<br/>
    <a href="${pageContext.request.contextPath }/CalculateStartServlet?qid=1">1.计算1到100万中质数的个数。</a><br/>
    <a href="${pageContext.request.contextPath }/CalculateStartServlet?qid=2">2.计算1到100万中能同时被2,3,5整除的数的个数。</a><br/>
	</body>
</html>