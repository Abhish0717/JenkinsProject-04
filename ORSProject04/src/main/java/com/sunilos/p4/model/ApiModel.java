package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.ApiBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class ApiModel extends BaseModel<ApiBean> {

	@Override
	public long add(ApiBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		ApiBean existbean = findByName(bean.getApiName());

		if (existbean != null) {
			throw new DuplicateRecordException("API Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("insert into st_api values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getApiName());
			pstmt.setString(3, bean.getVersion());
			pstmt.setString(4, bean.getEndPoint());
			pstmt.setString(5, bean.getStatus());
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
			throw new ApplicationException("Exception : Exception in add Api Info");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	@Override
	public void update(ApiBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		ApiBean existbean = findByName(bean.getApiName());

		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("API Name already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction`
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_api set name = ?, version = ?, end_point = ?, status = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getApiName());
			pstmt.setString(2, bean.getVersion());
			pstmt.setString(3, bean.getEndPoint());
			pstmt.setString(4, bean.getStatus());
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
			throw new ApplicationException("Exception : Exception in Updated Api Info");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public ApiBean findByName(String name) throws ApplicationException {
		return findByUniqueColumn("name", name);
	}

	@Override
	public String getWhereClause(ApiBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getApiName() != null && bean.getApiName().length() > 0) {
				sql.append(" and name like '" + bean.getApiName() + "%'");
			}
			if (bean.getVersion() != null && bean.getVersion().length() > 0) {
				sql.append(" and version like '" + bean.getVersion() + "%'");
			}
			if (bean.getEndPoint() != null && bean.getEndPoint().length() > 0) {
				sql.append(" and end_point like '" + bean.getEndPoint() + "%'");
			}
			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" and status like '" + bean.getStatus() + "%'");
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_api";
	}

	@Override
	public ApiBean getBean() {
		return new ApiBean();
	}

}