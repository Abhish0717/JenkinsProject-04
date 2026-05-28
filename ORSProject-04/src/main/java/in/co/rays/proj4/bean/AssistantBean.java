package in.co.rays.proj4.bean;

public class AssistantBean extends BaseBean {
	private String userVoice;
	private String response;
	private String language;
	private int accuracy;

	public String getUserVoice() {
		return userVoice;
	}

	public void setUserVoice(String userVoice) {
		this.userVoice = userVoice;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	@Override
	public String getKey() {
		return userVoice;
	}

	@Override
	public String getValue() {
		return userVoice;
	}
}
