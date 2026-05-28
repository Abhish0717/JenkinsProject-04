package in.co.rays.proj4.testmodel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.MarksheetModel;

public class TestMarksheetModel {

	public static void main(String[] args) {

//		testNextPk();
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByRollNo();
		testSearch();
	}

	public static void testNextPk() {

		MarksheetModel model = new MarksheetModel();
		try {
			int i = model.nextPk();
			System.out.println("NextPk : " + i);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	public static void testAdd() {

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo("101");
		bean.setStudentId(1);
		bean.setName("Ankit Jain");
		bean.setPhysics(74);
		bean.setChemistry(96);
		bean.setMaths(88);
		bean.setCreatedBy("root@gmail.com");
		bean.setModifiedBy("root@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		
			MarksheetModel model = new MarksheetModel();
			
			try {
				long i = model.add(bean);
				System.out.println("Add method : " + i);
			} catch (ApplicationException | DuplicateRecordException e) {
				e.printStackTrace();
			}

		

	}

	public static void testUpdate() {

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo("101");
		bean.setStudentId(1);
		bean.setName("Vinay Rawat");
		bean.setPhysics(74);
		bean.setChemistry(96);
		bean.setMaths(88);
		bean.setCreatedBy("root@gmail.com");
		bean.setModifiedBy("root@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		try {
			MarksheetModel model = new MarksheetModel();
			try {
				model.update(bean);
				System.out.println("Data Updated Successfully in st_marksheet");
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
			}
		} catch (ApplicationException e) {

			e.printStackTrace();
		}

	}

	public static void testDelete() {

		MarksheetBean bean = new MarksheetBean();
		bean.setId(2);
		try {
			MarksheetModel model = new MarksheetModel();
			model.delete(bean);
			System.out.println("Data Deleted Successfully in st_marksheet");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPk() {

		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = new MarksheetBean();

		try {

			bean = model.findByPk(1);

			System.out.println("ID : " + bean.getId());
			System.out.println("Roll No. : " + bean.getRollNo());
			System.out.println("Student ID : " + bean.getStudentId());
			System.out.println("Name : " + bean.getName());
			System.out.println("Physics : " + bean.getPhysics());
			System.out.println("Chemistry : " + bean.getChemistry());
			System.out.println("Maths : " + bean.getMaths());
			System.out.println("CreatedBy : " + bean.getCreatedBy());
			System.out.println("ModifiedBy : " + bean.getModifiedBy());
			System.out.println("CreatedDatetime : " + bean.getCreatedDatetime());
			System.out.println("ModifiedDatetime : " + bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByRollNo() {

		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = new MarksheetBean();

		try {

			bean = model.findByRollNo("101");

			System.out.println("ID : " + bean.getId());
			System.out.println("Roll No. : " + bean.getRollNo());
			System.out.println("Student ID : " + bean.getStudentId());
			System.out.println("Name : " + bean.getName());
			System.out.println("Physics : " + bean.getPhysics());
			System.out.println("Chemistry : " + bean.getChemistry());
			System.out.println("Maths : " + bean.getMaths());
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
			MarksheetBean bean = new MarksheetBean();
			
			bean.setName("Ankit Jain");

			MarksheetModel model = new MarksheetModel();

			List<MarksheetBean> list = new ArrayList<MarksheetBean>();

			list = model.search(bean, 0, 0);

			Iterator<MarksheetBean> it = list.iterator();

			while (it.hasNext()) {
				bean = (MarksheetBean) it.next();
				System.out.println("ID : " + bean.getId());
				System.out.println("Roll No. : " + bean.getRollNo());
				System.out.println("Student ID : " + bean.getStudentId());
				System.out.println("Name : " + bean.getName());
				System.out.println("Physics : " + bean.getPhysics());
				System.out.println("Chemistry : " + bean.getChemistry());
				System.out.println("Maths : " + bean.getMaths());
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
