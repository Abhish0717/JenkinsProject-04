<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.RegistrationListCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.RegistrationBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>Registration List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.RegistrationBean"
		scope="request"></jsp:useBean>

	<div align="center">
		<h1 align="center" style="margin-bottom: -15; color: navy;">Registration List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.REGISTRATION_LIST_CTL%>" method="post">
			<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;
			int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

			List<RegistrationBean> codeList = (List<RegistrationBean>) request.getAttribute("codeList");

			List<RegistrationBean> list = (List<RegistrationBean>) ServletUtility.getList(request);
			Iterator<RegistrationBean> it = list.iterator();

			if (list.size() != 0) {
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Registration Code: </b></label> <%=HTMLUtility.getList("code", String.valueOf(bean.getRegistrationCode()), codeList)%>
						&nbsp; <label><b>Registration Name:</b></label>
						
						<input type="text"
						name="first" placeholder="Enter First Name"
						value="<%=ServletUtility.getParameter("first", request)%>">&emsp;
						<input type="submit" name="operation"
						value="<%=RegistrationListCtl.OP_SEARCH%>">&nbsp; <input
						type="submit" name="operation"
						value="<%=RegistrationListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="25%">Registration Code</th>
					<th width="25%">First Name</th>
					<th width="30%">Last Name</th>
					<th width="25%">Status</th>
					<th width="10%">Edit</th>
				</tr>
				<%
				while (it.hasNext()) {
					bean = (RegistrationBean) it.next();
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getRegistrationCode()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getFirstName()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getLastName()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getStatus()%></td>
					<td style="text-align: center;"><a
						href="RegistrationCtl?id=<%=bean.getId()%>">Edit</a></td>
					<%
					}
					%>
				
			</table>
			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=RegistrationListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=RegistrationListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=RegistrationListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=RegistrationListCtl.OP_NEXT%>"
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
						value="<%=RegistrationListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>