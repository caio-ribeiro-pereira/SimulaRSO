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
		<div class="container">
			<%@ include file="../templates/header.jsp"%>
		  	<section class="row">
		  		<article id="idioma-box">
		  			<p><strong><fmt:message key="main.painel.idioma" />:</strong></p>
					<p>
						<a class="btn info" href="<c:url value="/idioma/?idioma=pt_BR" />"><fmt:message key="misc.idioma.pt" /></a>
		  		   		<a class="btn info" href="<c:url value="/idioma/?idioma=en_US" />"><fmt:message key="misc.idioma.en" /></a>
		  		   	</p>		  			
		  		</article>
		  		<article>
		  			<p><strong><fmt:message key="main.painel.aviso" />:</strong></p>
					<p><fmt:message key="main.painel.msg1" /></p>
					<p><fmt:message key="main.painel.msg2" /></p>
					<p><fmt:message key="main.painel.msg3" /></p>
					<%@ include file="../templates/browsers.jsp"%>
					<p><fmt:message key="main.painel.msg4" />&nbsp;<a href="https://github.com/caio-ribeiro-pereira/SimulaRSO/issues" target="_blank"><fmt:message key="main.painel.report.bug" /></a></p>
			  	</article>
		  		<article>
			  		<p><strong><fmt:message key="main.sobre.projeto" />:</strong></p>
			  		<p><fmt:message key="main.sobre.msg1" /></p>
					<p><fmt:message key="main.sobre.msg2" /></p>
					<p><fmt:message key="main.sobre.msg3" />&nbsp;<a href="http://www.unisantos.br" target="_blank">Universidade Católica de Santos.</a></p>
			  	</article>
		  		<article>
			  		<p><strong><fmt:message key="main.atualizacoes" />:</strong></p>
			  		<p><fmt:message key="main.atualizacoes.msg4" /></p>
			  		<p><fmt:message key="main.atualizacoes.msg3" /></p>
			  		<p><fmt:message key="main.atualizacoes.msg2" /></p>
			  		<p><fmt:message key="main.atualizacoes.msg1" /></p>
			  	</article>
			</section>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>