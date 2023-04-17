<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="local.newsList.name.news"
	var="news_link" />
<spring:message code="local.newsList.name.newsList"
	var="newsList" />
<spring:message code="local.newsList.name.editLink"
	var="editLink" />
<spring:message code="local.newsList.name.viewLink"
	var="viewLink" />
<spring:message code="local.newsList.name.delete_button"
	var="delete_button" />


<div class="body-title">
	<a href="goToNewsList"><c:out
			value="${news_link}" /> </a>
	<c:out value="${newsList}" />
</div>

<form action="goToConfirmationPage" method="get">
	<c:forEach var="news" items="${news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<c:out value="${news.newsDate}" />
				</div>

				<div class="news-content">
					<c:out value="${news.briefNews}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${role eq 'admin'}">

							<c:url var="edit_link" value="/news/goToEditNews">
								<c:param name="id" value="${news.idNews}" />
							</c:url>
							<a href="${edit_link}"><c:out value="${editLink}" /> </a>
						</c:if>
						<c:url var="view_link" value="/news/goToViewNews">
							<c:param name="id" value="${news.idNews}" />
						</c:url>
						<a href="${view_link }"><c:out value="${viewLink}" /> </a>

						<c:if test="${role eq 'admin'}">
							<input type="checkbox" name="idNews" value="${news.idNews }" />
						</c:if>
					</div>
				</div>

			</div>
		</div>

	</c:forEach>

	<c:if test="${role eq 'admin'}">
		<div class="delete-button-position">

			
			<input type="submit" value="${delete_button}" class="delete_button" />

		</div>
	</c:if>

	<!-- 	 	<logic:notEmpty name="newsForm" property="newsList"> -->
	<!-- 		<div class="delete-button-position"> -->
	<!-- 			<html:submit> -->
	<!-- 				<bean:message key="locale.newslink.deletebutton" /> -->
	<!-- 			</html:submit> -->
	<!-- 		</div> -->
	<!-- 	</logic:notEmpty>  -->

	<div class="no-news">
		<c:if test="${news eq null}">
        No news.
	</c:if>
	</div>
</form>
