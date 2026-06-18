package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.PaymentBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class PaymentModel extends BaseModel<PaymentBean> {

	@Override
	public long add(PaymentBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		PaymentBean existbean = findByOrder(bean.getOrderId());

		if (existbean != null) {
			throw new DuplicateRecordException("Order ID already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getOrderId());
			pstmt.setLong(3, bean.getAmount());
			pstmt.setString(4, bean.getPaymentMethod());
			pstmt.setString(5, bean.getPaymentStatus());
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
			throw new ApplicationException("Exception : Exception in add Payment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	@Override
	public void update(PaymentBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		PaymentBean existbean = findByOrder(bean.getOrderId());

		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Order ID already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction`
			PreparedStatement pstmt = conn.prepareStatement(
					"update " + getTable() + " set order_id = ?, amount = ?, method = ?, status = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getOrderId());
			pstmt.setLong(2, bean.getAmount());
			pstmt.setString(3, bean.getPaymentMethod());
			pstmt.setString(4, bean.getPaymentStatus());
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
			throw new ApplicationException("Exception : Exception in Updated Payment Management");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public PaymentBean findByOrder(String order) throws ApplicationException {
		return findByUniqueColumn("order_id", order);
	}

	@Override
	public String getWhereClause(PaymentBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getOrderId() != null && bean.getOrderId().length() > 0) {
				sql.append(" and order_id like '" + bean.getOrderId() + "%'");
			}
			if (bean.getAmount() > 0) {
				sql.append(" and amount = " + bean.getAmount());
			}
			if (bean.getPaymentMethod() != null && bean.getPaymentMethod().length() > 0) {
				sql.append(" and method like '" + bean.getPaymentMethod() + "%'");
			}
			if (bean.getPaymentStatus() != null && bean.getPaymentStatus().length() > 0) {
				sql.append(" and status like '" + bean.getPaymentStatus() + "%'");
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_payment";
	}

	@Override
	public PaymentBean getBean() {
		return new PaymentBean();
	}

}