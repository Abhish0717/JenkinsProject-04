<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.util.DataUtility"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="java.util.List"%>

<jsp:useBean id="bean" class="com.sunilos.p4.bean.CitizenBean"
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
				<%=bean.getId() > 0 ? "Edit Citizen Management" : "Add Citizen Management"%>
			</h5>
		</div>

		<div class="card-body px-4 py-4">

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

			<form action="<%=ORSView.CITIZEN_CTL%>" method="POST">
				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

				<div class="mb-3">
					<label class="form-label fw-semibold">Name <span
						class="text-danger">*</span></label> <input type="text" name="name"
						placeholder="Enter your Name" class="form-control" maxlength="100"
						value="<%=DataUtility.getStringData(bean.getName())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("name", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Mobile No<span
						class="text-danger">*</span></label> <input type="text" name="mobile_no"
						placeholder="Enter your Mobile No" class="form-control"
						maxlength="200"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("mobile_no", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Address<span
						class="text-danger">*</span></label> <input type="text" name="address"
						placeholder="Enter your Address" class="form-control"
						maxlength="200"
						value="<%=DataUtility.getStringData(bean.getAddress())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("address", request)%></div>
				</div>

				<div class="mb-3">
					<label class="form-label fw-semibold">Reward Points<span
						class="text-danger">*</span></label> <input type="text"
						name="reward_points" placeholder="Enter your Reward Points"
						class="form-control" maxlength="200"
						value="<%=DataUtility.getStringData(bean.getRewardPoints())%>">
					<div class="text-danger small mt-1"><%=ServletUtility.getErrorMessage("reward_points", request)%></div>
				</div>

				<div class="d-flex gap-2 pt-2 border-top">
					<button type="submit" name="operation" value="<%=BaseCtl.OP_SAVE%>"
						class="btn btn-primary">
						<i class="bi bi-save me-1"></i> Save
					</button>
					<%
					if (bean.getId() > 0) {
					%>
					<%-- <button type="submit" name="operation"
						value="<%=BaseCtl.OP_DELETE%>" class="btn btn-danger"
						onclick="return confirm('Delete this user?')">
						<i class="bi bi-trash me-1"></i> Delete
					</button> --%>
					<button type="submit" name="operation"
						value="<%=BaseCtl.OP_CANCEL%>" class="btn btn-danger ms-auto">
						<i class="bi bi-x-circle me-1"></i> Cancel
					</button>

					<%
					} else {
					%>

					<a href="CitizenCtl" class="btn btn-secondary ms-auto"> <i
						class="bi bi-arrow-clockwise me-1"></i> Reset
					</a>
					<%
					}
					%>

				</div>
			</form>
		</div>
	</div>
</div>