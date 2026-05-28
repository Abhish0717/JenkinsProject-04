package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.FaceBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class FaceModel {

	public Integer nextPk() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		conn = JDBCDataSource.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_face");
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

	public long add(FaceBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		FaceBean existBean = findByCameraId(bean.getCameraId());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Camera ID Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_face values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, pk);

			pstmt.setString(2, bean.getPersonName());
			pstmt.setString(3, bean.getConfidenceScore());
			pstmt.setString(4, bean.getDetectedObject());
			pstmt.setString(5, bean.getCameraId());
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
			throw new ApplicationException("Exception : Exception in add Face");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(FaceBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		FaceBean existBean = findByCameraId(bean.getCameraId());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Camera ID Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_face set name = ?, score = ?, object = ?, c_id= ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getPersonName());
			pstmt.setString(2, bean.getConfidenceScore());
			pstmt.setString(3, bean.getDetectedObject());
			pstmt.setString(4, bean.getCameraId());
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
			throw new ApplicationException("Exception : Exception in update Face");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(FaceBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_face where id = ?");

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
			throw new ApplicationException("Exception : Exception in Deleted Face");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public FaceBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		FaceBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_face where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FaceBean();
				bean.setId(rs.getLong(1));
				bean.setPersonName(rs.getString(2));
				bean.setConfidenceScore(rs.getString(3));
				bean.setDetectedObject(rs.getString(4));
				bean.setCameraId(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Face by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public FaceBean findByCameraId(String CameraId) throws ApplicationException {

		Connection conn = null;
		FaceBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_face where c_id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, CameraId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FaceBean();
				bean.setId(rs.getLong(1));
				bean.setPersonName(rs.getString(2));
				bean.setConfidenceScore(rs.getString(3));
				bean.setDetectedObject(rs.getString(4));
				bean.setCameraId(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Face by Camera ID ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<FaceBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<FaceBean> search(FaceBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<FaceBean> list = new ArrayList<FaceBean>();

		StringBuffer sql = new StringBuffer("select * from st_face where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getPersonName() != null && bean.getPersonName().length() > 0) {
				sql.append(" and name like '" + bean.getPersonName() + "%'");
			}
			if (bean.getConfidenceScore() != null && bean.getConfidenceScore().length() > 0) {
				sql.append(" and score like '" + bean.getConfidenceScore() + "%'");
			}

			if (bean.getDetectedObject() != null && bean.getDetectedObject().length() > 0) {
				sql.append(" and object like '" + bean.getDetectedObject() + "%'");
			}

			if (bean.getCameraId() != null && bean.getCameraId().length() > 0) {
				sql.append(" and c_id like '" + bean.getCameraId() + "%'");
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
				bean = new FaceBean();
				bean.setId(rs.getLong(1));
				bean.setPersonName(rs.getString(2));
				bean.setConfidenceScore(rs.getString(3));
				bean.setDetectedObject(rs.getString(4));
				bean.setCameraId(rs.getString(5));
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
			throw new ApplicationException("Exception : Exception in getting Face by Search");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
