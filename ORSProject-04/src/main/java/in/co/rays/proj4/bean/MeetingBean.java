package in.co.rays.proj4.bean;

public class MeetingBean extends BaseBean {
	private String hostName;
	private String platform;
	private long duration;
	private long participants;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getParticipants() {
		return participants;
	}

	public void setParticipants(long participants) {
		this.participants = participants;
	}

	@Override
	public String getKey() {
		return hostName;
	}

	@Override
	public String getValue() {
		return hostName;
	}
}
