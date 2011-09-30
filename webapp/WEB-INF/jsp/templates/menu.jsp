<%@ page contentType="text/html; charset=UTF-8" %>
<div class="topbar menu" data-scrollspy="scrollspy">
	<ul class="tabs">
		<li><a href="<c:url value="/" />"><fmt:message key="header.home" /></a></li>
		<li><a href="<c:url value="/escalonamento-processo" />"><fmt:message key="header.escalonamento.processo" /></a></li>
		<li><a href="<c:url value="/escalonamento-disco" />"><fmt:message key="header.escalonamento.disco" /></a></li>
		<li><a href="<c:url value="/paginacao-memoria" />"><fmt:message key="header.paginacao.memoria" /></a></li>
		<li><a href="<c:url value="/sobre" />"><fmt:message key="header.sobre" /></a></li>
	</ul>
</div>