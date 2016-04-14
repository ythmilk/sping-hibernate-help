<%@page import="com.yth.info.Execute"%>
<%@page import="com.yth.info.OrderTest"%>
<%@page import="java.util.List"%>
<%@page import="com.yth.dao.OperationDb"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>申请查询</title>
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<style type="text/css">
a#refresh {
	position: absolute;
	right: 50px;
	top: 0px;
	margin-bottom: 20px
}
</style>
</head>
<body>
	<div style="margin-bottom: 40px">
		<a id="refresh" href="order-do.jsp">刷新</a>
	</div>
	<table class="table table-striped"
		style="margin: 20px auto; width: 90%">
		<tr>
			<th>地址</th>
			<th>执行</th>
		</tr>
		<jsp:useBean id="user" class="com.yth.info.UserInfo" scope="request">
		</jsp:useBean>
		<%
			Long cardId = user.getCardId();
			ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
			OperationDb operationDb = (OperationDb) act.getBean("operationDb");
			List<OrderTest> orders = operationDb.getNeedDo();
			if (orders.size() > 0) {
				for (int i = 0; i < orders.size(); i++) {
		%>
		<tr>
			<td><%=orders.get(i).getAddress()%></td>
			<td>
				<form class="form-inline" role="form" action="ExecuteServlet">
					<div class="checkbox">
						<label> <input type="checkbox" name="yth">执行
						</label> <input type="hidden" value=<%=orders.get(i).getId()%> name="id">
					</div>
					<button type="submit" class="btn btn-default">提交</button>
				</form>
			</td>
			<%
				}
				}
			%>
		
	</table>

</body>
</html>