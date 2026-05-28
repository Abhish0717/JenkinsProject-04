package in.co.rays.proj4.bean;

import java.util.Date;

public class SystemBean extends BaseBean {
	private String eventCode;
	private String eventName;
	private Date eventTime;
	private String status;

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return eventName;
	}

	@Override
	public String getValue() {
		return eventName;
	}
}
