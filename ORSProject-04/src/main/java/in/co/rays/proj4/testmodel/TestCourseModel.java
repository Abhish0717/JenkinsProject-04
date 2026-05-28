package in.co.rays.proj4.testmodel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CourseModel;

public class TestCourseModel {

	public static void main(String[] args) {

//		testNextPk();
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByName();
		testSearch();

	}

	public static void testNextPk() {

		CourseModel model = new CourseModel();
		try {
			int i = model.nextPk();
			System.out.println("NextPk : " + i);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	public static void testAdd() {

		try {
			CourseBean bean = new CourseBean();

			bean.setName("Java Full Stack");
			bean.setDuration("6 Months");
			bean.setDescription("Java, JSP, Servlet, Spring");
			bean.setCreatedBy("root@gmail.com");
			bean.setModifiedBy("root@gmail.com");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			CourseModel model = new CourseModel();

			try {
				long pk = model.add(bean);

				System.out.println("Course added successfully, PK = " + pk);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		try {
			CourseBean bean = new CourseBean();

			bean.setId(2);
			bean.setName("Java Advanced");
			bean.setDuration("8 Months");
			bean.setDescription("Advanced Java Course");
			bean.setCreatedBy("root@gmail.com");
			bean.setModifiedBy("root@gmail.com");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			CourseModel model = new CourseModel();

			try {
				model.update(bean);
				System.out.println("Course Updated Successfully");
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {

		CourseBean bean = new CourseBean();

		bean.setId(1);

		try {
			CourseModel model = new CourseModel();

			model.delete(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		System.out.println("Course Deleted Successfully");
	}

	public static void testFindByPk() {

		CourseModel model = new CourseModel();

		try {
			CourseBean bean = model.findByPk(1);

			System.out.println("ID : " + bean.getId());
			System.out.println("Name : " + bean.getName());
			System.out.println("Duration : " + bean.getDuration());
			System.out.println("Description : " + bean.getDescription());
			System.out.println("CreatedBy : " + bean.getCreatedBy());
			System.out.println("ModifiedBy : " + bean.getModifiedBy());
			System.out.println("CreatedDatetime : " + bean.getCreatedDatetime());
			System.out.println("ModifiedDatetime : " + bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByName() {

		CourseModel model = new CourseModel();

		try {
			CourseBean bean = model.findByName("Java Full Stack");

			System.out.println("ID : " + bean.getId());
			System.out.println("Name : " + bean.getName());
			System.out.println("Duration : " + bean.getDuration());
			System.out.println("Description : " + bean.getDescription());
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
			CourseBean bean = new CourseBean();
			
			bean.setName("Java Advanced");

			CourseModel model = new CourseModel();

			List<CourseBean> list = new ArrayList<CourseBean>();

			list = model.search(bean, 0, 0);

			Iterator<CourseBean> it = list.iterator();

			while (it.hasNext()) {
				bean = (CourseBean) it.next();
				System.out.println("ID : " + bean.getId());
				System.out.println("Name : " + bean.getName());
				System.out.println("Duration : " + bean.getDuration());
				System.out.println("Description : " + bean.getDescription());
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
