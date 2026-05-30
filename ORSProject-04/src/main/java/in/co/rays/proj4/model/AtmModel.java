package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.AtmBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class AtmModel {

	public Integer nextPk() throws DatabaseException, SQLException {

		Connection conn = null;
		int pk = 0;

		conn = JDBCDataSource.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_atm");
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

	public long add(AtmBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		AtmBean existBean = findByName(bean.getBankName());

		if (existBean != null) {
			throw new DuplicateRecordException("Bank Name Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_atm values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getBankName());
			pstmt.setString(3, bean.getLocation());
			pstmt.setLong(4, bean.getCashAvailable());
			pstmt.setInt(5, bean.getSecurityCode());
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
			throw new ApplicationException("Exception : Exception in add Atm system");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(AtmBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		AtmBean existBean = findByName(bean.getBankName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Bank Name Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_atm set name = ?, location = ?, cash = ?, code = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getBankName());
			pstmt.setString(2, bean.getLocation());
			pstmt.setLong(3, bean.getCashAvailable());
			pstmt.setInt(4, bean.getSecurityCode());
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
			throw new ApplicationException("Exception : Exception in update Atm system");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(AtmBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_atm where id = ?");

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
			throw new ApplicationException("Exception : Exception in Deleted Atm system");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public AtmBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		AtmBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_atm where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AtmBean();
				bean.setId(rs.getLong(1));
				bean.setBankName(rs.getString(2));
				bean.setLocation(rs.getString(3));
				bean.setCashAvailable(rs.getLong(4));
				bean.setSecurityCode(rs.getInt(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Atm by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public AtmBean findByName(String name) throws ApplicationException {

		Connection conn = null;
		AtmBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_atm where name = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AtmBean();
				bean.setId(rs.getLong(1));
				bean.setBankName(rs.getString(2));
				bean.setLocation(rs.getString(3));
				bean.setCashAvailable(rs.getLong(4));
				bean.setSecurityCode(rs.getInt(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Atm system by code");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<AtmBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<AtmBean> search(AtmBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<AtmBean> list = new ArrayList<AtmBean>();

		StringBuffer sql = new StringBuffer("select * from st_atm where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getBankName() != null && bean.getBankName().length() > 0) {
				sql.append(" and name like '" + bean.getBankName() + "%'");
			}
			if (bean.getLocation() != null && bean.getLocation().length() > 0) {
				sql.append(" and location like '" + bean.getLocation() + "%'");
			}

			if (bean.getCashAvailable() > 0) {
				sql.append(" and cash = '" + bean.getCashAvailable() + "'");
			}

			if (bean.getSecurityCode() > 0) {
				sql.append(" and code = '" + bean.getSecurityCode() + "'");
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
				bean = new AtmBean();
				bean.setId(rs.getLong(1));
				bean.setBankName(rs.getString(2));
				bean.setLocation(rs.getString(3));
				bean.setCashAvailable(rs.getLong(4));
				bean.setSecurityCode(rs.getInt(5));
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
			throw new ApplicationException("Exception : Exception in getting Atm system by Search");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
