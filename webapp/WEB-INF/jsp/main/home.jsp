<!DOCTYPE html>
<fmt:setLocale value="pt-BR" />
<html lang="pt-BR" >
	<head>
		<title>Bem-vindo ao Projeto SimulaRSO</title>
		<meta name="keywords" content="sistema operacional, algoritmos, escalonamento, processos, disco, paginação de memória" />
		<meta name="keywords" content="sistema de ensino a distância, EAD, sistemas operacionais" />
		<%@include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<div class="container_12 main">
			<%@ include file="../templates/header.jsp"%>
		  	<article class="clearfix">
		  		<section class="clearfix main-info">
			  		<p><strong>Sobre o projeto:</strong></p>
			  		<p class="text">É um aplicativo que simula o comportamento dos principais recursos que são implementados em um sistema operacional moderno.
					   Nesta primeira versão foi implementado os simuladores dos principais algoritmos de escalonamento de processos, disco e 
					   paginação de memória, em ambos são componentes essenciais que trabalham interligados para gerenciar toda demanda de
					   tarefas solicitadas pelo usuário.
					</p>
					<p class="text">O objetivo principal desse projeto é disponibilizar um aplicativo web que se torne uma ferramenta 
					   de ensino, para servir de base nos estudos da disciplina de sistema operacionais, fazendo
					   com que o projeto seja facilmente integrado a qualquer sistema de ensino a distância (EAD).
					</p>
					<p class="text">Este projeto será o nosso trabalho de conclusão de curso para 2011. 
						e será contribuído para a <a href="http://www.unisantos.br" target="_blank">Universidade Católica de Santos.</a>
					</p>
			  	</section>
		  		<section class="clearfix main-info">
				  		<p><strong>Últimas atualizações:</strong></p>
				  		<p><strong>13/04/2011:</strong> Implementado animação nos gráficos de simulação: <a href="<c:url value="/escalonamento-processo" />">Escalonamento de Processos.</a> e <a href="<c:url value="/paginacao-memoria" />">Paginação de Memória.</a></p>
				  		<p><strong>07/04/2011:</strong> Lançamento da primeira versão de teste do módulo: <a href="<c:url value="/paginacao-memoria" />">Paginação de Memória.</a></p>
				  		<p><strong>15/03/2011:</strong> Lançamento para testes do módulo de <a href="<c:url value="/escalonamento-processo" />">Escalonamento de Processos.</a></p>
			  	</section>
				<section class="clearfix main-info">
					<p><strong>Painel de aviso:</strong></p>
					<p><strong class="clearfix error-message">Atenção estamos ainda em fase de testes e desenvolvimento!</strong></p>
					<p><strong class="clearfix error-message">Este projeto atualmente não funciona corretamente nos navegadores Internet Explorer 7 e 8.</strong></p>
					<p><strong class="clearfix error-message">Por esses motivos, recomendamos utilizar browsers alternativos que dão melhor suporte a tecnologia HTML 5:</strong></p>
					<%@ include file="../templates/browsers.jsp"%>
					<p><strong class="clearfix error-message">ou</strong></p>
					<p><strong class="clearfix error-message">Instalando o plugin <a href="http://code.google.com/chrome/chromeframe/" target="_blank">Google Chrome Frame</a> que importa as principais funcionalidades do Google Chrome para o navegador Internet Explorer versões: 6, 7 e 8.</strong></p>
					<p><strong class="clearfix error-message">Qualquer notificação de bugs, ficaremos grato com a sua colaboração reportando o problema <a href="https://github.com/caio-ribeiro-pereira/SimulaRSO/issues" target="_blank">clicando aqui.</a></strong></p>  		
			  	</section>
			</article>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>