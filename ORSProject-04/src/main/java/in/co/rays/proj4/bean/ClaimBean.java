package in.co.rays.proj4.bean;

public class ClaimBean extends BaseBean {
	private String claimNumber;
	private int claimAmount;
	private String status;

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public int getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(int claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return claimNumber;
	}

	@Override
	public String getValue() {
		return claimNumber;
	}

}
