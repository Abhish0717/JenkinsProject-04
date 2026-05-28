package in.co.rays.proj4.testmodel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.UserModel;

public class TestUserModel {

	public static void main(String[] args) throws ParseException {

//		testNextPk();
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testSearch();

	}

	public static void testNextPk() {

		UserModel model = new UserModel();
		try {
			int i = model.nextPk();
			System.out.println("NextPk : " + i);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public static void testAdd() throws ParseException {

		UserBean bean = new UserBean();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("Abhishish");
		bean.setLastName("Bhawsar");
		bean.setLogin("ramg@gmail.com");
		bean.setPassword("123");
		bean.setDob(sdf.parse("1999-11-19"));
		bean.setMobileNo("9876543210");
		bean.setRoleId(1);
		bean.setGender("Male");
		

		UserModel model = new UserModel();

		try {
			long pk = model.add(bean);

			System.out.println("User added with PK = " + pk);

		} catch (ApplicationException e) {

			System.out.println(e);

		} catch (DuplicateRecordException e) {

			System.out.println(e);
		}

	}

	public static void testUpdate() throws ParseException {

		try {
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			bean.setFirstName("Abhishek");
			bean.setLastName("Bhawsar");
			bean.setLogin("abhi@gmail.com");
			bean.setPassword("123");
			bean.setDob(sdf.parse("1999-11-19"));
			bean.setMobileNo("9876543210");
			bean.setRoleId(1);
			bean.setGender("Male");
			bean.setCreatedBy("root");
			bean.setModifiedBy("root");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			UserModel model = new UserModel();

			model.update(bean);
			System.out.println("User Updated in st_user");

		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {

		UserBean bean = new UserBean();

		bean.setId(3);

		UserModel model = new UserModel();

		try {
			model.delete(bean);
			System.out.println("User Deleted in st_user");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByPk() throws Exception {

		UserModel model = new UserModel();

		UserBean bean = model.findByPk(1);

		System.out.println("ID : " + bean.getId());
		System.out.println("First Name : " + bean.getFirstName());
		System.out.println("Last Name : " + bean.getLastName());
		System.out.println("Login : " + bean.getLogin());
		System.out.println("Password : " + bean.getPassword());
		System.out.println("DOB : " + bean.getDob());
		System.out.println("Mobile No : " + bean.getMobileNo());
		System.out.println("Role ID : " + bean.getRoleId());
		System.out.println("Gender : " + bean.getGender());
		System.out.println("CreatedBy : " + bean.getCreatedBy());
		System.out.println("ModifiedBy : " + bean.getModifiedBy());
		System.out.println("CreatedDatetime : " + bean.getCreatedDatetime());
		System.out.println("ModifiedDatetime : " + bean.getModifiedDatetime());

	}

	public static void testFindBylogin() {

		UserModel model = new UserModel();

		try {
			UserBean bean = model.findByLogin("abhi@gmail.com");

			System.out.println("ID : " + bean.getId());
			System.out.println("First Name : " + bean.getFirstName());
			System.out.println("Last Name : " + bean.getLastName());
			System.out.println("Login : " + bean.getLogin());
			System.out.println("Password : " + bean.getPassword());
			System.out.println("DOB : " + bean.getDob());
			System.out.println("Mobile No : " + bean.getMobileNo());
			System.out.println("Role ID : " + bean.getRoleId());
			System.out.println("Gender : " + bean.getGender());
			System.out.println("CreatedBy : " + bean.getCreatedBy());
			System.out.println("ModifiedBy : " + bean.getModifiedBy());
			System.out.println("CreatedDatetime : " + bean.getCreatedDatetime());
			System.out.println("ModifiedDatetime : " + bean.getModifiedDatetime());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() {

		UserBean bean = new UserBean();

		bean.setFirstName("Abhishish");

		UserModel model = new UserModel();

		try {
			List<UserBean> list = model.search(bean, 0, 0);

			Iterator<UserBean> it = list.iterator();

			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.println("ID : " + bean.getId());
				System.out.println("First Name : " + bean.getFirstName());
				System.out.println("Last Name : " + bean.getLastName());
				System.out.println("Login : " + bean.getLogin());
				System.out.println("Password : " + bean.getPassword());
				System.out.println("DOB : " + bean.getDob());
				System.out.println("Mobile No : " + bean.getMobileNo());
				System.out.println("Role ID : " + bean.getRoleId());
				System.out.println("Gender : " + bean.getGender());
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