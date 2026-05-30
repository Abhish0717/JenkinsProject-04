<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.AtmListCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.AtmBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>Atm System List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.AtmBean"
		scope="request"></jsp:useBean>

	<div align="center">
		<h1 align="center" style="margin-bottom: -15; color: navy;">Atm
			System List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.ATM_LIST_CTL%>" method="post">
			<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;
			int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

			List<AtmBean> nameList = (List<AtmBean>) request.getAttribute("nameList");

			List<AtmBean> list = (List<AtmBean>) ServletUtility.getList(request);
			Iterator<AtmBean> it = list.iterator();

			if (list.size() != 0) {
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Location:</b></label> <input
						type="text" name="location" placeholder="Enter Location:"
						value="<%=ServletUtility.getParameter("location", request)%>">&emsp;

						<label><b>Bank name: </b></label> <%=HTMLUtility.getList("name", String.valueOf(bean.getBankName()), nameList)%>
						&nbsp; <input type="submit" name="operation"
						value="<%=AtmListCtl.OP_SEARCH%>">&nbsp; <input
						type="submit" name="operation"
						value="<%=AtmListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="25%">Bank Name</th>
					<th width="25%">Location</th>
					<th width="30%">Cash Available</th>
					<th width="25%">Security Code</th>
					<th width="10%">Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					bean = (AtmBean) it.next();
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getBankName()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getLocation()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getCashAvailable()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getSecurityCode()%></td>
					<td style="text-align: center;"><a
						href="AtmCtl?id=<%=bean.getId()%>">Edit</a></td>
					<%
					}
					%>
				
			</table>

			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=AtmListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=AtmListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=AtmListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=AtmListCtl.OP_NEXT%>"
						<%=nextListSize != 0 ? "" : "disabled"%>></td>
				</tr>
			</table>

			<%
			}
			if (list.size() == 0) {
			%>
			<table>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="<%=AtmListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>