package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.ClaimBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.ClaimModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "ClaimListCtl", urlPatterns = { "/ctl/ClaimListCtl" })
public class ClaimListCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ClaimModel Model = new ClaimModel();
		try {
			List<ClaimBean> numberList = Model.list();
			request.setAttribute("numberList", numberList);
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response, getView());
		}
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		ClaimBean bean = new ClaimBean();

		bean.setClaimNumber(DataUtility.getString(request.getParameter("number")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		ClaimBean bean = (ClaimBean) populateBean(request);
		ClaimModel model = new ClaimModel();

		try {

			List<ClaimBean> list = model.search(bean, pageNo, pageSize);
			List<ClaimBean> next = model.search(bean, pageNo + 1, pageSize);

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

		ClaimBean bean = (ClaimBean) populateBean(request);
		ClaimModel model = new ClaimModel();


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
				ServletUtility.redirect(ORSView.CLAIM_CTL, request, response);
				return;

			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.CLAIM_LIST_CTL, request, response);
				return;

			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				ClaimBean deletebean = new ClaimBean();

				if (ids != null && ids.length > 0) {
					for (String id : ids) {
						deletebean.setId(Integer.parseInt(id));
						model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Claim deleted successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}
			List<ClaimBean> list = model.search(bean, pageNo, pageSize);
			List<ClaimBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);
			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response, getView());
		}
	}

	@Override
	protected String getView() {
		return ORSView.CLAIM_LIST_VIEW;
	}
}