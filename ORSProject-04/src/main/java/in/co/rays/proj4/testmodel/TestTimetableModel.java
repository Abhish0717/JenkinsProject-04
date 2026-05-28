package in.co.rays.proj4.testmodel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.TimeTableBean;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.model.TimeTableModel;

public class TestTimetableModel {

	public static void main(String[] args) {

//		testNextPk();
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
		testSearch();

	}

	public static void testNextPk() {

		TimeTableModel model = new TimeTableModel();
		try {
			int i = model.nextPk();
			System.out.println("NextPk : " + i);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public static void testAdd() {

		try {
			TimeTableBean bean = new TimeTableBean();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			bean.setSemester("3rd");
			bean.setDescription("Mid Exam");
			bean.setExamDate(sdf.parse("2026-03-15"));
			bean.setExamTime("10:00 AM");
			bean.setCourseId(1);
			bean.setCourseName("B.Tech");
			bean.setSubjectId(1);
			bean.setSubjectName("Java");
			bean.setCreatedBy("root@gmail.com");
			bean.setModifiedBy("root@gmail.com");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			TimeTableModel model = new TimeTableModel();

			long pk = model.add(bean);

			System.out.println("Timetable Added Successfully, PK = " + pk);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		try {
			TimeTableBean bean = new TimeTableBean();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			bean.setId(2);
			bean.setSemester("4th");
			bean.setDescription("Final Exam");
			bean.setExamDate(sdf.parse("2026-04-20"));
			bean.setExamTime("2:00 PM");
			bean.setCourseId(1);
			bean.setCourseName("BCA");
			bean.setSubjectId(1);
			bean.setSubjectName("Advanced Java");
			bean.setCreatedBy("root@gmail.com");
			bean.setModifiedBy("root@gmail.com");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			TimeTableModel model = new TimeTableModel();

			model.update(bean);

			System.out.println("Timetable Updated Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() {

		try {

			TimeTableBean bean = new TimeTableBean();

			bean.setId(3);

			TimeTableModel model = new TimeTableModel();

			model.delete(bean);

			System.out.println("Timetable Deleted Successfully");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void testFindByPk() {

		try {

			TimeTableModel model = new TimeTableModel();

			TimeTableBean bean = model.findByPk(2);

			System.out.println("ID : " + bean.getId());
			System.out.println("Semester : " + bean.getSemester());
			System.out.println("Description : " + bean.getDescription());
			System.out.println("Exam Date : " + bean.getExamDate());
			System.out.println("Exam Time : " + bean.getExamTime());
			System.out.println("Course ID : "+bean.getCourseId());
			System.out.println("Course Name : " + bean.getCourseName());
			System.out.println("Subject ID : "+bean.getSubjectId());
			System.out.println("Subject Name : " + bean.getSubjectName());
			System.out.println("CreatedBy : " + bean.getCreatedBy());
			System.out.println("ModifiedBy : " + bean.getModifiedBy());
			System.out.println("CreatedDatetime : " + bean.getCreatedDatetime());
			System.out.println("ModifiedDatetime : " + bean.getModifiedDatetime());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() {

		try {
			TimeTableBean bean = new TimeTableBean();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
//			bean.setSemester("3rd");
			bean.setExamDate(sdf.parse("2026-04-20"));

			TimeTableModel model = new TimeTableModel();

			List<TimeTableBean> list = model.search(bean, 0, 0);

			Iterator<TimeTableBean> it = list.iterator();

			while (it.hasNext()) {

				bean = (TimeTableBean) it.next();

				System.out.println("ID : " + bean.getId());
				System.out.println("Semester : " + bean.getSemester());
				System.out.println("Description : " + bean.getDescription());
				System.out.println("Exam Date : " + bean.getExamDate());
				System.out.println("Exam Time : " + bean.getExamTime());
				System.out.println("Course ID : "+bean.getCourseId());
				System.out.println("Course Name : " + bean.getCourseName());
				System.out.println("Subject ID : "+bean.getSubjectId());
				System.out.println("Subject Name : " + bean.getSubjectName());
				System.out.println("CreatedBy : " + bean.getCreatedBy());
				System.out.println("ModifiedBy : " + bean.getModifiedBy());
				System.out.println("CreatedDatetime : " + bean.getCreatedDatetime());
				System.out.println("ModifiedDatetime : " + bean.getModifiedDatetime());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}