package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.HospitalBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class HospitalModel extends BaseModel<HospitalBean> {

	@Override
	public long add(HospitalBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		HospitalBean existbean = findByName(bean.getPatientName());

		if (existbean != null) {
			throw new DuplicateRecordException("Patient Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO st_hospital VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getPatientName());
			pstmt.setString(3, bean.getDoctorName());
			pstmt.setString(4, bean.getDisease());
			pstmt.setInt(5, bean.getRoomNumber());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Hospital System");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	@Override
	public void update(HospitalBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model Update Started");
		Connection conn = null;
		int pk = 0;

		HospitalBean existbean = findByName(bean.getPatientName());

		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("product Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			// Get auto-generated next primary key
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction`
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_hospital set patient=?,doctor=?,disease=?,room=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getPatientName());
			pstmt.setString(2, bean.getDoctorName());
			pstmt.setString(3, bean.getDisease());
			pstmt.setInt(4, bean.getRoomNumber());
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
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : Update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in Updated Hospital System");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model Updated Successfully and Code End");
	}

	public HospitalBean findByName(String patient) throws ApplicationException {
		return findByUniqueColumn("patient", patient);
	}

	@Override
	public String getWhereClause(HospitalBean bean) {
		StringBuffer sql = new StringBuffer();
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getPatientName() != null && bean.getPatientName().length() > 0) {
				sql.append(" and patient like '" + bean.getPatientName() + "%'");
			}
			if (bean.getDoctorName() != null && bean.getDoctorName().length() > 0) {
				sql.append(" and doctor like '" + bean.getDoctorName() + "%'");
			}
			if (bean.getDisease() != null && bean.getDisease().length() > 0) {
				sql.append(" and disease like '" + bean.getDisease() + "%'");
			}
			if (bean.getRoomNumber() > 0) {
				sql.append(" and room = " + bean.getRoomNumber());
			}

		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_hospital";
	}

	@Override
	public HospitalBean getBean() {
		return new HospitalBean();
	}

}
