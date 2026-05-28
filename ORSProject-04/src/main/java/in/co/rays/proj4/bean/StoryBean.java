package in.co.rays.proj4.bean;

public class StoryBean extends BaseBean {
	private String storyCode;
	private String userName;
	private String confirmValue;
	private String status;

	public String getStoryCode() {
		return storyCode;
	}

	public void setStoryCode(String storyCode) {
		this.storyCode = storyCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getConfirmValue() {
		return confirmValue;
	}

	public void setConfirmValue(String confirmValue) {
		this.confirmValue = confirmValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return storyCode;
	}

	@Override
	public String getValue() {
		return storyCode;
	}

}
