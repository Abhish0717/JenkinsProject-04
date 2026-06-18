package com.sunilos.p4.ctl;

import com.sunilos.p4.bean.CitizenBean;
import com.sunilos.p4.model.CitizenModel;
import com.sunilos.p4.util.DataUtility;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/CitizenListCtl")
public class CitizenListCtl extends BaseListCtl<CitizenBean, CitizenModel> {

    @Override
    protected CitizenBean populateBean(HttpServletRequest request) {
        CitizenBean bean = new CitizenBean();
        bean.setName(DataUtility.getString(request.getParameter("name")));
        bean.setMobileNo(DataUtility.getInt(request.getParameter("mobile_no")));
        bean.setAddress(DataUtility.getString(request.getParameter("address")));
        bean.setRewardPoints(DataUtility.getString(request.getParameter("reward_points")));
        return bean;
    }

    @Override
    protected String getView() {
        return ORSView.CITIZEN_LIST_VIEW;
    }

    @Override
    protected String getView(String op) {
        return ORSView.CITIZEN_LIST_VIEW;
    }

    @Override
    protected CitizenModel getModel() {
        return new CitizenModel();
    }

}