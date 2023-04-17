<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="local.addNews.name.update_button"
	var="update_button" />
<spring:message code="local.addNews.name.save_button"
	var="save_button" />
<spring:message code="local.addNews.name.cancel_button"
	var="cancel_button" />
<spring:message code="local.viewNews.name.title"
	var="news_title" />
<spring:message code="local.viewNews.name.date" var="date" />
<spring:message code="local.viewNews.name.brief" var="brief" />
<spring:message code="local.viewNews.name.content"
	var="news_content" />
<spring:message code="local.addNews.name.add_title"
	var="add_title" />
<spring:message code="local.newsList.name.news"
	var="news_link" />

<script type="text/javascript">
	
<%@include file="/resources/script/validation.js"%>
	
</script>

<div class="body-title">
	<a href="controller?command=go_to_news_list"><c:out
			value="${news_link }" /></a>
	<c:out value="${add_title}" />
</div>

<form:form name="form" action="updateNews" method="post"
	onsubmit="return validateNewsForm()" modelAttribute="news">
	<div class="add-table-margin">

		<table class="news_text_format">
			<tr>
				<td class="space_around_title_text"><c:out
						value="${news_title }" /></td>
				<td>
					<div class="title_field">
						<form:input type="text" path="title" value="${news.title}"
							class="text_field" />
							<form:errors path="title" cssClass = "errorNews_title"/>
						<br />
					</div>
				</td>
				

			</tr>
			<tr>
				<td class="space_around_title_text"><c:out value="${date}" /></td>
				<td><form:input type="date" path="newsDate"
						value="${news.newsDate}" class="date_field" /><br />
						<form:errors path="newsDate" cssClass = "errorNews_date"/></td>
			</tr>
			<tr>
				<td class="space_around_title_text"><c:out value="${brief}" /></td>
				<td>
					<div class="brief_field_format">
						<form:input type="text" path="briefNews" value="${news.briefNews}"
							class="brief_field" />
							<form:errors path="briefNews" cssClass = "errorNews_brief"/>
						<br />
					</div>
				</td>
			</tr>
			<tr>
				<td class="space_around_title_text"><c:out
						value="${news_content}" /></td>
				<td>
					<div class="content_field_format">
						<form:input type="text" path="content" value="${news.content}"
							class="content_field" />
							<form:errors path="content" cssClass = "errorNews_content"/>
						<br />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="button_field">
						<c:choose>
							<c:when test="${not (news eq null)}">
								<input type="hidden" name="local" value="${sessionScope.local}" />
								<input type="hidden" name="idNews" value="${news.idNews}" />
								<input type="submit" class="update_button"
									value="${update_button}" />
							</c:when>
							<c:otherwise>
								<input type="hidden" name="command" value="save_news" />
								<input type="hidden" name="local" value="${sessionScope.local}" />
								<input type="submit" class="update_button"
									value="${save_button}" />
							</c:otherwise>
						</c:choose>
					</div>
				</td>

			</tr>
		</table>
	</div>
</form:form>

<table>
	<tr>
		<td>
			<form action="goToViewNews" method="post">
				<input type="hidden" name="command" value="go_to_view_news" /> <input
					type="hidden" name="id" value="${news.idNews}" /> <input
					type="submit" class="update_button" value="${cancel_button}" />
			</form>

		</td>
	</tr>
</table>