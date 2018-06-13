<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>首页</title>
</head>
<body style="text-align: center;">

<h1>xxxxx网站</h1>
<br/>

<div style="text-align: right;">
  <c:if test="${user != null }">
    欢迎您：${user.nikename }
    <a href="${pageContext.request.contextPath }/LogoutServlet">注销</a>
  </c:if>

  <c:if test="${user == null }">
    <a href="${pageContext.request.contextPath }/RegisterUIServlet">注册</a>
    <a href="${pageContext.request.contextPath }/LoginUIServlet">登录</a>
    <a href="${pageContext.request.contextPath }/BlockUIServlet">区块</a>
    <a href="${pageContext.request.contextPath }/CalculateStartUIServlet">计算</a>

  </c:if>
</div>
<br/>
<hr>
</body>
</html>
