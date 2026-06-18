<%@ taglib uri="http://www.sunilos.com/ors-tags" prefix="ors"%>
<%@page import="com.sunilos.p4.ctl.BaseCtl"%>
<%@page import="com.sunilos.p4.util.ServletUtility"%>
<%@page import="java.util.List"%>

<%
int pageNo1 = ServletUtility.getPageNo(request);
List nextList = (List) request.getAttribute("nextList");
%>

<div class="d-flex justify-content-center align-items-center gap-2">
	<button type="submit" name="operation" value="<%=BaseCtl.OP_PREVIOUS%>"
		class="btn btn-outline-primary btn-sm"
		<%=pageNo1 == 1 ? "disabled" : ""%>>
		<i class="bi bi-chevron-left"></i> <ors:message key="button.previous" />
	</button>
	<button type="submit" name="operation" value="<%=BaseCtl.OP_NEXT%>"
		class="btn btn-outline-primary btn-sm"
		<%=nextList.size() == 0 ? "disabled" : ""%>>
		<ors:message key="button.next" /> <i class="bi bi-chevron-right"></i>
	</button>
</div>