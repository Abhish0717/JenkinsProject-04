<%@page import="com.sunilos.p4.ctl.HospitalListCtl"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.bean.HospitalBean"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%
int pageNo = ServletUtility.getPageNo(request);
int pageSize = ServletUtility.getPageSize(request);
int index = ((pageNo - 1) * pageSize) + 1;
List list = ServletUtility.getList(request);
Iterator<HospitalBean> it = list.iterator();
String _suc = ServletUtility.getSuccessMessage(request);
String _err = ServletUtility.getErrorMessage(request);
%>

<div class="container-fluid px-4 py-4" style="max-width: 900px;">
	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">
		<div
			class="card-header text-white border-0 py-3 px-4 d-flex justify-content-between align-items-center"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
			<h5 class="mb-0 fw-bold">
				<i class="bi bi-hospital-fill me-2"></i>Hospital Record List
			</h5>
			<a href="<%=ORSView.HOSPITAL_CTL%>"
				class="btn btn-sm btn-light text-primary fw-semibold"> <i
				class="bi bi-plus-circle me-1"></i> Add Hospital Record
			</a>
		</div>
	</div>
	<form action="<%=ORSView.HOSPITAL_LIST_CTL%>" method="POST">
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">

		<div
			class="p-3 bg-light border-bottom d-flex flex-wrap gap-2 align-items-center">
			<input type="text" name="name" class="form-control form-control-sm"
				style="max-width: 220px;" placeholder="Search by Name"
				value="<%=ServletUtility.getParameter("name", request)%>">
			<button type="submit" name="operation" value="<%=BaseCtl.OP_SEARCH%>"
				class="btn btn-primary btn-sm">
				<i class="bi bi-search me-1"></i> Search
			</button>
			<button type="submit" name="operation" value="<%=BaseCtl.OP_DELETE%>"
				class="btn btn-danger btn-sm ms-auto"
				onclick="return confirm('Delete selected subjects?')">
				<i class="bi bi-trash me-1"></i> Delete Selected
			</button>
		</div>
		<%
		if (_suc != null && !_suc.isEmpty()) {
		%>
		<div class="alert alert-success py-2">
			<i class="bi bi-check-circle-fill me-2"></i><%=_suc%><button
				type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
		<%
		}
		%>
		<%
		if (_err != null && !_err.isEmpty()) {
		%>
		<div class="alert alert-danger py-2">
			<i class="bi bi-exclamation-triangle-fill me-2"></i><%=_err%><button
				type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
		<%
		}
		%>

		<div class="table-responsive">
			<table class="table table-hover align-middle mb-0">
				<thead class="table-light">
					<tr>
						<th width="40"><input type="checkbox"
							onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)"></th>
						<th>Sr.No.</th>
						<th>ID</th>
						<th>Patient Name</th>
						<th>Doctor Name</th>
						<th>Disease</th>
						<th>Room Number</th>
					</tr>
				</thead>
				<tbody>
					<%
					while (it.hasNext()) {
						HospitalBean bean = it.next();
					%>
					<tr>
						<td><input type="checkbox" name="ids"
							value="<%=bean.getId()%>"></td>
						<td class="text-muted small"><%=index++%></td>
						<td class="text-muted small"><%=bean.getId()%></td>
						<td class="fw-semibold"><%=bean.getPatientName()%></td>
						<td><%=bean.getDoctorName()%></td>
						<td><%=bean.getDisease()%></td>
						<td><%=bean.getRoomNumber()%></td>
						<td><a href="HospitalCtl?id=<%=bean.getId()%>"
							class="btn btn-sm btn-outline-primary"> <i
								class="bi bi-pencil"></i> Edit
						</a></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<div class="p-3 border-top">
			<%@ include file="ListFooter.jsp"%>
		</div>
	</form>
</div>
</div>