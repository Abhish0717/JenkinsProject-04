package com.sunilos.p4.ctl;

import java.util.List;

import com.sunilos.p4.bean.ProductBean;
import com.sunilos.p4.model.ProductModel;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ctl/ProductReportCtl")
public class ProductReportCtl extends BaseReportCtl<ProductBean>{

	@Override
	public String getView() {
		return ORSView.PRODUCT_REPORT_VIEW;
	}

	@Override
	public String getCompiledReportKey() {
		return "PRODUCT_LIST_COMPILED_REPORT";
	}

	@Override
	public List<ProductBean> getList() {
		ProductModel model = new ProductModel();
		@SuppressWarnings("unchecked")
		List<ProductBean> product = model.list();
		return product;
	}

}
