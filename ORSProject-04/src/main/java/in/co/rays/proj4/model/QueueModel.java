package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.QueueBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class QueueModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		conn = JDBCDataSource.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_queue");
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

	public long add(QueueBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		QueueBean existBean = findByName(bean.getQueueName());

		if (existBean != null) {
			throw new DuplicateRecordException("Queue Name Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_queue values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, pk);
			
			pstmt.setString(2, bean.getListenerCode());
			pstmt.setString(3, bean.getQueueName());
			pstmt.setString(4, bean.getConsumerGroup());
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
			throw new ApplicationException("Exception : Exception in add Queue");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(QueueBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		QueueBean existBean = findByName(bean.getQueueName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Queue Name Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_queue set code = ?, name = ?, c_group = ?, status= ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getListenerCode());
			pstmt.setString(2, bean.getQueueName());
			pstmt.setString(3, bean.getConsumerGroup());
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
			throw new ApplicationException("Exception : Exception in update Queue");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(QueueBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_queue where id = ?");

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
			throw new ApplicationException("Exception : Exception in Deleted Queue");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public QueueBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		QueueBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_queue where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new QueueBean();
				bean.setId(rs.getLong(1));
				bean.setListenerCode(rs.getString(2));
				bean.setQueueName(rs.getString(3));
				bean.setConsumerGroup(rs.getString(4));
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
			throw new ApplicationException("Exception : Exception in getting Queue by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public QueueBean findByName(String name) throws ApplicationException {

		Connection conn = null;
		QueueBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_queue where name = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new QueueBean();
				bean.setId(rs.getLong(1));
				bean.setListenerCode(rs.getString(2));
				bean.setQueueName(rs.getString(3));
				bean.setConsumerGroup(rs.getString(4));
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
			throw new ApplicationException("Exception : Exception in getting Queue by Queue Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<QueueBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<QueueBean> search(QueueBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<QueueBean> list = new ArrayList<QueueBean>();

		StringBuffer sql = new StringBuffer("select * from st_queue where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getListenerCode() != null && bean.getListenerCode().length() > 0) {
				sql.append(" and code like '" + bean.getListenerCode() + "%'");
			}
			if (bean.getQueueName() != null && bean.getQueueName().length() > 0) {
				sql.append(" and name like '" + bean.getQueueName() + "%'");
			}

			if (bean.getConsumerGroup() != null && bean.getConsumerGroup().length() > 0) {
				sql.append(" and c_group like '" + bean.getConsumerGroup() + "%'");
			}

			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" and status like '" + bean.getStatus() + "%'");
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
				bean = new QueueBean();
				bean.setId(rs.getLong(1));
				bean.setListenerCode(rs.getString(2));
				bean.setQueueName(rs.getString(3));
				bean.setConsumerGroup(rs.getString(4));
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
			throw new ApplicationException("Exception : Exception in getting Queue by Search");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
