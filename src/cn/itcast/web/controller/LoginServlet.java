package cn.itcast.web.controller;

import cn.itcast.domain.User;
import cn.itcast.service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//处理登录请求
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		BusinessServiceImpl service = new BusinessServiceImpl();
		User user = service.login(username, password);
		if(user != null) {
			request.getSession().setAttribute("user", user);
			//让用户登录成功后，跳转首页
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}

		request.setAttribute("message", "用户名或密码错误");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}