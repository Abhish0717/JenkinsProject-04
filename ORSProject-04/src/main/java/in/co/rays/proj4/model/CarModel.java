package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CarBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CarModel {

	public Integer nextPk() throws DatabaseException, SQLException {

		Connection conn = null;
		int pk = 0;

		conn = JDBCDataSource.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_car");
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

	public long add(CarBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		CarBean existBean = findByName(bean.getCustomerName());

		if (existBean != null) {
			throw new DuplicateRecordException("Customer Name Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_car values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getCustomerName());
			pstmt.setString(3, bean.getCarModel());
			pstmt.setLong(4, bean.getRentPerDay());
			pstmt.setString(5, bean.getFuelType());
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
			throw new ApplicationException("Exception : Exception in add Car Rental");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(CarBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		CarBean existBean = findByName(bean.getCustomerName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Customer Name Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_car set name = ?, model = ?, rent = ?, fuel = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getCustomerName());
			pstmt.setString(2, bean.getCarModel());
			pstmt.setLong(3, bean.getRentPerDay());
			pstmt.setString(4, bean.getFuelType());
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
			throw new ApplicationException("Exception : Exception in update Car Rental");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(CarBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_car where id = ?");

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
			throw new ApplicationException("Exception : Exception in Deleted Car Rental");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public CarBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		CarBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_car where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CarBean();
				bean.setId(rs.getLong(1));
				bean.setCustomerName(rs.getString(2));
				bean.setCarModel(rs.getString(3));
				bean.setRentPerDay(rs.getLong(4));
				bean.setFuelType(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Car Rental by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public CarBean findByName(String name) throws ApplicationException {

		Connection conn = null;
		CarBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_car where name = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CarBean();
				bean.setId(rs.getLong(1));
				bean.setCustomerName(rs.getString(2));
				bean.setCarModel(rs.getString(3));
				bean.setRentPerDay(rs.getLong(4));
				bean.setFuelType(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Car Rental by Customer Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<CarBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<CarBean> search(CarBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<CarBean> list = new ArrayList<CarBean>();

		StringBuffer sql = new StringBuffer("select * from st_car where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getCustomerName() != null && bean.getCustomerName().length() > 0) {
				sql.append(" and name like '" + bean.getCustomerName() + "%'");
			}
			if (bean.getCarModel() != null && bean.getCarModel().length() > 0) {
				sql.append(" and model like '" + bean.getCarModel() + "%'");
			}

			if (bean.getRentPerDay() > 0) {
				sql.append(" and rent = '" + bean.getRentPerDay() + "'");
			}

			if (bean.getFuelType() != null && bean.getFuelType().length() > 0) {
				sql.append(" and fuel like '" + bean.getFuelType() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}
		System.out.println("sql = "+ sql.toString());

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CarBean();
				bean.setId(rs.getLong(1));
				bean.setCustomerName(rs.getString(2));
				bean.setCarModel(rs.getString(3));
				bean.setRentPerDay(rs.getLong(4));
				bean.setFuelType(rs.getString(5));
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
			throw new ApplicationException("Exception : Exception in getting Car Rental by Search");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
