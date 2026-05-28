<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error 500</title>
</head>
<body>
	<div align="center">
		<h3>Something Went Wrong</h3>
		<hr>
		<img src="<%=ORSView.APP_CONTEXT%>/img/error500.webp">
		<hr>
		<a href=<%=ORSView.WELCOME_CTL%>>Go to home Page</a>
	</div>
</body>
</html>