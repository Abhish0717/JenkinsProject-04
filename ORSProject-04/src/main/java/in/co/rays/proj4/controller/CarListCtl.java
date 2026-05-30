package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CarBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.CarModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "CarListCtl", urlPatterns = { "/ctl/CarListCtl" })
public class CarListCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		CarModel nameModel = new CarModel();
		try {
			List modelList = nameModel.list();
			request.setAttribute("modelList", modelList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CarBean bean = new CarBean();

		bean.setCustomerName(DataUtility.getString(request.getParameter("name")));
		bean.setCarModel(DataUtility.getString(request.getParameter("model")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		CarBean bean = (CarBean) populateBean(request);
		CarModel model = new CarModel();

		try {

			List<CarBean> list = model.search(bean, pageNo, pageSize);
			List<CarBean> next = model.search(bean, pageNo + 1, pageSize);

			if (list == null || list.isEmpty()) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			request.setAttribute("nextListSize", next.size());

			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
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

		CarBean bean = (CarBean) populateBean(request);
		CarModel model = new CarModel();

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
				ServletUtility.redirect(ORSView.CAR_CTL, request, response);
				return;

			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.CAR_LIST_CTL, request, response);
				return;

			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				CarBean deletebean = new CarBean();

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

			List<CarBean> list = model.search(bean, pageNo, pageSize);
			List<CarBean> next = model.search(bean, pageNo + 1, pageSize);

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
		return ORSView.CAR_LIST_VIEW;
	}
}