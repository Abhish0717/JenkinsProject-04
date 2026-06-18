package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApiBean extends BaseBean {
	private String apiName;
	private String version;
	private String endPoint;
	private String status;

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return apiName;
	}

	@Override
	public String getValue() {
		return apiName;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setApiName(rs.getString("name"));
			this.setVersion(rs.getString("version"));
			this.setEndPoint(rs.getString("end_Point"));
			this.setStatus(rs.getString("status"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
