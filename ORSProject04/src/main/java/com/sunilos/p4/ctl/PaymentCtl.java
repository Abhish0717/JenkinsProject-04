package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.PaymentBean;
import com.sunilos.p4.model.PaymentModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/PaymentCtl")
public class PaymentCtl extends BaseCtl<PaymentBean, PaymentModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("order"))) {
			request.setAttribute("order", PropertyReader.getValue("error.require", "Order ID"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getValue("error.require", "Amount"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("method"))) {
			request.setAttribute("method", PropertyReader.getValue("error.require", "Payment Method"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "Payment Status"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected PaymentBean populateBean(HttpServletRequest request) {

		PaymentBean bean = new PaymentBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setOrderId(DataUtility.getString(request.getParameter("order")));
		bean.setAmount(DataUtility.getLong(request.getParameter("amount")));
		bean.setPaymentMethod(DataUtility.getString(request.getParameter("method")));
		bean.setPaymentStatus(DataUtility.getString(request.getParameter("status")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.PAYMENT_VIEW;
	}

	@Override
	protected String getView(String op) {
		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.PAYMENT_LIST_CTL;
		} else {
			return ORSView.PAYMENT_VIEW;
		}
	}

	@Override
	protected PaymentModel getModel() {
		return new PaymentModel();
	}

}