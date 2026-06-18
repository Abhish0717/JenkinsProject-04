<%@page import="com.sunilos.p4.ctl.ORSView"%>
<%@page import="com.sunilos.p4.bean.UserBean"%>
<%@page import="com.sunilos.p4.bean.RoleBean"%>

<%
UserBean currentUser = (UserBean) session.getAttribute("user");
boolean loggedIn = currentUser != null;
boolean isStudent = loggedIn && currentUser.getRoleId() == RoleBean.STUDENT;
boolean isAdmin = loggedIn && currentUser.getRoleId() == RoleBean.ADMIN;
String firstName = loggedIn ? currentUser.getFirstName() : "Guest";
%>
<div class="container-fluid px-4 py-4" style="max-width: 1100px;">

	<!-- ===== Hero Banner ===== -->
	<div
		class="rounded-4 text-white p-4 p-md-5 mb-4 d-flex align-items-center gap-4 shadow"
		style="background: linear-gradient(135deg, #0d2137 0%, #1565c0 100%);">
		<div
			class="flex-shrink-0 rounded-circle d-flex align-items-center justify-content-center"
			style="width: 80px; height: 80px; background: rgba(255, 255, 255, 0.15); border: 2px solid rgba(255, 255, 255, 0.25); font-size: 2.3rem;">
			<i class="fas fa-graduation-cap"></i>
		</div>
		<div>
			<h1 class="fw-bold mb-1 fs-2">
				Welcome<%
			if (loggedIn) {
			%>,
				<%=firstName%>!<%
			} else {
			%>
				to ORS<%
			}
			%>
			</h1>
			<p class="mb-0 opacity-75">Online Result System &mdash; Powered
				by Rays Technologies</p>
		</div>
	</div>
</div>