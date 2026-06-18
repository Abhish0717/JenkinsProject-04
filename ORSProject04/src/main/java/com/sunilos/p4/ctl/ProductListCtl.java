package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.ProductBean;
import com.sunilos.p4.model.ProductModel;
import com.sunilos.p4.util.DataUtility;

@WebServlet("/ctl/ProductListCtl")
public class ProductListCtl extends BaseListCtl<ProductBean, ProductModel> {

    private static Logger log = Logger.getLogger(ProductListCtl.class);

    @Override
    protected ProductBean populateBean(HttpServletRequest request) {
        ProductBean bean = new ProductBean();
        bean.setProductName(DataUtility.getString(request.getParameter("product")));
        bean.setProductCategory(DataUtility.getString(request.getParameter("category")));
        bean.setOrderDate(DataUtility.getDate(request.getParameter("order_date")));
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
        return ORSView.PRODUCT_LIST_VIEW;
    }

    @Override
    protected ProductModel getModel() {
        return new ProductModel();
    }

}