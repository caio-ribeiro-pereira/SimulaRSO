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
		<div class="container_12 main">
			<%@ include file="../templates/header.jsp"%>
		  	<article class="clearfix">
		  		<section class="clearfix main-info">
		  			<div id="idioma-box">
		  				<form action="<c:url value="/idioma" />" method="post" id="idioma-form">
		  					<input type="hidden" name="idioma" id="idioma-val">
		  				</form>
						<p>
							<button type="button" value="pt_BR">Português</button>
		  			   		<button type="button" value="en_US">English</button>
		  			   	</p>		  			
		  			</div>
		  			<%-- <p><a href="<c:url value="/idioma/pt_BR" />">Português</a> - <a href="<c:url value="/idioma/en_US" />">English</a></p> --%>
					<p><strong><fmt:message key="main.painel.aviso" />:</strong></p>
					<p><strong class="clearfix info-message"><fmt:message key="main.painel.msg1" /></strong></p>
					<p><strong class="clearfix info-message"><fmt:message key="main.painel.msg2" /></strong></p>
					<p><strong class="clearfix info-message"><fmt:message key="main.painel.msg3" /></strong></p>
					<%@ include file="../templates/browsers.jsp"%>
					<p><strong class="clearfix info-message"><fmt:message key="main.painel.msg4" /></strong></p>
			  	</section>
		  		<section class="clearfix main-info">
			  		<p><strong><fmt:message key="main.sobre.projeto" />:</strong></p>
			  		<p class="text"><fmt:message key="main.sobre.msg1" /></p>
					<p class="text"><fmt:message key="main.sobre.msg2" /></p>
					<p class="text"><fmt:message key="main.sobre.msg3" /></p>
			  	</section>
		  		<section class="clearfix main-info">
				  		<p><strong><fmt:message key="main.atualizacoes" />:</strong></p>
				  		<p><fmt:message key="main.atualizacoes.msg4" /></p>
				  		<p><fmt:message key="main.atualizacoes.msg3" /></p>
				  		<p><fmt:message key="main.atualizacoes.msg2" /></p>
				  		<p><fmt:message key="main.atualizacoes.msg1" /></p>
			  	</section>
			</article>
			<%@ include file="../templates/footer.jsp"%>
		</div>
		<script type="text/javascript">
			head.ready(function(){
				$('button').button().click(function(){
					$('#idioma-val').val($(this).val());
					$('#idioma-form').submit();
				});
			});
		</script>
	</body>
</html>
</fmt:bundle>