package com.sunilos.p4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sunilos.p4.bean.PaymentBean;
import com.sunilos.p4.bean.ProductBean;
import com.sunilos.p4.exception.ApplicationException;
import com.sunilos.p4.exception.DuplicateRecordException;
import com.sunilos.p4.util.JDBCDataSource;

public class ProductModel extends BaseModel<ProductBean> {

	@Override
	public ProductBean getBean() {
		return new ProductBean();
	}

	@Override
	public long add(ProductBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		ProductBean existbean = findByProductName(bean.getProductName());

		if (existbean != null) {
			throw new DuplicateRecordException("product Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getProductName());
			pstmt.setString(3, bean.getProductCategory());
			pstmt.setDate(4, new java.sql.Date(bean.getOrderDate().getTime()));
			pstmt.setInt(5, bean.getPrice());
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
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	@Override
	public void update(ProductBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		ProductBean existbean = findByProductName(bean.getProductName());

		if (existbean != null && existbean.getId() != bean.getId()) {
			throw new DuplicateRecordException("productName already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction`
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name = ?, category = ?, order_date = ?, price = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getProductName());
			pstmt.setString(2, bean.getProductCategory());
			pstmt.setDate(3, new java.sql.Date(bean.getOrderDate().getTime()));
			pstmt.setInt(4, bean.getPrice());
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

	@Override
	public String getWhereClause(ProductBean bean) {

		StringBuffer sql = new StringBuffer();

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getProductName() != null && bean.getProductName().length() > 0) {
				sql.append(" AND name like '" + bean.getProductName() + "%'");
			}
			if (bean.getProductCategory() != null && bean.getProductCategory().length() > 0) {
				sql.append(" AND category like '" + bean.getProductCategory() + "%'");
			}
			if (bean.getOrderDate() != null && bean.getOrderDate().getTime() > 0) {
				sql.append(" AND order_date = " + bean.getOrderDate());
			}
			if (bean.getPrice() > 0) {
				sql.append(" AND price = " + bean.getProductName());
			}
		}

		return sql.toString();
	}

	public ProductBean findByProductName(String productName) {
		return findByUniqueColumn("name", productName);
	}

	@Override
	public String getTable() {
		return "st_product";
	}

}
