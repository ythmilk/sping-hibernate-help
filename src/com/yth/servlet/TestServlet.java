package com.yth.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.yth.dao.OperationDb;
import com.yth.info.UserInfo;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");

		String cardId = request.getParameter("cardId");
		String userName = request.getParameter("userName");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		String applyState = request.getParameter("applyState");

		ServletContext servletContext = getServletContext();

		ApplicationContext act = (ApplicationContext) servletContext.getAttribute("ApplicationContext");
		OperationDb operationDb = (OperationDb) act.getBean("operationDb");
		Boolean bo = operationDb.inseartDb(userName, Long.parseLong(cardId), Long.parseLong(phoneNumber), address,
				applyState);
		UserInfo user = new UserInfo();
		user.setCardId(Long.parseLong(cardId));
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("applayonly.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
