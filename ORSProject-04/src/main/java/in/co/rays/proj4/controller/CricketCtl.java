package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CricketBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.CricketModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "CricketCtl", urlPatterns = { "/ctl/CricketCtl" })
public class CricketCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("player"))) {
			request.setAttribute("player", PropertyReader.getValue("error.require", "Player Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("player"))) {
			request.setAttribute("player", "Invalid Player Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("team"))) {
			request.setAttribute("team", PropertyReader.getValue("error.require", "Team Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("team"))) {
			request.setAttribute("team", "Invalid Team Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("runs"))) {
			request.setAttribute("runs", PropertyReader.getValue("error.require", "Runs"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("runs"))) {
			request.setAttribute("runs", "Invalid Runs");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("matches"))) {
			request.setAttribute("matches", PropertyReader.getValue("error.require", "Matches"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("matches"))) {
			request.setAttribute("matches", "Invalid Matches");
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CricketBean bean = new CricketBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setPlayerName(DataUtility.getString(request.getParameter("player")));
		bean.setTeamName(DataUtility.getString(request.getParameter("team")));
		bean.setRuns(DataUtility.getInt(request.getParameter("runs")));
		bean.setMatches(DataUtility.getInt(request.getParameter("matches")));
		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = DataUtility.getLong(request.getParameter("id"));

		CricketModel model = new CricketModel();

		if (id > 0) {
			try {
				CricketBean bean = model.findByPk(id);
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

		CricketModel model = new CricketModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {
			CricketBean bean = (CricketBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Team Name already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response, getView());
				return;
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			CricketBean bean = (CricketBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Team Name already exists", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response, getView());
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.CRICKET_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.CRICKET_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.CRICKET_VIEW;
	}

}
