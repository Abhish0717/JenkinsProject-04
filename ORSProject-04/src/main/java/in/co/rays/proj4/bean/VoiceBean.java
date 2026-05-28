package in.co.rays.proj4.bean;

public class VoiceBean extends BaseBean {
	private String commandCode;
	private String userName;
	private String commandText;
	private String status;

	public String getCommandCode() {
		return commandCode;
	}

	public void setCommandCode(String commandCode) {
		this.commandCode = commandCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCommandText() {
		return commandText;
	}

	public void setCommandText(String commandText) {
		this.commandText = commandText;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return commandCode;
	}

	@Override
	public String getValue() {
		return commandCode;
	}

}
