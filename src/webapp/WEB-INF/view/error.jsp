<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="local.error.name.return_button"
	var="return_button" />
<spring:message code="local.error.name.general_message"
	var="general_message" />

<h1>
	<c:out value="${general_message}" />
</h1>

<c:if test="${not (errorMessage eq null)}">
	<font color="red" size="+3"> <spring:message 
			code="${errorMessage}" var="error_message" /> <c:out
			value="${error_message}" />
	</font>
	<c:set var="errorMessage" scope="session" value="local.message.name.emptyMessage"  />
</c:if><br>
<c:if test="${not (sessionScope.errorValidationMessage eq null)}">
	<font color="red" size="+3"> 
	<c:forEach var="error" items="${sessionScope.errorValidationMessage}">
	<fmt:message bundle="${loc}"
			key="${error}" var="error_message" /> <c:out
			value="${error_message}" /><br>
			</c:forEach>
	</font>
	<c:remove var = "errorValidationMessage" scope="session"/>
</c:if>
<br />
<form action="goToBasePage" method="get">
	 <input
		type="submit" value="${return_button}" />
</form>

