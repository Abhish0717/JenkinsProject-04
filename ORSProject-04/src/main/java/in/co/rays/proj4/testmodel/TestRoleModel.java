package in.co.rays.proj4.testmodel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.RoleModel;

public class TestRoleModel {

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

		RoleModel model = new RoleModel();
		try {
			int pk = model.nextPk();
			System.out.println("NextPk : " + pk);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	public static void testAdd() {

		RoleBean bean = new RoleBean();

		bean.setName("Student");
		bean.setDescription("B.Tech");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		RoleModel model = new RoleModel();

		try {
			long i = model.add(bean);
			System.out.println("Data Stored in st_role" + i);
		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		RoleBean bean = new RoleBean();

		bean.setId(1);
		bean.setName("Student");
		bean.setDescription("B.Tech CSE(DS)");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		RoleModel model = new RoleModel();

		try {
			try {
				model.update(bean);
				System.out.println("Data Updated in st_role");
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() {

		RoleBean bean = new RoleBean();

		bean.setId(1);

		RoleModel model = new RoleModel();

		try {
			model.delete(bean);
			System.out.println("Data Deleted in st_role");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByPk() {

		RoleModel model = new RoleModel();

		try {
			RoleBean bean = model.findByPk(1L);

			System.out.println("ID : " + bean.getId());
			System.out.println("Name : " + bean.getName());
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

		RoleModel model = new RoleModel();

		try {
			RoleBean bean = model.findByName("Student");

			System.out.println("ID : " + bean.getId());
			System.out.println("Name : " + bean.getName());
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

		RoleBean bean = new RoleBean();
		
		bean.setName("Student");

		RoleModel model = new RoleModel();

		try {
			List<RoleBean> list = new ArrayList<RoleBean>();

			list = model.search(bean, 0, 0);

			Iterator<RoleBean> it = list.iterator();

			while (it.hasNext()) {
				bean = (RoleBean) it.next();

				System.out.println("ID : " + bean.getId());
				System.out.println("Name : " + bean.getName());
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