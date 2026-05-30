package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CarBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CarModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "CarCtl", urlPatterns = { "/ctl/CarCtl" })
public class CarCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Customer Name"));
			pass = false;
		}else if(!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Invalid Customer Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("model"))) {
			request.setAttribute("model", PropertyReader.getValue("error.require", "Car Model"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("rent"))) {
			request.setAttribute("rent", PropertyReader.getValue("error.require", "Rent Per Day"));
			pass = false;

		} else if (!DataValidator.isLong(request.getParameter("rent"))) {
			request.setAttribute("rent", "Invalid Rent Per Day");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("fuel"))) {
			request.setAttribute("fuel", PropertyReader.getValue("error.require", "Status"));
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CarBean bean = new CarBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCustomerName(DataUtility.getString(request.getParameter("name")));
		bean.setCarModel(DataUtility.getString(request.getParameter("model")));
		bean.setRentPerDay(DataUtility.getLong(request.getParameter("rent")));
		bean.setFuelType(DataUtility.getString(request.getParameter("fuel")));
		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = DataUtility.getLong(request.getParameter("id"));

		CarModel model = new CarModel();

		if (id > 0) {
			try {
				CarBean bean = model.findByPk(id);
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

		CarModel model = new CarModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {
			CarBean bean = (CarBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Customer Name already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response, getView());
				return;
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			CarBean bean = (CarBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Customer Name already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response, getView());
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.CAR_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.CAR_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.CAR_VIEW;
	}

}
