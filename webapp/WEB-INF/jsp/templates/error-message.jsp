<%@ page contentType="text/html; charset=UTF-8" %>
<c:if test="${not empty notices}">
	<c:forEach var="notice" items="${notices}">
	    <strong class="clearfix error-message">${notice.value}</strong>
	</c:forEach>
</c:if>
