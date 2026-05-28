package in.co.rays.proj4.bean;

public class HealthBean extends BaseBean {

	private String healthCode;
	private String serviceName;
	private String uptime;
	private String status;

	public String getHealthCode() {
		return healthCode;
	}

	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return serviceName;
	}

	@Override
	public String getValue() {
		return serviceName;
	}
}
