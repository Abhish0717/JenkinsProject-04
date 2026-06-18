package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.CitizenBean;
import com.sunilos.p4.model.CitizenModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/CitizenCtl")
public class CitizenCtl extends BaseCtl<CitizenBean, CitizenModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobile_no"))) {
			request.setAttribute("mobile_no", PropertyReader.getValue("error.require", "Mobile Number"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("reward_points"))) {
			request.setAttribute("reward_points", PropertyReader.getValue("error.require", "Reward Point"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected CitizenBean populateBean(HttpServletRequest request) {

		CitizenBean bean = new CitizenBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setMobileNo(DataUtility.getInt(request.getParameter("mobile_no")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setRewardPoints(DataUtility.getString(request.getParameter("reward_points")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.CITIZEN_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.CITIZEN_LIST_CTL;

		} else {
			return ORSView.CITIZEN_VIEW;
		}
	}

	@Override
	protected CitizenModel getModel() {
		return new CitizenModel();
	}

}