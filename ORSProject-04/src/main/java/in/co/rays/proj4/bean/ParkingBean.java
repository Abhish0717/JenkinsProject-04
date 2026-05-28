package in.co.rays.proj4.bean;

public class ParkingBean extends BaseBean {
	private String vehicleNumber;
	private String slotNumber;
	private String entryTime;
	private long parkingCharge;


	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public long getParkingCharge() {
		return parkingCharge;
	}

	public void setParkingCharge(long parkingCharge) {
		this.parkingCharge = parkingCharge;
	}

	@Override
	public String getKey() {
		return slotNumber;
	}

	@Override
	public String getValue() {
		return slotNumber;
	}

}
