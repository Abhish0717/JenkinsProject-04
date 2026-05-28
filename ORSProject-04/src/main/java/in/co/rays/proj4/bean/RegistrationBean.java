package in.co.rays.proj4.bean;

public class RegistrationBean extends BaseBean {
	private String registrationCode;
	private String firstName;
	private String LastName;
	private String Status;

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String getKey() {
		return registrationCode;
	}

	@Override
	public String getValue() {
		return registrationCode;
	}

}
