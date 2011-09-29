<%@ page contentType="text/html; charset=UTF-8" %>
<header>
	<ul class="tabs">
		<li><a href="<c:url value="/" />"><fmt:message key="header.home" /></a></li>
		<li><a href="<c:url value="/escalonamento-processo" />"><fmt:message key="header.escalonamento.processo" /></a></li>
		<li><a href="<c:url value="/escalonamento-disco" />"><fmt:message key="header.escalonamento.disco" /></a></li>
		<li><a href="<c:url value="/paginacao-memoria" />"><fmt:message key="header.paginacao.memoria" /></a></li>
		<li><a href="<c:url value="/sobre" />"><fmt:message key="header.sobre" /></a></li>
	</ul>
	<h1><fmt:message key="header.titulo" /></h1>
	<c:if test="${not empty warning}">
		<div class="alert-message warning">
			<a href="#" class="close"><fmt:message key="misc.hide" /></a>
			<p><strong>${warning}</strong></p>
		</div>
		<script type="text/javascript">
			head.ready(function(){
				$('.alert-message').alert('close');
			});
		</script>
	</c:if>
</header>
<hr>