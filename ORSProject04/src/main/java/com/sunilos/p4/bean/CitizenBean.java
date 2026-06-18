package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CitizenBean extends BaseBean {
	private String name;
	private Integer mobileNo;
	private String address;
	private String rewardPoints;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Integer mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(String rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	@Override
	public String getKey() {
		return name;
	}

	@Override
	public String getValue() {
		return name;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setName(rs.getString("name"));
			this.setMobileNo(rs.getInt("mobile_no"));
			this.setAddress(rs.getString("address"));
			this.setRewardPoints(rs.getString("reward_points"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
