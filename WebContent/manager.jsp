
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
<script>
	$(document).ready(function() {
	});
	function checkMoney() {
		var monet = $("#checkMoney").val();
		if (!checknull(monet)) {
			if (isidcard) {
				//设置支付按钮可点击
			}
		}

	}
	//判断是否是空
	function checknull(a) {
		if (a == "") {
			return true;
		} else {
			return false
		}
	}
	//判断是否是数字
	function isidcard(str) {
		var reg = new RegExp("^[0-9]*$");
		if (reg.test(str)) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<div style="margin-bottom: 40px">
		<a id="refresh" href="manager.jsp">刷新</a>
	</div>
	<table class="table table-bordered"
		style="margin: 20px auto; width: 90%">
		<tr>
			<th>姓名</th>
			<th>地址</th>
			<th>设定价格</th>
		</tr>
		<jsp:useBean id="user" class="com.yth.info.UserInfo" scope="request">
		</jsp:useBean>
		<%
			Long cardId = user.getCardId();
			ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
			OperationDb operationDb = (OperationDb) act.getBean("operationDb");
			List<OrderTest> orders = operationDb.getOrderUnDo();
			if (orders.size() > 0) {
				for(int i=0;i<orders.size();i++)
				 {
					//添加一个是否有金额，有的话btn为修改
		%>
		<tr>
			<td><%=orders.get(i).getUser().getUserName()%></td>
			<td><%=orders.get(i).getAddress()%></td>
			<td><form name="myForm" action="Manager" method="post">
					<div class="row">
						<div class="col-lg-3 " style="width: 300px">
							<div class="input-group">
								<input type="text" class="form-control" name="yth"
									placeholder="金额"> <input type="hidden" value=<%=orders.get(i).getId() %> name="id"><span
									class="input-group-btn">
									<button class="btn btn-default" type="submit">确定</button>
								</span>
							</div>
							<!-- /input-group -->
						</div>
						<!-- /.col-lg-3 -->
					</div>
					<!-- /.row -->
				</form> <%
				}
			%>
		</tr>
		<%
			}
			
		%>
	</table>

</body>
</html>