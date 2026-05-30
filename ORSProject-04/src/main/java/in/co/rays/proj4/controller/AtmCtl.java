package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.AtmBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.AtmModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "AtmCtl", urlPatterns = { "/ctl/AtmCtl" })
public class AtmCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Bank Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Invalid Bank Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("location"))) {
			request.setAttribute("location", PropertyReader.getValue("error.require", "Location"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("location"))) {
			request.setAttribute("location", "Invalid Location");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("cash"))) {
			request.setAttribute("cash", PropertyReader.getValue("error.require", "Cash Available"));
			pass = false;
		} else if (!DataValidator.isLong(request.getParameter("cash"))) {
			request.setAttribute("cash", "Invalid Cash Available");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("code"))) {
			request.setAttribute("code", PropertyReader.getValue("error.require", "Security Code"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("code"))) {
			request.setAttribute("code", "Invalid Security Code");
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		AtmBean bean = new AtmBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setBankName(DataUtility.getString(request.getParameter("name")));
		bean.setLocation(DataUtility.getString(request.getParameter("location")));
		bean.setCashAvailable(DataUtility.getInt(request.getParameter("cash")));
		bean.setSecurityCode(DataUtility.getInt(request.getParameter("code")));
		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = DataUtility.getLong(request.getParameter("id"));

		AtmModel model = new AtmModel();

		if (id > 0) {
			try {
				AtmBean bean = model.findByPk(id);
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

		AtmModel model = new AtmModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {
			AtmBean bean = (AtmBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Bank Name already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response, getView());
				return;
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			AtmBean bean = (AtmBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Bank Name already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response, getView());
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ATM_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ATM_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.ATM_VIEW;
	}

}
