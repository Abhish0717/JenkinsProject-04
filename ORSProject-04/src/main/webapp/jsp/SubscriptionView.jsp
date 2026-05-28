<%@page import="in.co.rays.proj4.bean.SubscriptionBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.SubscriptionCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Subscription</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.SUBSCRIPTION_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.SubscriptionBean"
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
				Subscription Plan
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
					<th align="left">Plan Name<span style="color: red">*</span></th>
					<td><input type="text" name="name"
						placeholder="Enter Plan Name"
						value="<%=DataUtility.getStringData(bean.getPlanName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Plan Code<span style="color: red">*</span></th>
					<td><input type="text" name="code"
						placeholder="Enter Plan Code"
						value="<%=DataUtility.getStringData(bean.getPlanCode())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("code", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Subscription Price<span style="color: red">*</span></th>
					<td><input type="text" name="price"
						placeholder="Enter Subscription Price"
						value="<%=DataUtility.getStringData(bean.getPrice())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("price", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Subscription Status<span style="color: red">*</span></th>
					<td><input type="text" name="status"
						placeholder="Enter Subscription Status"
						value="<%=DataUtility.getStringData(bean.getStatus())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("status", request)%></font></td>
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
						name="operation" value="<%=SubscriptionCtl.OP_UPDATE%>"> <input
						type="submit" name="operation"
						value="<%=SubscriptionCtl.OP_CANCEL%>"> <%
 } else {
 %>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=SubscriptionCtl.OP_SAVE%>"> <input
						type="submit" name="operation"
						value="<%=SubscriptionCtl.OP_RESET%>"> <%
 }
 %>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>