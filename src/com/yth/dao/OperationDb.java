package com.yth.dao;

import java.util.List;

import com.yth.info.Execute;
import com.yth.info.OrderTest;

public interface OperationDb {
	/** �ύ������еĲ��� */
	public boolean inseartDb(String userName, Long cardId, Long phoneNumber, String address, String applyState);

	/** ��ѯ���� */
	public List<OrderTest> getOrderByCardId(Long cardId);

	/** ��ȡ���ն��� */
	public List<OrderTest> getOrderTody();

	/** ��ȡ���е��������״̬�Ķ��� */
	public List<OrderTest> getOrderUnDo();

	/** ��ȡ�������ͨ����û��֧���� */
	public List<OrderTest> getOrderUnDoT(String aplayState);
	
	/** ����δ֧���� */
	public List<OrderTest> getUnPay();
	
	/** ����֧����û������ */
	public List<OrderTest> getPayUnDo();
	
	/** �����Ѿ���ɵĶ��� */
	public List<OrderTest> getcompleted();
	
	/** ��д����ʱ����в��� */
	public void setMoney(Double money, int id);

	/** ��ȡ������Ҫִ�еĶ��� */
	public List<OrderTest> getNeedDo();
	/**�������ʱ�䣬����isDo=true*/
	public void completed(int id);
	
	/**�������ʱ�䣬����isPay=true ״̬��Ϊ�Ѿ�֧��*/
	public void payed(int id);
}
