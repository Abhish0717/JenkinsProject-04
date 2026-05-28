package in.co.rays.proj4.bean;

import java.util.Date;

public class LoginBean extends BaseBean {
	private String historyCode;
	private String userName;
	private Date loginTime;

	public String getHistoryCode() {
		return historyCode;
	}

	public void setHistoryCode(String historyCode) {
		this.historyCode = historyCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String status;

	@Override
	public String getKey() {
		return userName;
	}

	@Override
	public String getValue() {
		return userName;
	}
}
