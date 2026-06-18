package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.CourierBean;
import com.sunilos.p4.model.CourierModel;
import com.sunilos.p4.util.DataUtility;

@WebServlet("/ctl/CourierListCtl")
public class CourierListCtl extends BaseListCtl<CourierBean, CourierModel> {

	private static Logger log = Logger.getLogger(CourierListCtl.class);

	@Override
	protected CourierBean populateBean(HttpServletRequest request) {
		CourierBean bean = new CourierBean();
		bean.setTrackingNumber(DataUtility.getString(request.getParameter("Tracking")));
		bean.setSenderName(DataUtility.getString(request.getParameter("sender")));
		bean.setReceiverName(DataUtility.getString(request.getParameter("receiver")));
		bean.setDeliveryStatus(DataUtility.getString(request.getParameter("status")));
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.COURIER_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.COURIER_LIST_VIEW;
	}

	@Override
	protected CourierModel getModel() {
		return new CourierModel();
	}

}