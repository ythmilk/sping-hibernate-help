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

/**
 * Servlet implementation class ExecuteServlet
 */
@WebServlet("/ExecuteServlet")
public class ExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");

		ServletContext servletContext = getServletContext();
		ApplicationContext act = (ApplicationContext) servletContext.getAttribute("ApplicationContext");
		OperationDb operationDb = (OperationDb) act.getBean("operationDb");
		String isChecked = request.getParameter("yth");
		String id = request.getParameter("id");
		try {
			if (isChecked.equals("on")) {
				System.out.println("已经执行");
				//写入数据库，时间isDo=true;
				//完成时间写入
				operationDb.completed(Integer.parseInt(id));
			}
		} catch (NullPointerException e) {
			System.out.println("没有执行");
		}
		request.getRequestDispatcher("order-do.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
