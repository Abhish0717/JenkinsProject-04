<%@page import="in.co.rays.proj4.controller.ClaimListCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.Collections"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.bean.ClaimBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>Claim List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>
	<div align="center">
		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.ClaimBean"
			scope="request"></jsp:useBean>
		<h1 align="center" style="margin-bottom: -15; color: navy;">Claim
			List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.CLAIM_LIST_CTL%>" method="POST">
			<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;
			int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

			List<ClaimBean> numberList = (List<ClaimBean>) request.getAttribute("numberList");

			List<ClaimBean> list = (List<ClaimBean>) ServletUtility.getList(request);
			Iterator<ClaimBean> it = list.iterator();

			if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Claim Number : </b></label> <%=HTMLUtility.getList("number", String.valueOf(bean.getClaimNumber()), numberList)%>&emsp;
						<label><b>Status :</b></label> <input type="text" name="status"
						placeholder="Enter Status"
						value="<%=ServletUtility.getParameter("status", request)%>">&emsp;
						<input type="submit" name="operation"
						value="<%=ClaimListCtl.OP_SEARCH%>">&nbsp; <input
						type="submit" name="operation" value="<%=ClaimListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="25%">Claim Number</th>
					<th width="15%">Claim Amount</th>
					<th width="15%">Status</th>
					<th width="5%">Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					bean = (ClaimBean) it.next();
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getClaimNumber()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getClaimAmount()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getStatus()%></td>
					<td style="text-align: center;"><a
						href="ClaimCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
				}
				%>
			</table>

			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=ClaimListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=ClaimListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=ClaimListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=ClaimListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
			</table>

			<%
			}
			if (list.size() == 0) {
			%>
			<table>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="<%=ClaimListCtl.OP_BACK%>"></td>

				</tr>
			</table>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>