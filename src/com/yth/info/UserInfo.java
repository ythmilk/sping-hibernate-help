package com.yth.info;

import java.util.HashSet;
import java.util.Set;

public class UserInfo {
	private String userName;
	private Long cardId;
	private Long phoneNumber;

	private Set<OrderTest> orders = new HashSet<>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<OrderTest> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderTest> orders) {
		this.orders = orders;
	}

}
