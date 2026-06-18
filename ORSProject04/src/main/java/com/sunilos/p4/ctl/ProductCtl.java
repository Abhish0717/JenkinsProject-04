package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.ProductBean;
import com.sunilos.p4.model.ProductModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ProductCtl")
public class ProductCtl extends BaseCtl<ProductBean, ProductModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("productName"))) {
			request.setAttribute("productName", PropertyReader.getValue("error.require", "productName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("productCategory"))) {
			request.setAttribute("productCategory", PropertyReader.getValue("error.require", "productCategory"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("orderDate"))) {
			request.setAttribute("orderDate", PropertyReader.getValue("error.require", "orderDate"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "price"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected ProductBean populateBean(HttpServletRequest request) {

		ProductBean bean = new ProductBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setProductName(DataUtility.getString(request.getParameter("productName")));
		bean.setProductCategory(DataUtility.getString(request.getParameter("productCategory")));
		bean.setOrderDate(DataUtility.getDate(request.getParameter("orderDate")));
		bean.setPrice(DataUtility.getInt(request.getParameter("price")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.PRODUCT_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.PRODUCT_LIST_CTL;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			return ORSView.PRODUCT_VIEW;
		} else {
			return ORSView.PRODUCT_VIEW;
		}
	}

	@Override
	protected ProductModel getModel() {
		return new ProductModel();
	}

}
