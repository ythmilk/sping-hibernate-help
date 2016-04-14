package com.yth.info;

import java.util.Date;

public class OrderTest {
	private Integer id;
	private Boolean isPay;
	private Boolean isDo;
	private UserInfo user;

	private Date orderTime;
	private Date completeTime;

	private String aplayState;
	private String address;
	private PayInfo payInfo;

	public PayInfo getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(PayInfo payInfo) {
		this.payInfo = payInfo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAplayState() {
		return aplayState;
	}

	public void setAplayState(String aplayState) {
		this.aplayState = aplayState;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Boolean getIsPay() {
		return isPay;
	}

	public void setIsPay(Boolean isPay) {
		this.isPay = isPay;
	}

	public Boolean getIsDo() {
		return isDo;
	}

	public void setIsDo(Boolean isDo) {
		this.isDo = isDo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public OrderTest() {
		super();
	}

	public OrderTest(Integer id, String address) {
		super();
		this.id = id;
		this.address = address;
	}

}
