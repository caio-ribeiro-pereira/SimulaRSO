<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${homeController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="main.bem.vindo" /></title>
		<%@include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<%@ include file="../templates/menu.jsp"%>
		<div class="container">
			<%@ include file="../templates/header.jsp"%>
			<hr>
		  	<section>
		  		<article>
		  			<p><strong><fmt:message key="main.painel.idioma" />:</strong></p>
					<div id="idioma-box" class="row center">
						<button class="btn primary" type="button" value="pt_BR"><fmt:message key="misc.idioma.pt" /></button>
		  		   		<button class="btn primary" type="button" value="en_US"><fmt:message key="misc.idioma.en" /></button>
		  		   	</div>
		  		   	<form action="<c:url value="/idioma" />" method="post" id="idioma-form">
		  				<input type="hidden" name="idioma" id="idioma-val">
		  			</form>
		  		</article>
		  		<hr>
		  		<article class="justify">
			  		<p><strong><fmt:message key="main.sobre.projeto" />:</strong></p>
			  		<p><fmt:message key="main.sobre.msg1" /></p>
					<p><fmt:message key="main.sobre.msg2" /></p>
					<p><fmt:message key="main.sobre.msg3" />&nbsp;<a href="http://www.unisantos.br" target="_blank" title="Universidade Católica de Santos." rel="no-follow">Universidade Católica de Santos.</a></p>
			  	</article>
		  		<hr>
		  		<article class="justify">
		  			<p><strong><fmt:message key="main.painel.aviso" />:</strong></p>
					<p><fmt:message key="main.painel.msg1" /></p>
					<p><fmt:message key="main.painel.msg2" /></p>
					<p><fmt:message key="main.painel.msg4" />&nbsp;<a href="https://github.com/caio-ribeiro-pereira/SimulaRSO/issues" target="_blank" title="Github.com"><fmt:message key="main.painel.report.bug" /></a></p>
					<p><fmt:message key="main.painel.msg3" /></p>
					<%@ include file="../templates/browsers.jsp"%>
			  	</article>
			  	<hr>
		  		<article class="justify">
			  		<p><strong><fmt:message key="main.atualizacoes" />:</strong></p>
			  		<p><fmt:message key="main.atualizacoes.msg5" /></p>
			  		<p><fmt:message key="main.atualizacoes.msg4" /></p>
			  		<p><fmt:message key="main.atualizacoes.msg3" /></p>
			  		<p><fmt:message key="main.atualizacoes.msg2" /></p>
			  		<p><fmt:message key="main.atualizacoes.msg1" /></p>
			  	</article>
			</section>
			<%@ include file="../templates/footer.jsp"%>
		</div>
		<script type="text/javascript">
			head.ready(function(){
				$('#idioma-box button').click(function(){
					$('#idioma-val').val($(this).val());
					$('#idioma-form').submit();
				});
			});
		</script>
	</body>
</html>
</fmt:bundle>