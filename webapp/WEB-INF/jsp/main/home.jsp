<!DOCTYPE html>
<fmt:setLocale value="pt-BR" />
<html lang="pt-BR" >
	<head>
		<title>Bem-vindo ao Projeto SimulaRSO</title>
		<%@include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<div class="container_12 main">
			<%@ include file="../templates/header.jsp"%>
		  	<article class="clearfix">
				<section class="clearfix">
					<p><strong>Painel de aviso:</strong></p>
					<p><strong class="clearfix error-message">Atenção estamos ainda em fase de testes e desenvolvimento!</strong></p>
					<p><strong class="clearfix error-message">Este projeto atualmente não funciona corretamente nos navegadores Internet Explorer 7 e 8.</strong></p>
					<p><strong class="clearfix error-message">Por esses motivos, recomendamos utilizar browsers alternativos que dão melhor suporte a tecnologia HTML 5:</strong></p>
					<%@ include file="../templates/browsers.jsp"%>
					<p><strong class="clearfix error-message">Qualquer notificação de bugs neste aplicativo, ficaremos grato com a sua colaboração reportando o problema <a href="https://github.com/caio-ribeiro-pereira/SimulaRSO/issues" target="_blank">clicando aqui.</a></strong></p>  		
			  	</section>
			  	<section>
			  		<p><strong>Últimas atualizações:</strong></p>
			  		<p><strong>07/04/2011:</strong> Lançamento da primeira versão de teste do módulo: <a href="<c:url value="/paginacao-memoria" />">Paginação de Memória.</a></p>
			  		<p><strong>15/03/2011:</strong> Lançamento para testes do módulo de <a href="<c:url value="/escalonamento-processo" />">Escalonamento de Processos.</a></p>
			  	</section>	
			</article>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>