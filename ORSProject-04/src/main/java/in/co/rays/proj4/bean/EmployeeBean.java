package in.co.rays.proj4.bean;

public class EmployeeBean extends BaseBean {
	private String employeeName;
	private String department;
	private long salary;
	private long bonus;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public long getBonus() {
		return bonus;
	}

	public void setBonus(long bonus) {
		this.bonus = bonus;
	}

	@Override
	public String getKey() {
		return department;
	}

	@Override
	public String getValue() {
		return department;
	}
}
