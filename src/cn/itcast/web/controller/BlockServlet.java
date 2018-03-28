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
@WebServlet("/BlockServlet")
public class BlockServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String blockid = request.getParameter("blockid");

		BusinessServiceImpl service = new BusinessServiceImpl();
		if(service.addNewBolck(Integer.parseInt(blockid),userid)){
			response.setStatus(200);
			return;
		}else{
			response.setStatus(207);
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}