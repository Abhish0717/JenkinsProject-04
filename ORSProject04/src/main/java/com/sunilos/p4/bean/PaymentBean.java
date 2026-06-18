package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentBean extends BaseBean {
	private String orderId;
	private long amount;
	private String paymentMethod;
	private String paymentStatus;

	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public long getAmount() {
		return amount;
	}



	public void setAmount(long amount) {
		this.amount = amount;
	}



	public String getPaymentMethod() {
		return paymentMethod;
	}



	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}



	public String getPaymentStatus() {
		return paymentStatus;
	}



	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	@Override
	public String getKey() {
		return orderId + "";
	}
	
	
	
	@Override
	public String getValue() {
		return orderId + "";
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setOrderId(rs.getString("order_id"));
			this.setAmount(rs.getLong("amount"));
			this.setPaymentMethod(rs.getString("method"));
			this.setPaymentStatus(rs.getString("status"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
