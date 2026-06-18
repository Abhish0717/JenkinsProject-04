package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.CitizenBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class CitizenModel extends BaseModel<CitizenBean> {

	@Override
	public long add(CitizenBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		CitizenBean existbean = findByName(bean.getName());

		if (existbean != null) {
			throw new DuplicateRecordException("Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setInt(3, bean.getMobileNo());
			pstmt.setString(4, bean.getAddress());
			pstmt.setString(5, bean.getRewardPoints());
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
			throw new ApplicationException("Exception : Exception in add Citizen Management");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	@Override
	public void update(CitizenBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		CitizenBean existbean = findByName(bean.getName());

		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Name already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction`
			PreparedStatement pstmt = conn.prepareStatement(
					"update " + getTable() + " set name = ?, mobile_no = ?, address = ?, reward_points = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getMobileNo());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getRewardPoints());
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
			throw new ApplicationException("Exception : Exception in Updated Citizen Management");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public CitizenBean findByName(String name) throws ApplicationException {
		return findByUniqueColumn("name", name);
	}

	@Override
	public String getWhereClause(CitizenBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getMobileNo() > 0) {
				sql.append(" and mobile_no = " + bean.getMobileNo());
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" and address like '" + bean.getAddress() + "%'");
			}
			if (bean.getRewardPoints() != null && bean.getRewardPoints().length() > 0) {
				sql.append(" and reward_points like '" + bean.getRewardPoints() + "%'");
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_citizen";
	}

	@Override
	public CitizenBean getBean() {
		return new CitizenBean();
	}

}