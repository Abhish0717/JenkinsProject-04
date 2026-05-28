package in.co.rays.proj4.bean;

public class QueueBean extends BaseBean{
	private String listenerCode;
	private String queueName;
	private String consumerGroup;
	private String status;
	
	public String getListenerCode() {
		return listenerCode;
	}
	public void setListenerCode(String listenerCode) {
		this.listenerCode = listenerCode;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getConsumerGroup() {
		return consumerGroup;
	}
	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String getKey() {
		return queueName;
	}
	@Override
	public String getValue() {
		return queueName;
	}

}
