package in.co.rays.proj4.bean;

public class FaceBean extends BaseBean {
	private String personName;
	private String confidenceScore;
	private String detectedObject;
	private String cameraId;

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getConfidenceScore() {
		return confidenceScore;
	}

	public void setConfidenceScore(String confidenceScore) {
		this.confidenceScore = confidenceScore;
	}

	public String getDetectedObject() {
		return detectedObject;
	}

	public void setDetectedObject(String detectedObject) {
		this.detectedObject = detectedObject;
	}

	public String getCameraId() {
		return cameraId;
	}

	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}

	@Override
	public String getKey() {
		return cameraId;
	}

	@Override
	public String getValue() {
		return cameraId;
	}

}
