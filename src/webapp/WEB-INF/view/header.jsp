<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="wrapper">
	<div class="newstitle">
		<spring:message code="local.header.name.newsTitle" />
	</div>

	<div class="local-link">

		<div align="right" class="local">
			<c:if test="${not (param.id eq null)}">
				<a href="?lang=en&id=${param.id }"><spring:message
						code="local.header.name.en" /></a>
			| <a href="?lang=ru&id=${param.id }"><spring:message
						code="local.header.name.ru" /></a>

			</c:if>
			<c:if test="${(param.id eq null)}">
				<a href="?lang=en"><spring:message code="local.header.name.en" /></a>
			| <a href="?lang=ru"><spring:message code="local.header.name.ru" /></a>
			</c:if>


		</div>

		<c:if test="${not (user eq 'active')}">

			<div align="right">
				<form action="doSignIn" method="post">
					<spring:message code="local.header.name.login" />
					<input type="text" name="login" value="" /><br />
					<spring:message code="local.header.name.password" />
					<input type="password" name="password" value="" /><br />

					<c:if test="${not (param.info_message eq null)}">
						<font color="white" size="+2"> <spring:message 
								code="${param.info_message}" var="info_message" /> <c:out
								value="${info_message}" />
						</font>
						<c:set var="info_message" scope="session"
							value="local.message.name.emptyMessage" />
					</c:if>

					<c:if test="${not (param.AuthenticationError eq null)}">
						<font color="red" size="+2"> 
						<spring:message 
								code="${param.AuthenticationError}" var="info_message_auth"/>
<%-- 						<fmt:message bundle="${loc}" --%>
<%-- 								key="${AuthenticationError}" var="login_error" /> --%>
							<c:out value="${info_message_auth}" />
						</font>
					</c:if>
					<a href="goToRegistrationPage"><spring:message
							code="local.header.name.registration" /></a> 
							<spring:message code="local.header.name.signIn" var="signIn"></spring:message>
							<input type="submit"
						value="${signIn}" /><br />
				</form>
			</div>
		</c:if>
		<c:if test="${sessionScope.user eq 'active'}">
			<div align="right">
				<form action="signOut" method="post">
					<spring:message code="local.header.name.signOut" var="signOut"></spring:message>
					<input
						type="submit" value="${signOut}" /><br />

					<c:if test="${not (param.user_info_message eq null)}">
					<spring:message 
								code="${param.user_info_message}" var="user_message"/> 
						<font color="white" size="+2"> 
								<c:out value="${user_message }" /> 
						</font> 

					</c:if>
				</form>
			</div>
		</c:if>
	</div>
</div>
