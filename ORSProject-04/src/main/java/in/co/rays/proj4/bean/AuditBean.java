package in.co.rays.proj4.bean;

public class AuditBean extends BaseBean {
	private String auditCode;
	private String action;
	private String performedBy;
	private String status;

	public String getAuditCode() {
		return auditCode;
	}

	public void setAuditCode(String auditCode) {
		this.auditCode = auditCode;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return auditCode;
	}

	@Override
	public String getValue() {
		return auditCode;
	}
}
