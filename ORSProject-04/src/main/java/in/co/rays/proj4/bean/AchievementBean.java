package in.co.rays.proj4.bean;

public class AchievementBean extends BaseBean {
	private String badgeCode;
	private String badgeName;
	private String earnedBy;
	private String status;

	public String getBadgeCode() {
		return badgeCode;
	}

	public void setBadgeCode(String badgeCode) {
		this.badgeCode = badgeCode;
	}

	public String getBadgeName() {
		return badgeName;
	}

	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}

	public String getEarnedBy() {
		return earnedBy;
	}

	public void setEarnedBy(String earnedBy) {
		this.earnedBy = earnedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return badgeName;
	}

	@Override
	public String getValue() {
		return badgeName;
	}

}
