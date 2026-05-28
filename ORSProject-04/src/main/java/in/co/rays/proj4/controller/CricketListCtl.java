package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CricketBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.CricketModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "CricketListCtl", urlPatterns = { "/ctl/CricketListCtl" })
public class CricketListCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		CricketModel nameModel = new CricketModel();
		try {
			List nameList = nameModel.list();
			request.setAttribute("nameList", nameList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CricketBean bean = new CricketBean();

		bean.setPlayerName(DataUtility.getString(request.getParameter("player")));
		bean.setTeamName(DataUtility.getString(request.getParameter("team")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		CricketBean bean = (CricketBean) populateBean(request);
		CricketModel model = new CricketModel();

		try {

			List<CricketBean> list = model.search(bean, pageNo, pageSize);
			List<CricketBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response, getView());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		CricketBean bean = (CricketBean) populateBean(request);
		CricketModel model = new CricketModel();

		String op = request.getParameter("operation");
		String[] ids = request.getParameterValues("ids");

		try {

			if (OP_SEARCH.equalsIgnoreCase(op)) {
				pageNo = 1;

			} else if (OP_NEXT.equalsIgnoreCase(op)) {
				pageNo++;

			} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
				pageNo--;

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.CRICKET_CTL, request, response);
				return;

			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.CRICKET_LIST_CTL, request, response);
				return;

			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				CricketBean deletebean = new CricketBean();

				if (ids != null && ids.length > 0) {
					for (String id : ids) {
						deletebean.setId(Integer.parseInt(id));
						model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Data deleted successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}

			List<CricketBean> list = model.search(bean, pageNo, pageSize);
			List<CricketBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found ", request);
			}

			request.setAttribute("nextListSize", next.size());
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setBean(bean, request);
			ServletUtility.setPageSize(pageSize, request);

			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response, getView());
		}
	}

	@Override
	protected String getView() {
		return ORSView.CRICKET_LIST_VIEW;
	}
}