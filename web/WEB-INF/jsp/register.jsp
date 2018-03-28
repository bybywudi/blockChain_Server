<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>my register.jsp</title>
	</head>

	<body>
		<form action="${pageContext.request.contextPath }/RegisterServlet" method="post">
		<table id="formtable">
			<tr>
				<td class="td1">登录账号：</td>
				<td>
					<input class="userinput" type="text" name="username" value="${form.username }">
					<span class="message">${form.errors.username }</span>
				</td>
			</tr>
			
			<tr>
				<td class="td1">密码：</td>
				<td>
					<input class="userinput" type="password" name="password" value="${form.password }">
					<span class="message">${form.errors.password }</span>
				</td>
			</tr>
			
			<tr>
				<td class="td1">确认密码：</td>
				<td>
					<input class="userinput" type="password" name="password2" value="${form.password2 }">
					<span class="message">${form.errors.password2 }</span>
				</td>
			</tr>
			
			<tr>
				<td class="td1">电子邮箱：</td>
				<td>
					<input class="userinput" type="text" name="email" value="${form.email }">
					<span class="message">${form.errors.email }</span>
				</td>
			</tr>
			
			<tr>
				<td class="td1">生日：</td>
				<td>
					<input class="userinput" type="text" name="birthday" value="${form.birthday }">
					<span class="message">${form.errors.birthday }</span>
				</td>
			</tr>
			
			<tr>
				<td class="td1">您的昵称：</td>
				<td>
					<input class="userinput" type="text" name="nikename" value="${form.nikename }">
					<span class="message">${form.errors.nikename }</span>
				</td>
			</tr>
			
			<tr>
				<td class="td1">图片认证：</td>
				<td>
					<input class="userinput" type="text" name="checkcode"><img alt="#" src="20px">
				</td>
			</tr>
		</table>
			<span><input class="btn" type="reset" name="reset" value="重置"></span>
			&nbsp;&nbsp;
			<span><input class="btn" type="submit" name="submit" value="注册"></span>
		</form>
	</body>
</html>