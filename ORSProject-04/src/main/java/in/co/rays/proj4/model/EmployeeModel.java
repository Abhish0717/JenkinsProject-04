package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.EmployeeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class EmployeeModel {

	public Integer nextPk() throws DatabaseException, SQLException {

		Connection conn = null;
		int pk = 0;

		conn = JDBCDataSource.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_employee");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException("Exception : Exception in getting Pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public long add(EmployeeBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		EmployeeBean existBean = findByName(bean.getEmployeeName());

		if (existBean != null) {
			throw new DuplicateRecordException("Employee Name Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_employee values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getEmployeeName());
			pstmt.setString(3, bean.getDepartment());
			pstmt.setLong(4, bean.getSalary());
			pstmt.setLong(5, bean.getBonus());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println(i + " Query OK, The rows affected (0.02 sec)" + "\n"
					+ "Records: Added successfully Duplicates: 0  Warnings: 0");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Employee");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(EmployeeBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		EmployeeBean existBean = findByName(bean.getEmployeeName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Employee Name Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_employee set name = ?, department = ?, salary = ?, bonus = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getEmployeeName());
			pstmt.setString(2, bean.getDepartment());
			pstmt.setLong(3, bean.getSalary());
			pstmt.setLong(4, bean.getBonus());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println(i + " Query OK, The rows affected (0.02 sec)" + "\n"
					+ "Records: Updated successfully Duplicates: 0  Warnings: 0");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in update Employee");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(EmployeeBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_employee where id = ?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println(i + " Query OK, The rows affected (0.02 sec)" + "\n"
					+ "Records: Deleted successfully Duplicates: 0  Warnings: 0");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Deleted rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in Deleted Employee");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public EmployeeBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		EmployeeBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_employee where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new EmployeeBean();
				bean.setId(rs.getLong(1));
				bean.setEmployeeName(rs.getString(2));
				bean.setDepartment(rs.getString(3));
				bean.setSalary(rs.getLong(4));
				bean.setBonus(rs.getLong(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Employee by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public EmployeeBean findByName(String name) throws ApplicationException {

		Connection conn = null;
		EmployeeBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_employee where name = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new EmployeeBean();
				bean.setId(rs.getLong(1));
				bean.setEmployeeName(rs.getString(2));
				bean.setDepartment(rs.getString(3));
				bean.setSalary(rs.getLong(4));
				bean.setBonus(rs.getLong(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Employee by Employee Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<EmployeeBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<EmployeeBean> search(EmployeeBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<EmployeeBean> list = new ArrayList<EmployeeBean>();

		StringBuffer sql = new StringBuffer("select * from st_employee where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getEmployeeName() != null && bean.getEmployeeName().length() > 0) {
				sql.append(" and name like '" + bean.getEmployeeName() + "%'");
			}
			if (bean.getDepartment() != null && bean.getDepartment().length() > 0) {
				sql.append(" and department like '" + bean.getDepartment() + "%'");
			}

			if (bean.getSalary() > 0) {
				sql.append(" and salary = '" + bean.getSalary() + "'");
			}

			if (bean.getBonus() > 0) {
				sql.append(" and bonus = '" + bean.getBonus() + "'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new EmployeeBean();
				bean.setId(rs.getLong(1));
				bean.setEmployeeName(rs.getString(2));
				bean.setDepartment(rs.getString(3));
				bean.setSalary(rs.getLong(4));
				bean.setBonus(rs.getLong(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Employee by Search");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
