package com.yth.dao;

import java.util.List;

import com.yth.info.Execute;
import com.yth.info.OrderTest;

public interface OperationDb {
	/** 提交申请进行的操作 */
	public boolean inseartDb(String userName, Long cardId, Long phoneNumber, String address, String applyState);

	/** 查询订单 */
	public List<OrderTest> getOrderByCardId(Long cardId);

	/** 获取当日订单 */
	public List<OrderTest> getOrderTody();

	/** 获取所有的正在审核状态的订单 */
	public List<OrderTest> getOrderUnDo();

	/** 获取所有审核通过但没有支付的 */
	public List<OrderTest> getOrderUnDoT(String aplayState);
	
	/** 所有未支付的 */
	public List<OrderTest> getUnPay();
	
	/** 所有支付但没有做的 */
	public List<OrderTest> getPayUnDo();
	
	/** 所有已经完成的订单 */
	public List<OrderTest> getcompleted();
	
	/** 填写申请时间进行操作 */
	public void setMoney(Double money, int id);

	/** 获取所有需要执行的订单 */
	public List<OrderTest> getNeedDo();
	/**设置完成时间，设置isDo=true*/
	public void completed(int id);
	
	/**设置完成时间，设置isPay=true 状态改为已经支付*/
	public void payed(int id);
}
