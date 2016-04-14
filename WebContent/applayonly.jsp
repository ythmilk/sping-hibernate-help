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
</head>
<body>
	<table class="table table-striped"
		style="margin: 20px auto; width: 90%">
		<tr>
			<th>姓名</th>
			<th>地址</th>
			<th>申请进度</th>
			<th>支付</th>
		</tr>
		<jsp:useBean id="user" class="com.yth.info.UserInfo" scope="request">
		</jsp:useBean>
		<%
			Long cardId = user.getCardId();
			ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
			OperationDb operationDb = (OperationDb) act.getBean("operationDb");
			List<OrderTest> orders = operationDb.getOrderByCardId(cardId);
			if (orders.size() > 0) {
				for (OrderTest order : orders) {
		%>
		<tr>
			<td><%=order.getUser().getUserName()%></td>
			<td><%=order.getAddress()%></td>
			<td><%=order.getAplayState()%></td>
			<%
				if ("申请通过".equals(order.getAplayState())) {
			%>
			<td><button class="btn btn-primary">付款</button>
				<p style="display: inline;">未支付</p></td>
			<%
				} else {
			%>
			<td><button class="no btn btn-primary"
					style="background-color: gray;" disabled="disabled">付款</button></td>
			<%
				}
			%>
		</tr>
		<%
			}
			}
		%>
	</table>

</body>
</html>