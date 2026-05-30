package in.co.rays.proj4.bean;

public class AtmBean extends BaseBean {
	private String bankName;
	private String location;
	private long cashAvailable;
	private int securityCode;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getCashAvailable() {
		return cashAvailable;
	}

	public void setCashAvailable(long cashAvailable) {
		this.cashAvailable = cashAvailable;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	public String getKey() {
		return bankName;
	}

	@Override
	public String getValue() {
		return bankName;
	}
}
