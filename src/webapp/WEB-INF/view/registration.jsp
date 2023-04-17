<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:message code="local.registration.name.title"
	var="title" />
<spring:message code="local.registration.name.first_name"
	var="first_name" />
<spring:message code="local.registration.name.last_name"
	var="last_name" />
<spring:message code="local.registration.name.login"
	var="login" />
<spring:message code="local.registration.name.password"
	var="password" />
<spring:message 
	code="local.registration.name.repeat_password" var="repeat_password" />
<spring:message 
	code="local.registration.name.registration_button" var="reg_button" />
<spring:message code="local.registration.name.email"
	var="email" />
	<spring:message code="local.registration.name.birthday"
	var="birthday" />
	<spring:message code="local.error.name.return_button"
	var="return_button" />
	
<link rel="stylesheet" href="/styles/registrationForm.css">
<script type="text/javascript">
	
<%@include file="/resources/script/validation.js"%>

</script>

<div class="wrapper">

	<div class="registration_form">

		<div class="title">
			<c:out value="${title}" />
		</div>


		<form:form name="form" action="registration" method="post" onsubmit="return validateRegistration()" modelAttribute="user">
			<div class="form_wrap">

					<div class="input_wrap">
						<label for="fname"><c:out value="${first_name}" /></label> <form:input
							type="text" id="firstName" path="userDetail.name" />
							<form:errors path="userDetail.name" cssClass="error" var="error">
							</form:errors>
					</div>


					<div class="input_wrap">
						<label for="lastname"><c:out value="${last_name}" /></label> <form:input
							type="text" id="lastName" path="userDetail.surname" />
							<form:errors path="userDetail.surname" cssClass="error" />
					</div>
					<div class="input_wrap">
						<label for="birthday"><c:out value="${birthday}" /></label> <form:input
							type="text" id="birthday" path="userDetail.birthday" value = "yyyy-MM-dd" />
							<form:errors path="userDetail.birthday" cssClass="error" />
					</div>


				<div class="input_wrap">
					<label for="email"><c:out value="${email}" /></label> <form:input
						type="text" id="email" path="userDetail.email" />
						<form:errors path="userDetail.email" cssClass="error" />
						
				</div>

				<div class="input_wrap">
					<label for="login"><c:out value="${login}" /></label> <form:input
						type="text" id="login" path="login" />
						<form:errors path="login" cssClass="error"/>
						
				</div>
				<div class="input_wrap">
					<label for="password"><c:out value="${password}" /></label> <form:input
						type="text" id="password" path="password" />
						<form:errors path="password" cssClass="error" />
				</div>
				<div class="input_wrap">
					<label for="repeatPassword"><c:out
							value="${repeat_password}" /></label> <input type="text"
						id="repeatPassword" name="repeatPassword">
				</div>

					 <input type="submit" value="${reg_button}" class="submit_btn">
			</div>
		</form:form>
		<form name="form" action="goToBasePage" method="get">
			 <input
				type="submit" value="${return_button}" class="submit_btn">
		</form>
	</div>
</div>
