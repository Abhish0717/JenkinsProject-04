<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.Collections"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.SubscriptionListCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.bean.SubscriptionBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>Subscription List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>
	<div align="center">
		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.SubscriptionBean"
			scope="request"></jsp:useBean>
		<h1 align="center" style="margin-bottom: -15; color: navy;">Subscription
			List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.SUBSCRIPTION_LIST_CTL%>" method="POST">
			<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;
			int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

			List<SubscriptionBean> namelist = (List<SubscriptionBean>) request.getAttribute("nameList");

			List<SubscriptionBean> list = (List<SubscriptionBean>) ServletUtility.getList(request);
			Iterator<SubscriptionBean> it = list.iterator();

			if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Plan Name : </b></label> <%=HTMLUtility.getList("name", String.valueOf(bean.getStatus()), namelist)%>&emsp;
						<label><b>Plan Code :</b></label> <input type="text" name="name"
						placeholder="Enter Plan Code"
						value="<%=ServletUtility.getParameter("code", request)%>">&emsp;
						<input type="submit" name="operation"
						value="<%=SubscriptionListCtl.OP_SEARCH%>">&nbsp; <input
						type="submit" name="operation"
						value="<%=SubscriptionListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="25%">Plan Name</th>
					<th width="25%">Plan Code</th>
					<th width="15%">Subscription Price</th>
					<th width="15%">Subscription Status</th>
					<th width="5%">Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					bean = (SubscriptionBean) it.next();
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getPlanName()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getPlanCode()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getPrice()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getStatus()%></td>
					<td style="text-align: center;"><a
						href="SubscriptionCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
				}
				%>
			</table>

			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=SubscriptionListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=SubscriptionListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=SubscriptionListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=SubscriptionListCtl.OP_NEXT%>"
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
						value="<%=SubscriptionListCtl.OP_BACK%>"></td>

				</tr>
			</table>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>