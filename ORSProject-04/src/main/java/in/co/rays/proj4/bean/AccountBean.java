package in.co.rays.proj4.bean;

public class AccountBean extends BaseBean {
	private String accountCode;
	private String accountName;
	private String accountType;
	private String Status;

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String getKey() {
		return accountCode;
	}

	@Override
	public String getValue() {
		return accountCode;
	}
}
