<%@ taglib uri="http://www.sunilos.com/ors-tags" prefix="ors"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.DataValidator"%>

<%@ page contentType="text/html; charset=UTF-8"%>

<%
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ORS - Online Result System</title>

<link rel="icon" href="<%=contextPath%>/img/favicon.ico"
	type="image/x-icon">

<!-- Bootstrap 5 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">

<!-- Application CSS -->
<link rel="stylesheet" href="<%=contextPath%>/css/app.css">

<!-- jQuery UI -->
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="<%=contextPath%>/js/datepicker.js"></script>

</head>

<body class="d-flex flex-column min-vh-100">

	<%@ include file="jsp/Header.jsp"%>

	<%
	String viewName = request.getParameter("p");

	if (DataValidator.isNull(viewName)) {
		viewName = (String) request.getAttribute("p");
	}

	if (DataValidator.isNull(viewName)) {
		viewName = "jsp/Welcome.jsp";
	}
	%>

	<main class="flex-grow-1">
		<div class="page-content">
			<jsp:include page="<%=viewName%>" />
		</div>
	</main>

	<%@ include file="jsp/Footer.jsp"%>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>