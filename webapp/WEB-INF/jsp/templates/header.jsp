<%@ page contentType="text/html; charset=UTF-8" %>
<header class="clearfix">
	<h1 class="clearfix"><fmt:message key="header.titulo" /></h1>
	<nav class="clearfix">
		<a class="left" href="<c:url value="/" />"><fmt:message key="header.home" /></a>
		<a class="middle" href="<c:url value="/escalonamento-processo" />"><fmt:message key="header.escalonamento.processo" /></a>
		<a class="middle" href="<c:url value="/escalonamento-disco" />"><fmt:message key="header.escalonamento.disco" /></a>
		<a class="middle" href="<c:url value="/paginacao-memoria" />"><fmt:message key="header.paginacao.memoria" /></a>
		<a class="right" href="<c:url value="/sobre" />"><fmt:message key="header.sobre" /></a>
	</nav>
	<br>
	<c:if test="${not empty warning}">
    	<strong class="clearfix error-message">${warning}</strong>
	</c:if>
</header>
<hr>