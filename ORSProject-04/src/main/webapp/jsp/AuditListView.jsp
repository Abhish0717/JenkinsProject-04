<%@page import="org.w3c.dom.NameList"%>
<%@page import="com.google.protobuf.StringValueOrBuilder"%>
<%@page import="com.google.protobuf.StringValue"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.AuditListCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.AuditBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>Audit Trail List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.AuditBean"
		scope="request"></jsp:useBean>

	<div align="center">
		<h1 align="center" style="margin-bottom: -15; color: navy;">Audit
			List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.AUDIT_LIST_CTL%>" method="post">
			<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;
			int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

			List<AuditBean> codeList = (List<AuditBean>) request.getAttribute("codeList");

			List<AuditBean> list = (List<AuditBean>) ServletUtility.getList(request);
			Iterator<AuditBean> it = list.iterator();

			if (list.size() != 0) {
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Action:</b></label> <input
						type="text" name="action" placeholder="Enter Action"
						value="<%=ServletUtility.getParameter("action", request)%>">&emsp;

						<label><b>Audit Code: </b></label> <%=HTMLUtility.getList("code", String.valueOf(bean.getAuditCode()), codeList)%>
						&nbsp; <input type="submit" name="operation"
						value="<%=AuditListCtl.OP_SEARCH%>">&nbsp; <input
						type="submit" name="operation" value="<%=AuditListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="25%">Audit Code</th>
					<th width="25%">Action</th>
					<th width="30%">Performed By</th>
					<th width="25%">Status</th>
					<th width="10%">Edit</th>
				</tr>
				<%
				while (it.hasNext()) {
					bean = (AuditBean) it.next();
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getAuditCode()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getAction()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getPerformedBy()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getStatus()%></td>
					<td style="text-align: center;"><a
						href="AuditCtl?id=<%=bean.getId()%>">Edit</a></td>
					<%
					}
					%>
				
			</table>
			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=AuditListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=AuditListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=AuditListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=AuditListCtl.OP_NEXT%>"
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
						value="<%=AuditListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>