package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourierBean extends BaseBean {
	private String trackingNumber;
	private String senderName;
	private String receiverName;
	private String deliveryStatus;

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setTrackingNumber(rs.getString("tracking"));
			this.setSenderName(rs.getString("sender"));
			this.setReceiverName(rs.getString("receiver"));
			this.setDeliveryStatus(rs.getString("status"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
