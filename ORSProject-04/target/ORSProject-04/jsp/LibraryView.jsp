<%@page import="java.util.concurrent.Phaser"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.LibraryBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.controller.LibraryCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Digital Library</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.LIBRARY_CTL%>" method="post">
		<%@ include file="ModuleView.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.LibraryBean"
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
				Digital Library
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
					<th align="left">Title<span style="color: red">*</span></th>
					<td><input type="text" name="title"
						placeholder="Enter Title"
						value="<%=DataUtility.getStringData(bean.getTitle())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("title", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Author<span style="color: red">*</span></th>
					<td><input type="text" name="author"
						placeholder="Enter Author"
						value="<%=DataUtility.getStringData(bean.getAuthor())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("author", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Category<span style="color: red">*</span></th>
					<td><input type="text" name="category" placeholder="Enter Category"
						value="<%=DataUtility.getStringData(bean.getCategory())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("category", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Available Copies<span style="color: red">*</span></th>
					<td><input type="text" name="copies"
						placeholder="Enter no. of available copies"
						value="<%=DataUtility.getStringData(bean.getAvailableCopies() > 0 ? bean.getAvailableCopies() : "")%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("copies", request)%></font></td>
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
						name="operation" value="<%=LibraryCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=LibraryCtl.OP_CANCEL%>">
						<%
						} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=LibraryCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=LibraryCtl.OP_RESET%>">
						<%
						}
						%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>