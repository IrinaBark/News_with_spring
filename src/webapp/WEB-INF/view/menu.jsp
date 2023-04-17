<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="local.menu.name.newsList"
	var="newsList" />
<spring:message code="local.menu.name.addNews" var="addNews" />


<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">News</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: center;">
                <ul   style="text-align: center;">
				<li class="link_menu" style="padding-left: 0px;"><a
					href="goToNewsList"><c:out
							value="${newsList}" /></a><br /></li>

				<c:if test="${role eq 'admin'}">
					<li style="padding-left: 0px;"><a
						href="goToAddNews"><c:out
								value="${addNews}" /> </a> <br /></li>
				</c:if>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	<!--  grey free space at the bottom of menu -->
	<div style="height: 25px;"></div>
</div>

