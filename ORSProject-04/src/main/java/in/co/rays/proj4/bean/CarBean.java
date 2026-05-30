package in.co.rays.proj4.bean;

public class CarBean extends BaseBean {
	private String customerName;
	private String carModel;
	private long rentPerDay;
	private String fuelType;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public long getRentPerDay() {
		return rentPerDay;
	}

	public void setRentPerDay(long rentPerDay) {
		this.rentPerDay = rentPerDay;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Override
	public String getKey() {
		return carModel;
	}

	@Override
	public String getValue() {
		return carModel;
	}
}
