package com.sunilos.p4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProductBean extends BaseBean {

	private String productName;
	private String productCategory;
	private Date orderDate;
	private int price;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return productName;
	}

	@Override
	public void setResultset(ResultSet rs) {

		try {
			super.setResultset(rs);
			this.setProductName(rs.getString("name"));
			this.setProductCategory(rs.getString("category"));
			this.setOrderDate(rs.getDate("order_date"));
			this.setPrice(rs.getInt("price"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
