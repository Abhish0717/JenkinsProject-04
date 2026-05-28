package in.co.rays.proj4.bean;

public class PasswordBean extends BaseBean {
	private String resetCode;
	private String userName;
	private String resetToken;
	private String status;

	public String getResetCode() {
		return resetCode;
	}

	public void setResetCode(String resetCode) {
		this.resetCode = resetCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
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
