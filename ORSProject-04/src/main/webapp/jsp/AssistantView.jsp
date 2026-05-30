<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.AssistantCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Voice Assistant</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>

	<form action="<%=ORSView.ASSISTANT_CTL%>" method="post">

		<%@ include file="ModuleView.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.AssistantBean"
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
				Voice Assistant
			</h1>

			<div style="height: 15px; margin-bottom: 12px">
				<h3 align="center">
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</h3>
				<h3 align="center">
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</h3>
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
					<th align="left">User Voice<span style="color: red">*</span></th>
					<td><input type="text" name="user"
						placeholder="Enter User Voice"
						value="<%=DataUtility.getStringData(bean.getUserVoice())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("user", request)%>
					</font></td>
				</tr>
				<tr>
					<th align="left">Response<span style="color: red">*</span></th>
					<td><input type="text" name="response"
						placeholder="Enter Response"
						value="<%=DataUtility.getStringData(bean.getResponse())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("response", request)%>
					</font></td>
				</tr>
				<tr>
					<th align="left">Language<span style="color: red">*</span></th>
					<td><input type="text" name="language"
						placeholder="Enter Language"
						value="<%=DataUtility.getStringData(bean.getLanguage())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("language", request)%>
					</font></td>
				</tr>
				<tr>
					<th align="left">Accuracy<span style="color: red">*</span></th>
					<td><input type="text" name="accuracy"
						placeholder="Enter Accuracy"
						<%-- value="<%=DataUtility.getStringData(bean.getAccuracy())%>"></td> --%>
						value="<%=bean.getAccuracy() > 0 ? bean.getAccuracy() : ""%>">
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("accuracy", request)%>
					</font></td>
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
						name="operation" value="<%=AssistantCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=AssistantCtl.OP_CANCEL%>">
						<%
						} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=AssistantCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=AssistantCtl.OP_RESET%>">
						<%
						}
						%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>