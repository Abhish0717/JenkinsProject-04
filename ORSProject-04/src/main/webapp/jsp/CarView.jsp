<%@page import="java.util.concurrent.Phaser"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.CarBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.controller.CarCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Car Rental</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.CAR_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CarBean"
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
				Car Rental
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
					<th align="left">Customer Name<span style="color: red">*</span></th>
					<td><input type="text" name="name"
						placeholder="Enter Customer Name"
						value="<%=DataUtility.getStringData(bean.getCustomerName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Car Model<span style="color: red">*</span></th>
					<td><input type="text" name="model"
						placeholder="Enter CarModel"
						value="<%=DataUtility.getStringData(bean.getCarModel())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("model", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Rent Per Day<span style="color: red">*</span></th>
					<td><input type="text" name="rent"
						placeholder="Enter Car Rent Per Day"
						value="<%=DataUtility.getStringData(bean.getRentPerDay() > 0 ? bean.getRentPerDay() : "")%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("rent", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Fuel Type<span style="color: red">*</span></th>
					<td><input type="text" name="fuel"
						placeholder="Enter Fuel Type"
						value="<%=DataUtility.getStringData(bean.getFuelType())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("fuel", request)%></font></td>
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
						name="operation" value="<%=CarCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=CarCtl.OP_CANCEL%>">
						<%
						} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=CarCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=CarCtl.OP_RESET%>">
						<%
						}
						%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>