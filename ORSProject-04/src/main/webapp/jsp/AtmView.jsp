<%@page import="java.util.concurrent.Phaser"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.AtmBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.controller.AtmCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Atm System</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.ATM_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.AtmBean"
			scope="request"></jsp:useBean>

		<div align="center">
			<h1 align="center" style="margin-bottom: -15; color: navy">
				<%
				if (bean != null && bean.getId() > 0) {
				%>Update<%
				} else {
				%>Add<%
				}
				%>
				Atm System
			</h1>

			<div style="height: 15px; margin-bottom: 12px">
				<H3 align="center">
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</H3>
				<H3 align="center">
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</H3>
			</div>
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<tr>
					<th align="left">Bank Name<span style="color: red">*</span></th>
					<td><input type="text" name="name"
						placeholder="Enter Bank Name"
						value="<%=DataUtility.getStringData(bean.getBankName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Location<span style="color: red">*</span></th>
					<td><input type="text" name="location"
						placeholder="Enter Location"
						value="<%=DataUtility.getStringData(bean.getLocation())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("location", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Cash Available<span style="color: red">*</span></th>
					<td><input type="text" name="cash" placeholder="Enter Cash Available"
						value="<%=DataUtility.getStringData(bean.getCashAvailable() > 0 ? bean.getCashAvailable() : "")%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("cash", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Security Code<span style="color: red">*</span></th>
					<td><input type="text" name="code"
						placeholder="Enter Security Code"
						value="<%=DataUtility.getStringData(bean.getSecurityCode() > 0 ? bean.getSecurityCode() : "")%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("code", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th></th>
					<%
					if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=AtmCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=AtmCtl.OP_CANCEL%>">
						<%
						} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=AtmCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=AtmCtl.OP_RESET%>">
						<%
						}
						%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>