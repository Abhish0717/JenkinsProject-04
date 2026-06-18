package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.CourierBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class CourierModel extends BaseModel<CourierBean> {

	@Override
	public long add(CourierBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		CourierBean existbean = findByName(bean.getTrackingNumber());

		if (existbean != null) {
			throw new DuplicateRecordException("Tracking Number already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("insert into st_api values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getTrackingNumber());
			pstmt.setString(3, bean.getSenderName());
			pstmt.setString(4, bean.getReceiverName());
			pstmt.setString(5, bean.getDeliveryStatus());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Courier Tracking");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	@Override
	public void update(CourierBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		CourierBean existbean = findByName(bean.getTrackingNumber());

		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Tracking Number already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction`
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_api set tracking = ?, sender = ?, receiver = ?, status = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getTrackingNumber());
			pstmt.setString(2, bean.getSenderName());
			pstmt.setString(3, bean.getReceiverName());
			pstmt.setString(4, bean.getDeliveryStatus());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : Update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in Updated Courier Tracking");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public CourierBean findByName(String trackingName) throws ApplicationException {
		return findByUniqueColumn("tracking", trackingName);
	}

	@Override
	public String getWhereClause(CourierBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getTrackingNumber() != null && bean.getTrackingNumber().length() > 0) {
				sql.append(" and tracking like '" + bean.getTrackingNumber() + "%'");
			}
			if (bean.getSenderName() != null && bean.getSenderName().length() > 0) {
				sql.append(" and sender like '" + bean.getSenderName() + "%'");
			}
			if (bean.getReceiverName() != null && bean.getReceiverName().length() > 0) {
				sql.append(" and receiver like '" + bean.getReceiverName() + "%'");
			}
			if (bean.getDeliveryStatus() != null && bean.getDeliveryStatus().length() > 0) {
				sql.append(" and status like '" + bean.getDeliveryStatus() + "%'");
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_courier";
	}

	@Override
	public CourierBean getBean() {
		return new CourierBean();
	}

}