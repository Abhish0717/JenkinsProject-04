<%@page import="org.w3c.dom.NameList"%>
<%@page import="com.google.protobuf.StringValueOrBuilder"%>
<%@page import="com.google.protobuf.StringValue"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.TransformationListCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.TransformationBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>Transformation List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.TransformationBean"
		scope="request"></jsp:useBean>

	<div align="center">
		<h1 align="center" style="margin-bottom: -15; color: navy;">Transformation
			List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.TRANSFORMATION_LIST_CTL%>" method="post">
			<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;
			int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

			List<TransformationBean> nameList = (List<TransformationBean>) request.getAttribute("nameList");

			List<TransformationBean> list = (List<TransformationBean>) ServletUtility.getList(request);
			Iterator<TransformationBean> it = list.iterator();

			if (list.size() != 0) {
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Transform Code:</b></label> <input
						type="text" name="code" placeholder="Enter Transform Code"
						value="<%=ServletUtility.getParameter("code", request)%>">&emsp;

						<label><b>Rule Name: </b></label> <%=HTMLUtility.getList("name", String.valueOf(bean.getRuleName()), nameList)%>
						&nbsp; <input type="submit" name="operation"
						value="<%=TransformationListCtl.OP_SEARCH%>">&nbsp; <input
						type="submit" name="operation" value="<%=TransformationListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="25%">Transform Code</th>
					<th width="25%">Rule Name</th>
					<th width="30%">Logic</th>
					<th width="25%">Status</th>
					<th width="10%">Edit</th>
				</tr>
				<%
				while (it.hasNext()) {
					bean = (TransformationBean) it.next();
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getTransformCode()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getRuleName()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getLogic()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getStatus()%></td>
					<td style="text-align: center;"><a
						href="TransformationCtl?id=<%=bean.getId()%>">Edit</a></td>
					<%
					}
					%>
				
			</table>
			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=TransformationListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=TransformationListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=TransformationListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=TransformationListCtl.OP_NEXT%>"
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
						value="<%=TransformationListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>