<%@ page contentType="text/html; charset=UTF-8" %>
<header class="clearfix">
	<h1 class="clearfix"><fmt:message key="main.titulo" /></h1>
	<nav class="clearfix">
		<a class="left" href="<c:url value="/home" />"><fmt:message key="main.home" /></a>
		<a class="middle" href="<c:url value="/escalonamento-processo" />"><fmt:message key="main.escalonamento.processo" /></a>
		<a class="middle" href="<c:url value="/escalonamento-disco" />"><fmt:message key="main.escalonamento.disco" /></a>
		<a class="middle" href="<c:url value="/paginacao-memoria" />"><fmt:message key="main.paginacao.memoria" /></a>
		<a class="right" href="<c:url value="/sobre" />"><fmt:message key="main.sobre" /></a>
	</nav>
</header>
<hr>