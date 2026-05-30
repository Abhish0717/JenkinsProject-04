<%@page import="java.util.concurrent.Phaser"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.CricketBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.controller.CricketCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Cricket Match</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<form action="<%=ORSView.CRICKET_CTL%>" method="post">
		<%@ include file="ModuleView.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CricketBean"
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
				Cricket Match
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
					<th align="left">Player Name<span style="color: red">*</span></th>
					<td><input type="text" name="player"
						placeholder="Enter Player Name"
						value="<%=DataUtility.getStringData(bean.getPlayerName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("player", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Team Name<span style="color: red">*</span></th>
					<td><input type="text" name="team"
						placeholder="Enter Team Name"
						value="<%=DataUtility.getStringData(bean.getTeamName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("team", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Runs<span style="color: red">*</span></th>
					<td><input type="text" name="runs" placeholder="Enter Runs"
						value="<%=DataUtility.getStringData(bean.getRuns() > 0 ? bean.getRuns() : "")%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("runs", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Matches<span style="color: red">*</span></th>
					<td><input type="text" name="matches"
						placeholder="Enter Matches"
						value="<%=DataUtility.getStringData(bean.getMatches() > 0 ? bean.getMatches() : "")%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("matches", request)%></font></td>
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
						name="operation" value="<%=CricketCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=CricketCtl.OP_CANCEL%>">
						<%
						} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=CricketCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=CricketCtl.OP_RESET%>">
						<%
						}
						%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>