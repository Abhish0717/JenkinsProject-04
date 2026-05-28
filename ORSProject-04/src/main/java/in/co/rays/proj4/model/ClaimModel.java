package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.ClaimBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class ClaimModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_claim");
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

	public long add(ClaimBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		ClaimBean existBean = findByNumber(bean.getClaimNumber());

		if (existBean != null) {
			throw new DuplicateRecordException("Account Code Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_claim values(?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getClaimNumber());
			pstmt.setInt(3, bean.getClaimAmount());
			pstmt.setString(4, bean.getStatus());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add Claim");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(ClaimBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		ClaimBean existBean = findByNumber(bean.getClaimNumber());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Account Code Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_claim set number = ?, amount = ?, status = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?;");

			pstmt.setLong(8, bean.getId());
			pstmt.setString(1, bean.getClaimNumber());
			pstmt.setInt(2, bean.getClaimAmount());
			pstmt.setString(3, bean.getStatus());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in update Claim");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(ClaimBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_claim where id = ?");

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
			throw new ApplicationException("Exception : Exception in Deleted Claim");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public ClaimBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		ClaimBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_claim where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ClaimBean();
				bean.setId(rs.getLong(1));
				bean.setClaimNumber(rs.getString(2));
				bean.setClaimAmount(rs.getInt(3));
				bean.setStatus(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Claim by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public ClaimBean findByNumber(String number) throws ApplicationException {

		Connection conn = null;
		ClaimBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_claim where number = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, number);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ClaimBean();
				bean.setId(rs.getLong(1));
				bean.setClaimNumber(rs.getString(2));
				bean.setClaimAmount(rs.getInt(3));
				bean.setStatus(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Claim by Claim Number");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<ClaimBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<ClaimBean> search(ClaimBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<ClaimBean> list = new ArrayList<ClaimBean>();

		StringBuffer sql = new StringBuffer("select * from st_claim where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getClaimNumber() != null && bean.getClaimNumber().length() > 0) {
				sql.append(" and number like '" + bean.getClaimNumber() + "%'");
			}
			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" and status like '" + bean.getStatus() + "%'");
			}
			if (bean.getClaimAmount() > 0) {
				sql.append(" and amount = " + bean.getClaimAmount());
			}
		}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + ", " + pageSize);

			}
		System.out.println("sql ========>" + sql.toString());
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ClaimBean();
				bean.setId(rs.getLong(1));
				bean.setClaimNumber(rs.getString(2));
				bean.setClaimAmount(rs.getInt(3));
				bean.setStatus(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Claim by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
