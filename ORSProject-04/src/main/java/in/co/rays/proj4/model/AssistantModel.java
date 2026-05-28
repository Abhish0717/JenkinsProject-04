package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.AssistantBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class AssistantModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_assistant");
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

	public long add(AssistantBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		AssistantBean existBean = findByUser(bean.getUserVoice());

		if (existBean != null) {
			throw new DuplicateRecordException("User Voice Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_assistant values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getUserVoice());
			pstmt.setString(3, bean.getResponse());
			pstmt.setString(4, bean.getLanguage());
			pstmt.setInt(5, bean.getAccuracy());
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
			throw new ApplicationException("Exception : Exception in add Assistant");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(AssistantBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		AssistantBean existBean = findByUser(bean.getUserVoice());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("User Voice Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_assistant set user = ?, response = ?, language = ?, accuracy = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?;");

			pstmt.setLong(9, bean.getId());
			pstmt.setString(1, bean.getUserVoice());
			pstmt.setString(2, bean.getResponse());
			pstmt.setString(3, bean.getLanguage());
			pstmt.setInt(4, bean.getAccuracy());
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
			throw new ApplicationException("Exception : Exception in update Assistant");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(AssistantBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_assistant where id = ?");

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
			throw new ApplicationException("Exception : Exception in Deleted Assistant");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public AssistantBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		AssistantBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_assistant where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AssistantBean();
				bean.setId(rs.getLong(1));
				bean.setUserVoice(rs.getString(2));
				bean.setResponse(rs.getString(3));
				bean.setLanguage(rs.getString(4));
				bean.setAccuracy(rs.getInt(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Assistant by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public AssistantBean findByUser(String user) throws ApplicationException {

		Connection conn = null;
		AssistantBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_assistant where user = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AssistantBean();
				bean.setId(rs.getLong(1));
				bean.setUserVoice(rs.getString(2));
				bean.setResponse(rs.getString(3));
				bean.setLanguage(rs.getString(4));
				bean.setAccuracy(rs.getInt(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Assistant by User Voice");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<AssistantBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<AssistantBean> search(AssistantBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<AssistantBean> list = new ArrayList<AssistantBean>();

		StringBuffer sql = new StringBuffer("select * from st_assistant where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getUserVoice() != null && bean.getUserVoice().length() > 0) {
				sql.append(" and user like '" + bean.getUserVoice() + "%'");
			}
			if (bean.getResponse() != null && bean.getResponse().length() > 0) {
				sql.append(" and response like '" + bean.getResponse() + "%'");
			}
			if (bean.getLanguage() != null && bean.getLanguage().length() > 0) {
				sql.append(" and language like '" + bean.getLanguage() + "%'");
			}
			if (bean.getAccuracy() > 0) {
				sql.append(" and accuracy = " + bean.getAccuracy());
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
				bean = new AssistantBean();
				bean.setId(rs.getLong(1));
				bean.setUserVoice(rs.getString(2));
				bean.setResponse(rs.getString(3));
				bean.setLanguage(rs.getString(4));
				bean.setAccuracy(rs.getInt(5));
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
			throw new ApplicationException("Exception : Exception in getting Assistant by Search");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
