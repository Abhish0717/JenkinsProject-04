package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.AssistantBean;
import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.AssistantModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "AssistantCtl", urlPatterns = { "/ctl/AssistantCtl" })
public class AssistantCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("user"))) {
			request.setAttribute("user", PropertyReader.getValue("error.require", "User Voice"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("user"))) {
			request.setAttribute("user", "Invalid User Voice");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("response"))) {
			request.setAttribute("response", PropertyReader.getValue("error.require", "Response"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("language"))) {
			request.setAttribute("language", PropertyReader.getValue("error.require", "Language"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("accuracy"))) {
			request.setAttribute("accuracy", PropertyReader.getValue("error.require", "Accuracy"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("accuracy"))) {
			request.setAttribute("accuracy", "Invalid Accuracy");
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		AssistantBean bean = new AssistantBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setUserVoice(DataUtility.getString(request.getParameter("user")));
		bean.setLanguage(DataUtility.getString(request.getParameter("language")));
		bean.setResponse(DataUtility.getString(request.getParameter("response")));
		bean.setAccuracy(DataUtility.getInt(request.getParameter("accuracy")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = DataUtility.getLong(request.getParameter("id"));

		AssistantModel model = new AssistantModel();

		if (id > 0) {
			try {
				AssistantBean bean = model.findByPk(id);
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

		AssistantModel model = new AssistantModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			AssistantBean bean = (AssistantBean) populateBean(request);

			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is Successfully Saved", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("User Voice Already Exists", request);
				e.printStackTrace();
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response, getView());
				e.printStackTrace();
				return;
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			AssistantBean bean = (AssistantBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("User Voice already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response, getView());
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ASSISTANT_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ASSISTANT_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.ASSISTANT_VIEW;
	}

}
