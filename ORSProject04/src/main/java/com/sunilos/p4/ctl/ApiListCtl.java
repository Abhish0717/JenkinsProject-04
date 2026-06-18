package com.sunilos.p4.ctl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.ApiBean;
import com.sunilos.p4.model.ApiModel;
import com.sunilos.p4.util.DataUtility;

@WebServlet("/ctl/ApiListCtl")
public class ApiListCtl extends BaseListCtl<ApiBean, ApiModel> {

    private static Logger log = Logger.getLogger(ApiListCtl.class);

    @Override
    protected ApiBean populateBean(HttpServletRequest request) {
        ApiBean bean = new ApiBean();
        bean.setApiName(DataUtility.getString(request.getParameter("name")));
        bean.setVersion(DataUtility.getString(request.getParameter("version")));
        bean.setEndPoint(DataUtility.getString(request.getParameter("end_point")));
        bean.setStatus(DataUtility.getString(request.getParameter("status")));
        return bean;
    }  

    @Override
    protected String getView() {
        return ORSView.API_VIEW;
    }

    @Override
    protected String getView(String op) {
        return ORSView.API_LIST_VIEW;
    }

    @Override
    protected ApiModel getModel() {
        return new ApiModel();
    }

}