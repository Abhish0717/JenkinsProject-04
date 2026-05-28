package in.co.rays.proj4.bean;

public class SubscriptionBean extends BaseBean {
	private String planCode;
	private String planName;
	private long price;
	private String status;

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long l) {
		this.price = l;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return planName;
	}

	@Override
	public String getValue() {
		return planName;
	}
}
