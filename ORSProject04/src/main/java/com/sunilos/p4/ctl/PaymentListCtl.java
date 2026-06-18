package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.PaymentBean;
import com.sunilos.p4.model.PaymentModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/PaymentListCtl")
public class PaymentListCtl extends BaseListCtl<PaymentBean, PaymentModel> {

    @Override
    protected PaymentBean populateBean(HttpServletRequest request) {
        PaymentBean bean = new PaymentBean();
        bean.setOrderId(DataUtility.getString(request.getParameter("order")));
		bean.setAmount(DataUtility.getLong(request.getParameter("amount")));
		bean.setPaymentMethod(DataUtility.getString(request.getParameter("method")));
		bean.setPaymentStatus(DataUtility.getString(request.getParameter("status")));

		return bean;
    }

    @Override
    protected String getView() {
        return ORSView.PAYMENT_LIST_VIEW;
    }

    @Override
    protected String getView(String op) {
        return ORSView.PAYMENT_LIST_VIEW;
    }

    @Override
    protected PaymentModel getModel() {
        return new PaymentModel();
    }

}