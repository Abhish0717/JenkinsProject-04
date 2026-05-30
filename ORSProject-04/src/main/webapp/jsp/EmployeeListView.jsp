<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.EmployeeListCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.EmployeeBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<title>Employee Payroll List</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
	<%@include file="ModuleView.jsp"%>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.EmployeeBean"
		scope="request"></jsp:useBean>

	<div align="center">
		<h1 align="center" style="margin-bottom: -15; color: navy;">Employee
			Payroll List</h1>

		<div style="height: 15px; margin-bottom: 12px">
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<form action="<%=ORSView.EMPLOYEE_LIST_CTL%>" method="post">
			<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;
			int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

			List<EmployeeBean> deptList = (List<EmployeeBean>) request.getAttribute("deptList");

			List<EmployeeBean> list = (List<EmployeeBean>) ServletUtility.getList(request);
			Iterator<EmployeeBean> it = list.iterator();

			if (list.size() != 0) {
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Employee Name:</b></label> <input
						type="text" name="name" placeholder="Enter Employee Name"
						value="<%=ServletUtility.getParameter("name", request)%>">&emsp;

						<label><b>Department: </b></label> <%=HTMLUtility.getList("department", String.valueOf(bean.getDepartment()), deptList)%>
						&nbsp; <input type="submit" name="operation"
						value="<%=EmployeeListCtl.OP_SEARCH%>">&nbsp; <input
						type="submit" name="operation"
						value="<%=EmployeeListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" style="width: 100%; border: groove;">
				<tr style="background-color: #e1e6f1e3;">
					<th width="5%"><input type="checkbox" id="selectall" /></th>
					<th width="5%">S.No</th>
					<th width="25%">Employee Name</th>
					<th width="25%">Department</th>
					<th width="30%">Salary</th>
					<th width="25%">Bonus</th>
					<th width="10%">Edit</th>
				</tr>

				<%
				while (it.hasNext()) {
					bean = (EmployeeBean) it.next();
				%>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						class="case" name="ids" value="<%=bean.getId()%>"></td>
					<td style="text-align: center;"><%=index++%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getEmployeeName()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getDepartment()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getSalary()%></td>
					<td style="text-align: center; text-transform: capitalize;"><%=bean.getBonus()%></td>
					<td style="text-align: center;"><a
						href="EmployeeCtl?id=<%=bean.getId()%>">Edit</a></td>
					<%
					}
					%>
				
			</table>

			<table style="width: 100%">
				<tr>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=EmployeeListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=EmployeeListCtl.OP_NEW%>"></td>
					<td align="center" style="width: 25%"><input type="submit"
						name="operation" value="<%=EmployeeListCtl.OP_DELETE%>"></td>
					<td style="width: 25%" align="right"><input type="submit"
						name="operation" value="<%=EmployeeListCtl.OP_NEXT%>"
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
						value="<%=EmployeeListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>