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
	<c:if test="${not empty warning}">
		<div id="warning" class="clearfix">
			<hr>
			<div class="clearfix ui-state-error ui-corner-all">
				<p class="error-message">
					<a href="javascript:void(0);" class="hide"><fmt:message key="misc.hide" /></a>
					<span class="ui-icon ui-icon-alert" style="float:left;margin-top:5px;"></span>
					<strong>${warning}</strong>
				</p>
			</div>
		</div>
		<script type="text/javascript">
			head.ready(function(){
				$('.hide').click(function(){
					$('#warning').fadeOut();
				});
			});
		</script>
	</c:if>
</header>
<hr>