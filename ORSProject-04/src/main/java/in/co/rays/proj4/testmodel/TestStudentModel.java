package in.co.rays.proj4.testmodel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.StudentModel;

public class TestStudentModel {

	public static void main(String[] args) throws ParseException {

//		testNextPk();
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByEmail();
		testSearch();
	}

	public static void testNextPk() {

		StudentModel model = new StudentModel();
		try {
			int pk = model.nextPk();
			System.out.println("NextPk : " + pk);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	public static void testAdd() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StudentBean bean = new StudentBean();

		bean.setFirstName("Aniket");
		bean.setLastName("Singh");
		bean.setDob(sdf.parse("2000-11-10"));
		bean.setGender("male");
		bean.setMobileNo("7946554655");
		bean.setEmail("ani@gmail.com");
		bean.setCollegeId(1);
		bean.setCollegeName("SGSITS");
		bean.setCreatedBy("root@gmail.com");
		bean.setModifiedBy("root@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		StudentModel model = new StudentModel();

		try {
			long i = model.add(bean);
			System.out.println("Data Stored in st_student: " + i);
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testUpdate() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StudentBean bean = new StudentBean();

		bean.setId(2);
		bean.setFirstName("Viraj");
		bean.setLastName("Rathi");
		bean.setDob(sdf.parse("2001-11-14"));
		bean.setGender("male");
		bean.setMobileNo("9223322857");
		bean.setEmail("vj@gmail.com");
		bean.setCollegeId(1);
		bean.setCollegeName("SGSITS");
		bean.setCreatedBy("root@gmail.com");
		bean.setModifiedBy("root@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		StudentModel model = new StudentModel();
		try {
			model.update(bean);

			System.out.println("Data Stored in st_student");
		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {

		StudentBean bean = new StudentBean();

		bean.setId(2);

		StudentModel model = new StudentModel();
		try {
			model.delete(bean);

			System.out.println("Data Deleted in st_student");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByPk() {

		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();

		try {

			bean = model.findByPk(1);

			System.out.println("ID : " + bean.getId());
			System.out.println("First Name : " + bean.getFirstName());
			System.out.println("Last Name : " + bean.getLastName());
			System.out.println("DOB : " + bean.getDob());
			System.out.println("Gender : " + bean.getGender());
			System.out.println("Mobile No : " + bean.getMobileNo());
			System.out.println("Email : " + bean.getEmail());
			System.out.println("College ID : " + bean.getCollegeId());
			System.out.println("College Name : " + bean.getCollegeName());
			System.out.println("CreatedBy : " + bean.getCreatedBy());
			System.out.println("ModifiedBy : " + bean.getModifiedBy());
			System.out.println("CreatedDatetime : " + bean.getCreatedDatetime());
			System.out.println("ModifiedDatetime : " + bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByEmail() {

		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();

		try {

			bean = model.findByEmail("ani@gmail.com");

			System.out.println("ID : " + bean.getId());
			System.out.println("First Name : " + bean.getFirstName());
			System.out.println("Last Name : " + bean.getLastName());
			System.out.println("DOB : " + bean.getDob());
			System.out.println("Gender : " + bean.getGender());
			System.out.println("Mobile No : " + bean.getMobileNo());
			System.out.println("Email : " + bean.getEmail());
			System.out.println("College ID : " + bean.getCollegeId());
			System.out.println("College Name : " + bean.getCollegeName());
			System.out.println("CreatedBy : " + bean.getCreatedBy());
			System.out.println("ModifiedBy : " + bean.getModifiedBy());
			System.out.println("CreatedDatetime : " + bean.getCreatedDatetime());
			System.out.println("ModifiedDatetime : " + bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() throws ParseException {

		StudentBean bean = new StudentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setDob(sdf.parse("2001-11-14"));

		try {

			List<StudentBean> list = new ArrayList<StudentBean>();

			StudentModel model = new StudentModel();

			list = model.search(bean, 0, 0);

			Iterator<StudentBean> it = list.iterator();

			while (it.hasNext()) {
				bean = (StudentBean) it.next();
				System.out.println("ID : " + bean.getId());
				System.out.println("First Name : " + bean.getFirstName());
				System.out.println("Last Name : " + bean.getLastName());
				System.out.println("DOB : " + bean.getDob());
				System.out.println("Gender : " + bean.getGender());
				System.out.println("Mobile No : " + bean.getMobileNo());
				System.out.println("Email : " + bean.getEmail());
				System.out.println("College ID : " + bean.getCollegeId());
				System.out.println("College Name : " + bean.getCollegeName());
				System.out.println("CreatedBy : " + bean.getCreatedBy());
				System.out.println("ModifiedBy : " + bean.getModifiedBy());
				System.out.println("CreatedDatetime : " + bean.getCreatedDatetime());
				System.out.println("ModifiedDatetime : " + bean.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
