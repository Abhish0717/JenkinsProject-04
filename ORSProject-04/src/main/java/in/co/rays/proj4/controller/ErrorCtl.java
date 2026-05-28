package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.util.ServletUtility;

/**
 * ErrorCtl handles application-wide errors and forwards the user to an
 * appropriate error view.
 * 
 * It captures error details from the request and sets default error messages
 * and attributes for display.
 * 
 * URL Mapping: /ErrorCtl
 * 
 * @author Abhishish Bhawsar
 * 
 * @version 1.0
 */
@WebServlet("/ErrorCtl")
public class ErrorCtl extends BaseCtl {

	/**
	 * Handles HTTP GET requests. Delegates processing to the common process()
	 * method.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if servlet-specific error occurs
	 * @throws IOException      if an input/output error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);
	}

	/**
	 * Handles HTTP POST requests. Delegates processing to the common process()
	 * method.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if servlet-specific error occurs
	 * @throws IOException      if an input/output error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);
	}

	/**
	 * Common method to process both GET and POST requests.
	 * 
	 * This method: - Retrieves the last requested controller - Sets a default error
	 * message - Handles list view fallback (if error occurred in ListCtl) -
	 * Forwards the request to the error view
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws IOException      if input/output error occurs
	 * @throws ServletException if servlet error occurs
	 */
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500

		String lastCtl = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (lastCtl == null) {
			lastCtl = (String) request.getAttribute("lastCtl");
		}
		String view = (String) request.getAttribute("view");

		ServletUtility.setErrorMessage("Database server down please check!!!", request);

		if (lastCtl != null && lastCtl.contains("ListCtl")) {

			if (ServletUtility.getList(request) == null) {

				ServletUtility.setList(new java.util.ArrayList(), request);
			}
			request.setAttribute("pageNo", 1);
			request.setAttribute("pageSize", 10);
			request.setAttribute("nextListSize", 0);
		}
		ServletUtility.forward(view, request, response);
	}

	/**
	 * Returns the default error view page.
	 * 
	 * @return path of ERROR_VIEW
	 */
	@Override
	protected String getView() {
		return ORSView.ERROR_VIEW;
	}

}