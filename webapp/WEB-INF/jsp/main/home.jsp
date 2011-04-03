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
					<p><strong class="clearfix error-message">Atenção estamos ainda em fase de testes e desenvolvimento!</strong></p>
					<p><strong class="clearfix error-message">Este projeto atualmente não funciona em navegadores Internet Explorer 7 e 8.</strong></p>
					<p><strong class="clearfix error-message">Por esses motivos, recomendamos utilizar browsers alternativos que dão suporte nativo ao HTML 5:</strong></p>
					<%@ include file="../templates/browsers.jsp"%>
					<p><strong class="clearfix error-message">Qualquer notificação de bugs neste aplicativo, ficaremos grato com a sua colaboração reportando o problema <a href="https://github.com/caio-ribeiro-pereira/SimulaRSO/issues" target="_blank">clicando aqui.</a></strong></p>  		
			  	</section>	  	
			</article>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>