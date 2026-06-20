package com.sunilos.p4.ctl;

/**
 * Contains ORS View and Controller URI
 * 
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

public interface ORSView {

	public String APP_CONTEXT = "/ORSProject04";

	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";

	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";
	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";

	public String ERROR_CTL = "/ctl/MarksheetCtl";

	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";
	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";

	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";

	public String STUDENT_REPORT_CTL = APP_CONTEXT + "/ctl/StudentReportCtl";
	public String COLLEGE_REPORT_CTL = APP_CONTEXT + "/ctl/CollegeReportCtl";
	public String USER_REPORT_CTL = APP_CONTEXT + "/ctl/UserReportCtl";
	public String ROLE_REPORT_CTL = APP_CONTEXT + "/ctl/RoleReportCtl";
	public String COURSE_REPORT_CTL = APP_CONTEXT + "/ctl/CourseReportCtl";
	public String SUBJECT_REPORT_CTL = APP_CONTEXT + "/ctl/SubjectReportCtl";
	public String MARKSHEET_REPORT_CTL = APP_CONTEXT + "/ctl/MarksheetReportCtl";
	public String FACULTY_REPORT_CTL = APP_CONTEXT + "/ctl/FacultyReportCtl";

	public String COURSE_REPORT_VIEW = "/reports/CourseListReport.jrxml";
	public String STUDENT_REPORT_VIEW = "/reports/StudentListReport.jrxml";
	public String COLLEGE_REPORT_VIEW = "/reports/CollegeListReport.jrxml";
	public String USER_REPORT_VIEW = "/reports/UserListReport.jrxml";
	public String ROLE_REPORT_VIEW = "/reports/RoleListReport.jrxml";
	public String SUBJECT_REPORT_VIEW = "/reports/SubjectListReport.jrxml";
	public String MARKSHEET_REPORT_VIEW = "/reports/MarksheetListReport.jrxml";
	public String FACULTY_REPORT_VIEW = "/reports/FacultyListReport.jrxml";

	/*-- ---- Daily Module : New Modules ---- --*/
 	/** Product View JSP */
	public String PRODUCT_VIEW = PAGE_FOLDER + "/ProductView.jsp";
	/** Product Controller URL */
	public String PRODUCT_CTL = APP_CONTEXT + "/ctl/ProductCtl";

	/** Product List JSP */
	public String PRODUCT_LIST_VIEW = PAGE_FOLDER + "/ProductListView.jsp";
	/** Product List Controller URL */
	public String PRODUCT_LIST_CTL = APP_CONTEXT + "/ctl/ProductListCtl";

	/** Product List Controller URL */
	public String PRODUCT_REPORT_CTL = APP_CONTEXT + "/ctl/ProductReportCtl";
	/** Product List Controller URL */
	public String PRODUCT_REPORT_VIEW = "/reports/ProductListReport.jrxml";
	

	/** Api View JSP */
	public String API_VIEW = PAGE_FOLDER + "/ApiView.jsp";
	/** Api Controller URL */
	public String API_CTL = APP_CONTEXT + "/ctl/ApiCtl";

	/** Api List JSP */
	public String API_LIST_VIEW = PAGE_FOLDER + "/ApiListView.jsp";
	/** Api List Controller URL */
	public String API_LIST_CTL = APP_CONTEXT + "/ctl/ApiListCtl";

	/** Citizen View JSP */
	public String CITIZEN_VIEW = PAGE_FOLDER + "/CitizenView.jsp";
	/** Citizen Controller URL */
	public String CITIZEN_CTL = APP_CONTEXT + "/ctl/CitizenCtl";

	/** Citizen List JSP */
	public String CITIZEN_LIST_VIEW = PAGE_FOLDER + "/CitizenListView.jsp";
	/** Citizen List Controller URL */
	public String CITIZEN_LIST_CTL = APP_CONTEXT + "/ctl/CitizenListCtl";

	/** Hospital View JSP */
	public String HOSPITAL_VIEW = PAGE_FOLDER + "/HospitalView.jsp";
	/** Hospital Controller URL */
	public String HOSPITAL_CTL = APP_CONTEXT + "/ctl/HospitalCtl";
	
	/** Hospital List JSP */
	public String HOSPITAL_LIST_VIEW = PAGE_FOLDER + "/HospitalListView.jsp";
	/** Hospital List Controller URL */
	public String HOSPITAL_LIST_CTL = APP_CONTEXT + "/ctl/HospitalListCtl";

	/** Payment View JSP */
	public String PAYMENT_VIEW = PAGE_FOLDER + "/PaymentView.jsp";
	/** Payment Controller URL */
	public String PAYMENT_CTL = APP_CONTEXT + "/ctl/PaymentCtl";
	
	/** Payment List JSP */
	public String PAYMENT_LIST_VIEW = PAGE_FOLDER + "/PaymentListView.jsp";
	/** Payment List Controller URL */
	public String PAYMENT_LIST_CTL = APP_CONTEXT + "/ctl/PaymentListCtl";

	/** Courier View JSP */
	public String COURIER_VIEW = PAGE_FOLDER + "/CourierView.jsp";
	/** Courier Controller URL */
	public String COURIER_CTL = APP_CONTEXT + "/ctl/CourierCtl";
	
	/** Courier List JSP */
	public String COURIER_LIST_VIEW = PAGE_FOLDER + "/CourierListView.jsp";
	/** Courier List Controller URL */
	public String COURIER_LIST_CTL = APP_CONTEXT + "/ctl/CourierListCtl";

}
