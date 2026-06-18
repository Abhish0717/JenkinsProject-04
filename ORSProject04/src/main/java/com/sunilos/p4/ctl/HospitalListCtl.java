package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.HospitalBean;
import com.sunilos.p4.model.HospitalModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/HospitalListCtl")
public class HospitalListCtl extends BaseListCtl<HospitalBean, HospitalModel> {

	@Override
	protected HospitalBean populateBean(HttpServletRequest request) {
		HospitalBean bean = new HospitalBean();
		bean.setPatientName(DataUtility.getString(request.getParameter("patient")));
		bean.setDoctorName(DataUtility.getString(request.getParameter("doctor")));
		bean.setDisease(DataUtility.getString(request.getParameter("disease")));
		bean.setRoomNumber(DataUtility.getInt(request.getParameter("room")));
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.HOSPITAL_VIEW;
	}

	@Override
	protected String getView(String op) {
		return ORSView.HOSPITAL_LIST_VIEW;
	}

	@Override
	protected HospitalModel getModel() {
		return new HospitalModel();
	}

}