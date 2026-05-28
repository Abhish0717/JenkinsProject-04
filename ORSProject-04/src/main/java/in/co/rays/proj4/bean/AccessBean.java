package in.co.rays.proj4.bean;

import java.util.Date;

public class AccessBean extends BaseBean {
	private String accessLogCode;
	private String userName;
	private Date accessTime;
	private String status;

	public String getAccessLogCode() {
		return accessLogCode;
	}

	public void setAccessLogCode(String accessLogCode) {
		this.accessLogCode = accessLogCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return userName;
	}

	@Override
	public String getValue() {
		return userName;
	}
}
