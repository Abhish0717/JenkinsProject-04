package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.ParkingBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.ParkingModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "ParkingCtl", urlPatterns = { "/ctl/ParkingCtl" })
public class ParkingCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("number"))) {
			request.setAttribute("number", PropertyReader.getValue("error.require", "Vehicle Number"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("slot"))) {
			request.setAttribute("slot", PropertyReader.getValue("error.require", "Slot Number"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("charge"))) {
			request.setAttribute("charge", PropertyReader.getValue("error.require", "Parking Charge"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("time"))) {
			request.setAttribute("time", PropertyReader.getValue("error.require", "Entry Time"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		ParkingBean bean = new ParkingBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setVehicleNumber(DataUtility.getString(request.getParameter("number")));
		bean.setSlotNumber(DataUtility.getString(request.getParameter("slot")));
		bean.setParkingCharge(DataUtility.getLong(request.getParameter("charge")));
		bean.setEntryTime(DataUtility.getString(request.getParameter("time")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		long id = DataUtility.getLong(request.getParameter("id"));

		ParkingModel model = new ParkingModel();

		if (id > 0) {
			try {
				ParkingBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response, getView());
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

		ParkingModel model = new ParkingModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {
			ParkingBean bean = (ParkingBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Parking added successfully", request);
			} catch (DuplicateRecordException e) {
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response, getView());
				return;
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			ParkingBean bean = (ParkingBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(bean.getSlotNumber() + " Slot Number already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response, getView());
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PARKING_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PARKING_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.PARKING_VIEW;
	}

}
