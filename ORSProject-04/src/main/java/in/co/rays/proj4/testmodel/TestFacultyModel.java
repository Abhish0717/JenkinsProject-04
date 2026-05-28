package in.co.rays.proj4.testmodel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {

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

		try {
			FacultyModel model = new FacultyModel();

			int i = model.nextPk();

			System.out.println("Next PK = " + i);

		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public static void testAdd() throws ParseException {

		try {
			FacultyBean bean = new FacultyBean();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			bean.setFirstName("Rahul");
			bean.setLastName("Sharma");
			bean.setDob(sdf.parse("1990-05-10"));
			bean.setGender("Male");
			bean.setMobileNo("9876543210");
			bean.setEmail("rahul@gmail.com");
			bean.setCollegeId(1);
			bean.setCollegeName("LNCT");
			bean.setCourseId(1);
			bean.setCourseName("BCA");
			bean.setSubjectId(1);
			bean.setSubjectName("Java");
			bean.setCreatedBy("root@gmail.com");
			bean.setModifiedBy("root@gmail.com");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			FacultyModel model = new FacultyModel();

			long pk;
			try {
				pk = model.add(bean);
				System.out.println("Faculty Added Successfully, PK = " + pk);
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws ParseException {

		try {
			FacultyBean bean = new FacultyBean();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			bean.setId(2);
			bean.setFirstName("Amit");
			bean.setLastName("Verma");
			bean.setDob(sdf.parse("1988-07-15"));
			bean.setGender("Male");
			bean.setMobileNo("0789456123");
			bean.setEmail("amit@gmail.com");
			bean.setCollegeId(1);
			bean.setCollegeName("LNCT");
			bean.setCourseId(1);
			bean.setCourseName("BCA");
			bean.setSubjectId(1);
			bean.setSubjectName("Advanced Java");
			bean.setCreatedBy("root@gmail.com");
			bean.setModifiedBy("root@gmail.com");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			FacultyModel model = new FacultyModel();

			try {
				model.update(bean);
				System.out.println("Faculty Updated Successfully");
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() {

		try {
			FacultyBean bean = new FacultyBean();

			bean.setId(3);

			FacultyModel model = new FacultyModel();

			model.delete(bean);

			System.out.println("Faculty Deleted Successfully");

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPk() {

		try {
			FacultyModel model = new FacultyModel();

			FacultyBean bean = model.findByPk(1);

			System.out.println("ID : " + bean.getId());
			System.out.println("First Name : " + bean.getFirstName());
			System.out.println("Last Name : " + bean.getLastName());
			System.out.println("DOB : " + bean.getDob());
			System.out.println("Gender : " + bean.getGender());
			System.out.println("Mobile No : " + bean.getMobileNo());
			System.out.println("Email : " + bean.getEmail());
			System.out.println("College ID : " + bean.getCollegeId());
			System.out.println("College Name : " + bean.getCollegeName());
			System.out.println("Course ID : " + bean.getCourseId());
			System.out.println("Course Name : " + bean.getCourseName());
			System.out.println("Subject ID : " + bean.getSubjectId());
			System.out.println("Subject : " + bean.getSubjectName());
			System.out.println("CreatedBy : " + bean.getCreatedBy());
			System.out.println("ModifiedBy : " + bean.getModifiedBy());
			System.out.println("CreatedDatetime : " + bean.getCreatedDatetime());
			System.out.println("ModifiedDatetime : " + bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByEmail() {

		try {
			FacultyModel model = new FacultyModel();

			FacultyBean bean = model.findByEmail("rahul@gmail.com");

			System.out.println("ID : " + bean.getId());
			System.out.println("First Name : " + bean.getFirstName());
			System.out.println("Last Name : " + bean.getLastName());
			System.out.println("DOB : " + bean.getDob());
			System.out.println("Gender : " + bean.getGender());
			System.out.println("Mobile No : " + bean.getMobileNo());
			System.out.println("Email : " + bean.getEmail());
			System.out.println("College ID : " + bean.getCollegeId());
			System.out.println("College Name : " + bean.getCollegeName());
			System.out.println("Course ID : " + bean.getCourseId());
			System.out.println("Course Name : " + bean.getCourseName());
			System.out.println("Subject ID : " + bean.getSubjectId());
			System.out.println("Subject : " + bean.getSubjectName());
			System.out.println("CreatedBy : " + bean.getCreatedBy());
			System.out.println("ModifiedBy : " + bean.getModifiedBy());
			System.out.println("CreatedDatetime : " + bean.getCreatedDatetime());
			System.out.println("ModifiedDatetime : " + bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() {

		try {
			FacultyBean bean = new FacultyBean();
			
			bean.setFirstName("Rahul");

			FacultyModel model = new FacultyModel();

			List<FacultyBean> list = model.search(bean, 0, 0);

			Iterator<FacultyBean> it = list.iterator();

			while (it.hasNext()) {
				bean = (FacultyBean) it.next();
				System.out.println("ID : " + bean.getId());
				System.out.println("First Name : " + bean.getFirstName());
				System.out.println("Last Name : " + bean.getLastName());
				System.out.println("DOB : " + bean.getDob());
				System.out.println("Gender : " + bean.getGender());
				System.out.println("Mobile No : " + bean.getMobileNo());
				System.out.println("Email : " + bean.getEmail());
				System.out.println("College ID : " + bean.getCollegeId());
				System.out.println("College Name : " + bean.getCollegeName());
				System.out.println("Course ID : " + bean.getCourseId());
				System.out.println("Course Name : " + bean.getCourseName());
				System.out.println("Subject ID : " + bean.getSubjectId());
				System.out.println("Subject : " + bean.getSubjectName());
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