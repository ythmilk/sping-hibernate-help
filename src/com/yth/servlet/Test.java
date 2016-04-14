package com.yth.servlet;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yth.dao.OperationDb;
import com.yth.info.OrderTest;
import com.yth.info.UserInfo;

public class Test {
	private ApplicationContext act = null;

	{
		act = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@org.junit.Test
	public void test() {
		OperationDb operationDb = (OperationDb) act.getBean("operationDb");
		// DataSource dataSource=(DataSource) act.getBean("dataSource");
		// System.out.println(dataSource);
		Long cardId = 111111l;
		String userName = "姚腾辉";
		Long phoneNumber = 13994l;
		String address = "江苏大学";
		String applyState = "正在申请";
		operationDb.inseartDb(userName, cardId, phoneNumber, address, applyState);

	}

	@org.junit.Test
	public void testt() {
		OperationDb operationDb = (OperationDb) act.getBean("operationDb");
	List<OrderTest> or = operationDb.getOrderUnDo();
	System.out.println(or.size());
	}

	@org.junit.Test
	public void testData() {
		// DataSource dataSource = (DataSource) act.getBean("dataSource");
		// System.out.println(dataSource);
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date da = new Date();
		String da1 = dateFormat.format(da);
		StringBuffer sb = new StringBuffer();
		sb.append(da1);
		sb.append(" 00:00:00");
		Date date=new Date();
		try {
			 date=dateFormat.parse(sb.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(dateFormat.format(date));
	}
	@org.junit.Test
	public void testUpdate(){
		OperationDb operationDb = (OperationDb) act.getBean("operationDb");
		operationDb.payed(3);;
		
	}
}