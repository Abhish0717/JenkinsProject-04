<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="java.util.List"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.ApiBean"
	scope="request"></jsp:useBean>

<%
String _suc = ServletUtility.getSuccessMessage(request);
String _err = ServletUtility.getErrorMessage(request);
%>

<div class="container py-4" style="max-width: 580px;">
	<div class="card border-0 shadow-sm rounded-4 overflow-hidden">

		<div class="card-header text-white border-0 py-3 px-4"
			style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
			<h5 class="mb-0 fw-bold">
				<i class="bi bi-bookmark-star-fill me-2"></i>
				<%=bean.getId() > 0 ? "Edit API" : "Add API"%>
			</h5>
		</div>

		<div class="card-body px-4 py-4">

			<%
			if (_suc != null && !_suc.isEmpty()) {
			%>
			<div class="alert alert-success py-2">
				<i class="bi bi-check-circle-fill me-2"></i><%=_suc%></div>
			<%
			}
			%>
			<%
			if (_err != null && !_err.isEmpty()) {
			%>
			<div class="alert alert-danger py-2">
				<i class="bi bi-exclamation-triangle-fill me-2"></i><%=_err%></div>
			<%
			}
			%>

			<form action="<%=ORSView.API_CTL%>" method="POST">
				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

				<div class="mb-3">
					<label class="form-label fw-semibold">API Name <span
						class="text-danger">*</span></label> <input type="text" name="name"
						placeholder="Enter your API Name" class="form-control"
						maxlength="100"
						value="<%=DataUtility.getStringData(bean.getApiName())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("name", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Version<span
						class="text-danger">*</span></label> <input type="text" name="version"
						placeholder="Enter your Version" class="form-control"
						maxlength="200"
						value="<%=DataUtility.getStringData(bean.getVersion())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("version", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">End Point<span
						class="text-danger">*</span></label> <input type="text" name="end_point"
						placeholder="Enter your End Point" class="form-control"
						maxlength="200"
						value="<%=DataUtility.getStringData(bean.getEndPoint())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("end_point", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Status<span
						class="text-danger">*</span></label> <input type="text" name="status"
						placeholder="Enter your Status" class="form-control"
						maxlength="200"
						value="<%=DataUtility.getStringData(bean.getStatus())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("status", request)%></div>
				</div>

				<div class="d-flex gap-2 pt-2 border-top">
					<button type="submit" name="operation" value="<%=BaseCtl.OP_SAVE%>"
						class="btn btn-primary">
						<i class="bi bi-save me-1"></i> Save
					</button>
					<%
					if (bean.getId() > 0) {
					%>
					<button type="submit" name="operation"
						value="<%=BaseCtl.OP_DELETE%>" class="btn btn-danger"
						onclick="return confirm('Delete this user?')">
						<i class="bi bi-trash me-1"></i> Delete
					</button>
					<%
					}
					%>
					<a href="ApiListCtl" class="btn btn-secondary ms-auto"> <i
						class="bi bi-x-circle me-1"></i> Cancel
					</a>
				</div>
			</form>
		</div>
	</div>
</div>