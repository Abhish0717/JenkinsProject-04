package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HospitalBean extends BaseBean {
	private String patientName;
	private String doctorName;
	private String disease;
	private int roomNumber;

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}
	
	@Override
	public void setResultset(ResultSet rs)  {
		try {
		super.setResultset(rs);
			this.setPatientName(rs.getString("patient"));
			this.setDoctorName(rs.getString("doctor"));
			this.setDisease(rs.getString("disease"));
			this.setRoomNumber(rs.getInt("room"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
