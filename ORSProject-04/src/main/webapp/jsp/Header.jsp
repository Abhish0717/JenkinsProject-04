<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@page import="in.co.rays.proj4.bean.RoleBean"%>
<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
<!-- JQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<script src="/ORSProject-04/js/checkbox.js"></script>
<script src="/ORSProject-04/js/datepicker.js"></script>
</head>
<body>
	<!-- Logo -->
	<img src="<%=request.getContextPath()%>/img/customLogo.jpg"
		align="right" width="100" height="40" border="0">
	<%
	UserBean user = (UserBean) session.getAttribute("user");
	boolean loggedIn = user != null;
	%>
	<%
	if (loggedIn) {
	%>
	<h3>
		Hi,
		<%=user.getFirstName()%>
		(<%=session.getAttribute("role")%>)
	</h3>
	<!-- Common menus -->
	<a href="<%=ORSView.MY_PROFILE_CTL%>"><b>My Profile</b></a>
	<b>|</b>
	<a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change Password</b></a>
	<b>|</b>
	<a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>Get Marksheet</b></a>
	<b>|</b>
	<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet
			Merit-List</b></a>
	<b>|</b>
	<!-- Logout -->
	<a href="<%=ORSView.LOGIN_CTL + "?operation=Logout"%>"><b>Logout</b></a>
	<b>|</b>
	<!-- Admin Only + Common Menus-->
	<%
	if (user.getRoleId() == RoleBean.ADMIN) {
	%>



	<a href="<%=ORSView.ROLE_CTL%>">Add Role</a>
	<b>|</b>
	<a href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a>
	<b>|</b>
	<a href="<%=ORSView.USER_CTL%>">Add User</a>
	<b>|</b>
	<a href="<%=ORSView.USER_LIST_CTL%>">User List</a>
	<b>|</b>
	<a href="<%=ORSView.COLLEGE_CTL%>">Add College</a>
	<b>|</b>
	<a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a>
	<b>|</b>
	<a href="<%=ORSView.STUDENT_CTL%>">Add Student</a>
	<b>|</b>
	<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a>
	<b>|</b>
	<a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a>
	<b>|</b>
	<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a>
	<b>|</b>
	<a href="<%=ORSView.COURSE_CTL%>">Add Course</a>
	<b>|</b>
	<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a>
	<b>|</b>
	<a href="<%=ORSView.SUBJECT_CTL%>">Add Subject</a>
	<b>|</b>
	<a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a>
	<b>|</b>
	<a href="<%=ORSView.TIMETABLE_CTL%>">Add TimeTable</a>
	<b>|</b>
	<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</a>
	<b>|</b>
	<a href="<%=ORSView.FACULTY_CTL%>">Add Faculty</a>
	<b>|</b>
	<a href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a>
	<b>|</b>
	<a target="blank" href="<%=ORSView.JAVA_DOC_VIEW%>"><b>Java Doc</b></a>
	<b>|</b>
	<!-- Daily Module -->
	<a href="<%=ORSView.MODULE_CTL%>"><b>Other Modules</b></a>
	<b>|</b>
	<a href="<%=ORSView.CAR_CTL%>">Add Car Rental</a>
	<b>|</b>
	<a href="<%=ORSView.CAR_LIST_CTL%>">Car Rental List</a>
	<%
	}
	%>
	<%
	if (user.getRoleId() == RoleBean.STUDENT) {
	%>
	<a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a>
	<b>|</b>
	<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a>
	<b>|</b>
	<a href="<%=ORSView.COLLEGE_CTL%>">Add College</a>
	<b>|</b>
	<a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a>
	<b>|</b>
	<a href="<%=ORSView.COURSE_CTL%>">Add Course</a>
	<b>|</b>
	<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a>
	<b>|</b>
	<a href="<%=ORSView.SUBJECT_CTL%>">Add Subject</a>
	<b>|</b>
	<a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a>
	<b>|</b>
	<a href="<%=ORSView.TIMETABLE_CTL%>">Add TimeTable</a>
	<b>|</b>
	<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</a>
	<%
	}
	%>
	<!-- Faculty + Common Menus -->
	<%
	if (user.getRoleId() == RoleBean.FACULTY) {
	%>
	<a href="<%=ORSView.STUDENT_CTL%>">Add Student</a>
	<b>|</b>
	<a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a>
	<b>|</b>
	<a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a>
	<b>|</b>
	<a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a>
	<b>|</b>
	<a href="<%=ORSView.COURSE_CTL%>">Add Course</a>
	<b>|</b>
	<a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a>
	<b>|</b>
	<a href="<%=ORSView.SUBJECT_CTL%>">Add Subject</a>
	<b>|</b>
	<a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a>
	<b>|</b>
	<a href="<%=ORSView.FACULTY_CTL%>">Add Faculty</a>
	<b>|</b>
	<a href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a>
	<b>|</b>
	<a href="<%=ORSView.TIMETABLE_CTL%>">Add Timetable</a>
	<b>|</b>
	<a href="<%=ORSView.TIMETABLE_LIST_CTL%>">Timetable List</a>
	<%
	}
	%>
	<%
	} else {
	%>
	<!-- Guest View -->
	<h3>Hi, Guest</h3>
	<a href="<%=ORSView.WELCOME_CTL%>"><b>Welcome</b></a>
	<b>|</b>
	<a href="<%=ORSView.LOGIN_CTL%>"><b>Login</b></a>
	<%
	}
	%>
	<hr>
	<%@ include file="Footer.jsp"%>
</body>
</html>