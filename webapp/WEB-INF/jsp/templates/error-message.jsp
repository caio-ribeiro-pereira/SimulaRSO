<%@ page contentType="text/html; charset=UTF-8" %>
<c:if test="${not empty errors}">
	<c:forEach var="error" items="${errors}">
	    <strong class="clearfix error-message">${error.message}</strong>
	</c:forEach>
</c:if>
