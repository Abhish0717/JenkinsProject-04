package com.sunilos.p4.testmodel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sunilos.p4.bean.HospitalBean;
import com.sunilos.p4.model.HospitalModel;

public class TestHospitalModel {

	public static void main(String[] args) {

//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByUniqueColumn();
		testSearch();
	}

	/**
	 * Test Add
	 */
	private static void testAdd() {

		try {

			HospitalModel model = new HospitalModel();

			HospitalBean bean = new HospitalBean();

			bean.setPatientName("Rahul Sharma");
			bean.setDoctorName("Dr. Mehta");
			bean.setDisease("Fever");
			bean.setRoomNumber(101);
			bean.setCreatedBy("root");
			bean.setModifiedBy("root");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = model.add(bean);

			System.out.println("Data Added Successfully : " + pk);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test Update
	 */
	private static void testUpdate() {

		try {

			HospitalModel model = new HospitalModel();
			HospitalBean bean = new HospitalBean();

			bean.setId(2);
			bean.setPatientName("Rahul Gupta ");
			bean.setDoctorName("Dr. Verma");
			bean.setDisease("Cough-Cold");
			bean.setRoomNumber(202);
			bean.setCreatedBy("root");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			model.update(bean);

			System.out.println("Data Updated Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test Delete
	 */
	private static void testDelete() {

		try {

			HospitalModel model = new HospitalModel();
			HospitalBean bean = new HospitalBean();

			bean.setId(3);
			model.delete(bean);

			System.out.println("Data Deleted Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test Find By PK
	 */
	private static void testFindByPk() {

		try {

			HospitalModel model = new HospitalModel();
			HospitalBean bean = model.findByPk(2);

			if (bean != null) {
				System.out.println("ID : " + bean.getId());
				System.out.println("Patient : " + bean.getPatientName());
				System.out.println("Doctor : " + bean.getDoctorName());
				System.out.println("Disease : " + bean.getDisease());
				System.out.println("Room : " + bean.getRoomNumber());

			} else {

				System.out.println("Record Not Found");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test Find By Unique Column
	 */
	private static void testFindByUniqueColumn() {

		try {

			HospitalModel model = new HospitalModel();
			HospitalBean bean = model.findByName("Rahul Sharma");

			if (bean != null) {
				System.out.println("ID : " + bean.getId());
				System.out.println("Patient : " + bean.getPatientName());
				System.out.println("Doctor : " + bean.getDoctorName());
				System.out.println("Disease : " + bean.getDisease());
				System.out.println("Room : " + bean.getRoomNumber());

			} else {

				System.out.println("Record Not Found");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test Search
	 */
	private static void testSearch() {

		try {

			HospitalModel model = new HospitalModel();
			HospitalBean bean = new HospitalBean();

//			bean.setDisease("Cough-Cold");

			List<HospitalBean> list = model.search(bean);
			Iterator<HospitalBean> it = list.iterator();

			while (it.hasNext()) {
				bean = it.next();
				System.out.println("ID : " + bean.getId());
				System.out.println("Patient : " + bean.getPatientName());
				System.out.println("Doctor : " + bean.getDoctorName());
				System.out.println("Disease : " + bean.getDisease());
				System.out.println("Room : " + bean.getRoomNumber());

				System.out.println("--------------------------------");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}