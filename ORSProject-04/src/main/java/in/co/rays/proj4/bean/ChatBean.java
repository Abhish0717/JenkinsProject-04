package in.co.rays.proj4.bean;

public class ChatBean extends BaseBean{
	private String chatRoomCode;
	private String RoomName;
	private String chatCreatedBy;
	private String status;

	public String getChatRoomCode() {
		return chatRoomCode;
	}
	public void setChatRoomCode(String chatRoomCode) {
		this.chatRoomCode = chatRoomCode;
	}
	public String getRoomName() {
		return RoomName;
	}
	public void setRoomName(String roomName) {
		RoomName = roomName;
	}
	public String getChatCreatedBy() {
		return chatCreatedBy;
	}
	public void setChatCreatedBy(String chatCreatedBy) {
		this.chatCreatedBy = chatCreatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String getKey() {
		return chatRoomCode;
	}
	@Override
	public String getValue() {
		return chatRoomCode;
	}
}
