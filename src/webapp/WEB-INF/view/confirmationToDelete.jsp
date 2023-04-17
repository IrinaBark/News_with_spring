<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="local.confirmationToDelete.name.warning_message"
	var="warning_message" />
<spring:message code="local.confirmationToDelete.name.yes_button"
	var="yes_button" />
<spring:message code="local.confirmationToDelete.name.no_button"
	var="no_button" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h3>
			<c:out value="${warning_message}" />
		</h3>
		<table>
			<tr>
				<td>
					<div class="yes_button">

						<form action="deleteNews" method="post">
							<c:forEach items="${idNews }" var="id">
								<input type="hidden" name="idNews" value="${id }" />
<%-- 								<c:out value="${id }" /> --%>
							</c:forEach>
							<input type="submit" value="${yes_button}" />
						</form>
					</div>
				</td>
				<td>
					<div class="no_button">
						<c:if test="${fn:length(idNews) gt 1}">
							<form action="goToNewsList" method="get">
								<input type="submit" value="${no_button}" />
							</form>
						</c:if>
						
<%-- 							<c:if test="${fn:length(idNews) gt 1}"> --%>
<!-- 								<input type="hidden" name="command" value="go_to_news_list" /> -->
<%-- 								<input type="submit" value="${no_button}" /> --%>
<%-- 							</c:if> --%>
							<c:if test="${fn:length(idNews) eq 1}">
							<form action="goToViewNews" method="get">
								<input type="hidden" name="id" value="${idNews[0]}" />
								<input type="submit" value="${no_button}" />
								</form>
							</c:if>

						</form>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>