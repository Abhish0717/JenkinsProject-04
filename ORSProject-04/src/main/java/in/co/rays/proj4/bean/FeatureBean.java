package in.co.rays.proj4.bean;

public class FeatureBean extends BaseBean {
	private String accessCode;
	private String featureName;
	private String userName;
	private String status;

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return featureName;
	}

	@Override
	public String getValue() {
		return featureName;
	}
}
