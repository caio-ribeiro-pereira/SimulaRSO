<%@ page contentType="text/html; charset=UTF-8" %>
<div class="topbar menu" data-scrollspy="scrollspy">
	<ul class="tabs">
		<li><a href="<c:url value="/" />" title="<fmt:message key="header.home" />" rel="me"><fmt:message key="header.home" /></a></li>
		<li><a href="<c:url value="/escalonamento-processo" />" title="<fmt:message key="header.escalonamento.processo" />" rel="me"><fmt:message key="header.escalonamento.processo" /></a></li>
		<li><a href="<c:url value="/escalonamento-disco" />" title="<fmt:message key="header.escalonamento.disco" />" rel="me"><fmt:message key="header.escalonamento.disco" /></a></li>
		<li><a href="<c:url value="/paginacao-memoria" />" title="<fmt:message key="header.paginacao.memoria" />" rel="me"><fmt:message key="header.paginacao.memoria" /></a></li>
		<li><a href="<c:url value="/sobre" />" title="<fmt:message key="header.sobre" />" rel="me"><fmt:message key="header.sobre" /></a></li>
	</ul>
</div>