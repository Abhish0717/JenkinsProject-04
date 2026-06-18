package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.CourierBean;
import com.sunilos.p4.model.CourierModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/CourierCtl")
public class CourierCtl extends BaseCtl<CourierBean, CourierModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("tracking"))) {
			request.setAttribute("tracking", PropertyReader.getValue("error.require", "Tracking Number"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("sender"))) {
			request.setAttribute("sender", PropertyReader.getValue("error.require", "Sender Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("receiver"))) {
			request.setAttribute("receiver", PropertyReader.getValue("error.require", "Receiver Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "Delivery Status"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected CourierBean populateBean(HttpServletRequest request) {

		CourierBean bean = new CourierBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setTrackingNumber(DataUtility.getString(request.getParameter("Tracking")));
		bean.setSenderName(DataUtility.getString(request.getParameter("sender")));
		bean.setReceiverName(DataUtility.getString(request.getParameter("receiver")));
		bean.setDeliveryStatus(DataUtility.getString(request.getParameter("status")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.COURIER_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.COURIER_LIST_CTL;

		} else {
			return ORSView.COURIER_VIEW;
		}
	}

	@Override
	protected CourierModel getModel() {
		return new CourierModel();
	}

}