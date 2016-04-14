package com.yth.daoimp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.regexp.internal.recompile;
import com.yth.dao.OperationDb;
import com.yth.info.Execute;
import com.yth.info.OrderTest;
import com.yth.info.PayInfo;
import com.yth.info.UserInfo;

@Transactional
@Repository("operationDb")
public class OperationDbImp implements OperationDb {
	@Autowired
	private SessionFactory sessionFactory;

	/** 获取当前线程绑定的Session */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean inseartDb(String userName, Long cardId, Long phoneNumber, String address, String applyState) {
		/**
		 * 思路，在插入的时候先做一次查询，如果数据库中已经有用户信息，就不进行插入，只插入订单信息。
		 */

		// 用户信息
		UserInfo userInfo = new UserInfo();
		userInfo.setCardId(cardId);

		String hql = "FROM UserInfo user WHERE user.cardId=?";
		Query query = getSession().createQuery(hql).setLong(0, cardId);
		List<UserInfo> us = query.list();

		// 订单信息初始化
		PayInfo payInfo = new PayInfo();

		OrderTest orderTest = new OrderTest();
		orderTest.setAddress(address);
		orderTest.setAplayState("正在申请");
		orderTest.setIsDo(false);
		orderTest.setIsPay(false);
		orderTest.setOrderTime(new Date(new java.util.Date().getTime()));
		orderTest.setUser(userInfo);
		orderTest.setPayInfo(payInfo);
		payInfo.setOrder(orderTest);
		if (us.size() == 0) {
			userInfo.setPhoneNumber(phoneNumber);
			userInfo.setUserName(userName);
			Set<OrderTest> orders = new HashSet<>();
			orders.add(orderTest);
			userInfo.setOrders(orders);
			getSession().save(userInfo);
		}
		try {
			getSession().save(orderTest);
			getSession().save(payInfo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<OrderTest> getOrderByCardId(Long cardId) {
		String hql = "FROM OrderTest order WHERE order.user.cardId=?";
		List<OrderTest> orders = new ArrayList<OrderTest>();
		try {
			orders = getSession().createQuery(hql).setLong(0, cardId).list();
		} catch (NullPointerException e) {
			System.out.println("数据库为空");
		}
		return orders;
	}

	@Override
	public List<OrderTest> getOrderTody() {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date da = new Date();
		String da1 = dateFormat.format(da);
		StringBuffer sb = new StringBuffer();
		sb.append(da1);
		sb.append(" 00:00:00");
		Date date = new Date();
		try {
			date = dateFormat.parse(sb.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String hql = "FROM OrderTest order WHERE order.orderTime>?";
		Query query = getSession().createQuery(hql).setDate(0, date);
		List<OrderTest> orderes = query.list();
		return orderes;
	}

	@Override
	public List<OrderTest> getOrderUnDo() {
		String hql = "FROM OrderTest orderx WHERE orderx.aplayState=? ";
		Query query = getSession().createQuery(hql).setString(0, "正在申请");
		return query.list();
	}

	@Override
	public void setMoney(Double money, int id) {
		String hql = "UPDATE PayInfo pay SET  pay.money=:money WHERE pay.id=:payId";
		String hql2 = "UPDATE OrderTest order1 SET order1.aplayState=:aplayState WHERE order1.id=:orderId";
		Query query = getSession().createQuery(hql2);
		query.setString("aplayState", "申请通过");

		query.setInteger("orderId", id);
		query.executeUpdate();
		query = getSession().createQuery(hql);

		query.setDouble("money", money);
		query.setInteger("payId", id);
		query.executeUpdate();
	}

	@Override
	public List<OrderTest> getNeedDo() {
		// 查询所有isPay=true isDo=false的地址
		String hql = "SELECT new OrderTest(order2.id,order2.address) FROM OrderTest order2 WHERE order2.isPay=true AND order2.isDo=false";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void completed(int id) {
		String hql = "UPDATE OrderTest order2 SET order2.isDo=true,order2.completeTime=:time ,order2.aplayState=:aplayState WHERE order2.id=:id ";
		Query query = getSession().createQuery(hql);
		query.setDate("time", new Date(new java.util.Date().getTime())).setInteger("id", id).setString("aplayState", "已完成").executeUpdate();
	}

	// 审核通过没有支付的订单
	@Override
	public List<OrderTest> getOrderUnDoT(String aplayState) {
		String hql = "FROM OrderTest order WHERE order.aplayState=?";
		Query query = getSession().createQuery(hql).setString(0, aplayState);
		return query.list();
	}

	@Override
	public void payed(int id) {
		String hql = "UPDATE OrderTest orders SET orders.isPay=true,orders.aplayState=:aplayState WHERE orders.id=:id";
		Query query = getSession().createQuery(hql).setString("aplayState", "已支付").setInteger("id", id);
		query.executeUpdate();
	}

	@Override
	public List<OrderTest> getUnPay() {
		String hql = "FROM OrderTest orders WHERE orders.isPay=false AND isDo=false";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<OrderTest> getPayUnDo() {
		String hql = "FROM OrderTest orders WHERE orders.isPay=true AND isDo=false";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<OrderTest> getcompleted() {
		String hql = "FROM OrderTest orders WHERE orders.isDo=true";
		return getSession().createQuery(hql).list();
	}
}
