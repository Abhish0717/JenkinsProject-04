package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.AccountBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class AccountModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		conn = JDBCDataSource.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_account");
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

	public long add(AccountBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		AccountBean existBean = findByCode(bean.getAccountCode());

		if (existBean != null) {
			throw new DuplicateRecordException("Account Code Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_account values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, pk);

			pstmt.setString(2, bean.getAccountCode());
			pstmt.setString(3, bean.getAccountName());
			pstmt.setString(4, bean.getAccountType());
			pstmt.setString(5, bean.getStatus());
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
			throw new ApplicationException("Exception : Exception in add Account");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(AccountBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		AccountBean existBean = findByCode(bean.getAccountCode());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Account Code Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_account set code = ?, name = ?, type = ?, status= ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getAccountCode());
			pstmt.setString(2, bean.getAccountName());
			pstmt.setString(3, bean.getAccountType());
			pstmt.setString(4, bean.getStatus());
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
			throw new ApplicationException("Exception : Exception in update Account");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(AccountBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_account where id = ?");

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
			throw new ApplicationException("Exception : Exception in Deleted Account");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public AccountBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		AccountBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_account where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AccountBean();
				bean.setId(rs.getLong(1));
				bean.setAccountCode(rs.getString(2));
				bean.setAccountName(rs.getString(3));
				bean.setAccountType(rs.getString(4));
				bean.setStatus(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Account by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public AccountBean findByCode(String code) throws ApplicationException {

		Connection conn = null;
		AccountBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_account where code = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AccountBean();
				bean.setId(rs.getLong(1));
				bean.setAccountCode(rs.getString(2));
				bean.setAccountName(rs.getString(3));
				bean.setAccountType(rs.getString(4));
				bean.setStatus(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Account by Account Code");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<AccountBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<AccountBean> search(AccountBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<AccountBean> list = new ArrayList<AccountBean>();

		StringBuffer sql = new StringBuffer("select * from st_account where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getAccountCode() != null && bean.getAccountCode().length() > 0) {
				sql.append(" and code like '" + bean.getAccountCode() + "%'");
			}
			if (bean.getAccountName() != null && bean.getAccountName().length() > 0) {
				sql.append(" and name like '" + bean.getAccountName() + "%'");
			}

			if (bean.getAccountType() != null && bean.getAccountType().length() > 0) {
				sql.append(" and type like '" + bean.getAccountType() + "%'");
			}

			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" and status like '" + bean.getStatus() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}
		System.out.println(sql.toString());

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AccountBean();
				bean.setId(rs.getLong(1));
				bean.setAccountCode(rs.getString(2));
				bean.setAccountName(rs.getString(3));
				bean.setAccountType(rs.getString(4));
				bean.setStatus(rs.getString(5));
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
			throw new ApplicationException("Exception : Exception in getting Account by Search");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
