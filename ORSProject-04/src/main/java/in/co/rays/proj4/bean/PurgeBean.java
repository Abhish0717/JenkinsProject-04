package in.co.rays.proj4.bean;

import java.util.Date;

public class PurgeBean extends BaseBean {
	private String purgeCode;
	private String dataType;
	private Date lastRunDate;
	private String status;

	public String getPurgeCode() {
		return purgeCode;
	}

	public void setPurgeCode(String purgeCode) {
		this.purgeCode = purgeCode;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Date getLastRunDate() {
		return lastRunDate;
	}

	public void setLastRunDate(Date lastRunDate) {
		this.lastRunDate = lastRunDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return purgeCode;
	}

	@Override
	public String getValue() {
		return purgeCode;
	}
}
