<%@page import="in.co.rays.proj4.bean.ParkingBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.ParkingCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Smart Parking</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.PARKING_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.ParkingBean"
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
				Smart Parking
			</h1>

			<div style="height: 15px; margin-bottom: 12px">
				<H3 align="center">
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</H3>

				<H3 align="center">
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
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
					<th align="left">Vehicle Number<span style="color: red">*</span></th>
					<td><input type="text" name="number"
						placeholder="Enter Vehicle Number"
						value="<%=DataUtility.getStringData(bean.getVehicleNumber())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("number", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Slot Number<span style="color: red">*</span></th>
					<td><input type="text" name="slot"
						placeholder="Enter Slot Number"
						value="<%=DataUtility.getStringData(bean.getSlotNumber())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("slot", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Parking Charge<span style="color: red">*</span></th>
					<td><input type="text" name="charge"
						placeholder="Enter Parking Charge"
						value="<%=DataUtility.getStringData(bean.getParkingCharge() > 0 ? bean.getParkingCharge() : "")%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("charge", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Entry Time<span style="color: red">*</span></th>
					<td><input type="text" name="time"
						placeholder="Enter Entry Time"
						value="<%=DataUtility.getStringData(bean.getEntryTime())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("time", request)%></font></td>
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
						name="operation" value="<%=ParkingCtl.OP_UPDATE%>"> <input
						type="submit" name="operation"
						value="<%=ParkingCtl.OP_CANCEL%>"> <%
 } else {
 %>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=ParkingCtl.OP_SAVE%>"> <input
						type="submit" name="operation"
						value="<%=ParkingCtl.OP_RESET%>"> <%
 }
 %>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>