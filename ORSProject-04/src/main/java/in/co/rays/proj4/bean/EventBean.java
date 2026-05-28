package in.co.rays.proj4.bean;

public class EventBean extends BaseBean {
	private String eventListenerCode;
	private String eventName;
	private String handler;
	private String status;

	public String getEventListenerCode() {
		return eventListenerCode;
	}

	public void setEventListenerCode(String eventListenerCode) {
		this.eventListenerCode = eventListenerCode;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
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
