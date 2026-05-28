package in.co.rays.proj4.bean;

public class TransformationBean extends BaseBean {
	private String transformCode;
	private String ruleName;
	private String logic;
	private String status;

	public String getTransformCode() {
		return transformCode;
	}

	public void setTransformCode(String transformCode) {
		this.transformCode = transformCode;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return ruleName;
	}

	@Override
	public String getValue() {
		return ruleName;
	}

}
