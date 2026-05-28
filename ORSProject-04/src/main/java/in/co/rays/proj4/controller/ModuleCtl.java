package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "ModuleCtl", urlPatterns = { "/ctl/ModuleCtl" })
public class ModuleCtl extends BaseCtl {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.MODULE_VIEW;
	}
}
