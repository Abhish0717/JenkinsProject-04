package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.ApiBean;
import com.sunilos.p4.model.ApiModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ApiCtl")
public class ApiCtl extends BaseCtl<ApiBean, ApiModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("version"))) {
			request.setAttribute("version", PropertyReader.getValue("error.require", "version"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("end_point"))) {
			request.setAttribute("end_point", PropertyReader.getValue("error.require", "end_point"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected ApiBean populateBean(HttpServletRequest request) {

		ApiBean bean = new ApiBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setApiName(DataUtility.getString(request.getParameter("name")));
		bean.setVersion(DataUtility.getString(request.getParameter("version")));
		bean.setEndPoint(DataUtility.getString(request.getParameter("end_point")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.API_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.API_LIST_CTL;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			return ORSView.API_CTL;
		} else {
			return ORSView.API_VIEW;
		}
	}

	@Override
	protected ApiModel getModel() {
		return new ApiModel();
	}

}