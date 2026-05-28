<%@page import="org.w3c.dom.NameList"%>
<%@page import="com.google.protobuf.StringValueOrBuilder"%>
<%@page import="com.google.protobuf.StringValue"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.RuleListCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.RuleBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>Rule Engine List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.RuleBean"
		scope="request"></jsp:useBean>

	<div align="center">
		<h1 align="center" style="margin-bottom: -15; color: navy;">Rule
			List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.RULE_LIST_CTL%>" method="post">
			<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;
			int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

			List<RuleBean> nameList = (List<RuleBean>) request.getAttribute("nameList");

			List<RuleBean> list = (List<RuleBean>) ServletUtility.getList(request);
			Iterator<RuleBean> it = list.iterator();

			if (list.size() != 0) {
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Rule Code:</b></label> <input
						type="text" name="code" placeholder="Enter Rule Code"
						value="<%=ServletUtility.getParameter("code", request)%>">&emsp;

						<label><b>Rule Name: </b></label> <%=HTMLUtility.getList("name", String.valueOf(bean.getRuleCode()), nameList)%>
						&nbsp; <input type="submit" name="operation"
						value="<%=RuleListCtl.OP_SEARCH%>">&nbsp; <input
						type="submit" name="operation" value="<%=RuleListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="25%">Rule Code</th>
					<th width="25%">Rule Name</th>
					<th width="30%">Condition</th>
					<th width="25%">Status</th>
					<th width="10%">Edit</th>
				</tr>
				<%
				while (it.hasNext()) {
					bean = (RuleBean) it.next();
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getRuleCode()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getRuleName()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getCondition()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getStatus()%></td>
					<td style="text-align: center;"><a
						href="RuleCtl?id=<%=bean.getId()%>">Edit</a></td>
					<%
					}
					%>
				
			</table>
			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=RuleListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=RuleListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=RuleListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=RuleListCtl.OP_NEXT%>"
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
						value="<%=RuleListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>