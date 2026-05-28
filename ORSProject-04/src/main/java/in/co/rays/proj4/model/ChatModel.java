package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.ChatBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class ChatModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		conn = JDBCDataSource.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_chat");
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

	public long add(ChatBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		ChatBean existBean = findByCode(bean.getChatRoomCode());

		if (existBean != null) {
			throw new DuplicateRecordException("Chat Room Code Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_chat values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, pk);

			pstmt.setString(2, bean.getChatRoomCode());
			pstmt.setString(3, bean.getRoomName());
			pstmt.setString(4, bean.getChatCreatedBy());
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
			throw new ApplicationException("Exception : Exception in add Chat");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(ChatBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		ChatBean existBean = findByCode(bean.getChatRoomCode());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Chat Room Code Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_chat set code = ?, name = ?, chat_created_by = ?, status= ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getChatRoomCode());
			pstmt.setString(2, bean.getRoomName());
			pstmt.setString(3, bean.getChatCreatedBy());
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
			throw new ApplicationException("Exception : Exception in update Chat");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(ChatBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_chat where id = ?");

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
			throw new ApplicationException("Exception : Exception in Deleted Chat");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public ChatBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		ChatBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_chat where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ChatBean();
				bean.setId(rs.getLong(1));
				bean.setChatRoomCode(rs.getString(2));
				bean.setRoomName(rs.getString(3));
				bean.setChatCreatedBy(rs.getString(4));
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
			throw new ApplicationException("Exception : Exception in getting Chat by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public ChatBean findByCode(String code) throws ApplicationException {

		Connection conn = null;
		ChatBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_chat where code = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ChatBean();
				bean.setId(rs.getLong(1));
				bean.setChatRoomCode(rs.getString(2));
				bean.setRoomName(rs.getString(3));
				bean.setChatCreatedBy(rs.getString(4));
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
			throw new ApplicationException("Exception : Exception in getting Chat by Chat Code");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<ChatBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<ChatBean> search(ChatBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<ChatBean> list = new ArrayList<ChatBean>();

		StringBuffer sql = new StringBuffer("select * from st_chat where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getChatRoomCode() != null && bean.getChatRoomCode().length() > 0) {
				sql.append(" and code like '" + bean.getChatRoomCode() + "%'");
			}
			if (bean.getRoomName() != null && bean.getRoomName().length() > 0) {
				sql.append(" and name like '" + bean.getRoomName() + "%'");
			}

			if (bean.getChatCreatedBy() != null && bean.getChatCreatedBy().length() > 0) {
				sql.append(" and chat_created_by like '" + bean.getChatCreatedBy() + "%'");
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
				bean = new ChatBean();
				bean.setId(rs.getLong(1));
				bean.setChatRoomCode(rs.getString(2));
				bean.setRoomName(rs.getString(3));
				bean.setChatCreatedBy(rs.getString(4));
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
			throw new ApplicationException("Exception : Exception in getting Chat by Search");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
