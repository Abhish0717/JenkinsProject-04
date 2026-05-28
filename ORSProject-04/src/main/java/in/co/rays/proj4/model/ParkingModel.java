package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.ParkingBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class ParkingModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_parking");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public long add(ParkingBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		ParkingBean existBean = findBySlot(bean.getSlotNumber ());

		if (existBean != null) {
			throw new DuplicateRecordException("Slot Number Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_parking values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getVehicleNumber ());
			pstmt.setString(3, bean.getSlotNumber ());
			pstmt.setDouble(4, bean.getParkingCharge());
			pstmt.setString(5, bean.getEntryTime());
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
			throw new ApplicationException("Exception : Exception in add Parking");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}


	public void update(ParkingBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		ParkingBean existBean = findBySlot(bean.getSlotNumber ());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Slot Number Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_parking set number = ?, slot = ?, charge = ?, time = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?;");

			pstmt.setLong(9, bean.getId());
			pstmt.setString(1, bean.getVehicleNumber ());
			pstmt.setString(2, bean.getSlotNumber ());
			pstmt.setDouble(3, bean.getParkingCharge());
			pstmt.setString(4, bean.getEntryTime());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
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
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in update Parking");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(ParkingBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_parking where id = ?");

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
			throw new ApplicationException("Exception : Exception in Deleted Parking");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public ParkingBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		ParkingBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_parking where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ParkingBean();
				bean.setId(rs.getLong(1));
				bean.setVehicleNumber (rs.getString(2));
				bean.setSlotNumber (rs.getString(3));
				bean.setParkingCharge(rs.getLong(4));
				bean.setEntryTime(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Parking by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public ParkingBean findBySlot(String slot) throws ApplicationException {

		Connection conn = null;
		ParkingBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_parking where slot = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, slot);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ParkingBean();
				bean.setId(rs.getLong(1));
				bean.setVehicleNumber (rs.getString(2));
				bean.setSlotNumber (rs.getString(3));
				bean.setParkingCharge(rs.getLong(4));
				bean.setEntryTime(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Parking by Slot Number");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<ParkingBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<ParkingBean> search(ParkingBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<ParkingBean> list = new ArrayList<ParkingBean>();

		StringBuffer sql = new StringBuffer("select * from st_parking where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getVehicleNumber () != null && bean.getVehicleNumber ().length() > 0) {
				sql.append(" and number like '" + bean.getVehicleNumber () + "%'");
			}
			if (bean.getSlotNumber () != null && bean.getSlotNumber ().length() > 0) {
				sql.append(" and slot like '" + bean.getSlotNumber () + "%'");
			}
			if (bean.getParkingCharge() > 0) {
				sql.append(" and charge = " + bean.getParkingCharge());
			}
			if (bean.getEntryTime() != null && bean.getEntryTime ().length() > 0) {
				sql.append(" and time like '" + bean.getEntryTime() + "%'");
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
				bean = new ParkingBean();
				bean.setId(rs.getLong(1));
				bean.setVehicleNumber (rs.getString(2));
				bean.setSlotNumber (rs.getString(3));
				bean.setParkingCharge(rs.getLong(4));
				bean.setEntryTime(rs.getString(5));
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
			throw new ApplicationException("Exception : Exception in getting Parking by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
