package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.HospitalBean;
import com.sunilos.p4.model.HospitalModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.PropertyReader;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/HospitalCtl")
public class HospitalCtl extends BaseCtl<HospitalBean, HospitalModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("patient"))) {
			request.setAttribute("patient", PropertyReader.getValue("error.require", "patient Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("patient"))) {
			request.setAttribute("patient", "Invalid Patient Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("doctor"))) {
			request.setAttribute("doctor", PropertyReader.getValue("error.require", "Doctor Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("doctor"))) {
			request.setAttribute("doctor", "Invalid Doctor Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("disease"))) {
			request.setAttribute("disease", PropertyReader.getValue("error.require", "Disease Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("room"))) {
			request.setAttribute("room", PropertyReader.getValue("error.require", "Room Number"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("room"))) {
			request.setAttribute("room", "Invalid Room Number");
			pass = false;
		}

		return pass;
	}

	@Override
	protected HospitalBean populateBean(HttpServletRequest request) {

		HospitalBean bean = new HospitalBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setPatientName(DataUtility.getString(request.getParameter("patient")));
		bean.setDoctorName(DataUtility.getString(request.getParameter("doctor")));
		bean.setDisease(DataUtility.getString(request.getParameter("disease")));
		bean.setRoomNumber(DataUtility.getInt(request.getParameter("room")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.HOSPITAL_VIEW;
	}

	@Override
	protected String getView(String op) {

		if (OP_CANCEL.equalsIgnoreCase(op) || OP_DELETE.equalsIgnoreCase(op)) {
			return ORSView.HOSPITAL_LIST_CTL;

		} else {
			return ORSView.HOSPITAL_VIEW;
		}
	}

	@Override
	protected HospitalModel getModel() {
		return new HospitalModel();
	}

}