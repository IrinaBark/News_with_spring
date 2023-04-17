<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="local.menu.name.welcome" var="welcome" />
<spring:message code="local.header.name.newsTitle"
	var="newsTitle" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/script/validation.js"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/styles/newsStyle.css"/>" />
<title> <c:out value="${newsTitle}" /> </title>

</head>
<body>
	<div class="page">
		<div class="header">
			<c:import url="/WEB-INF/view/header.jsp" />
		</div>

		<div class="base-layout-wrapper">
			<div class="menu">

				<c:if test="${not (sessionScope.user eq 'active')}">
					<c:out value="${welcome}" />
					<%-- <c:import url=""></c:import> --%>
				</c:if>
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/view/menu.jsp" />
				</c:if>
			</div>

			<div class="content">

				<c:if test="${not (sessionScope.user eq 'active')}">
					<c:if test="${(requestScope.presentation eq 'registration')}">
						<c:import url="/WEB-INF/view/registration.jsp" />
					</c:if>

					<c:if test="${requestScope.presentation eq 'error'}">
						<c:import url="/WEB-INF/view/error.jsp" />
					</c:if>
					<c:if test="${not (requestScope.presentation eq 'error')}">
						<c:if test="${not (requestScope.presentation eq 'registration')}">
							<c:import url="/WEB-INF/view/guestInfo.jsp" />
						</c:if>
					</c:if>
				</c:if>

				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/view/body.jsp" />
				</c:if>


			</div>
		</div>

		<div class="footer">

			<c:import url="/WEB-INF/view/footer.jsp" />
		</div>
	</div>
</body>
</html>