<!DOCTYPE html>
<fmt:setLocale value="pt-BR"/>
<html lang="pt-BR">
  <head>
    <title><decorator:title default="Projeto SimulaRSO"></decorator:title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="author" content="Unisantos - Universidade Católica de Santos" />
    <meta name="author" content="André de Araújo Rodrigues" />
    <meta name="author" content="Caio Ribeiro Pereira" />
    <meta name="description" content="SimulaRSO - Simulador de Recursos de Sistemas Operacionais" />
    <meta name="keywords" content="Projeto SimulaRSO, Simulador de Recursos de Sistemas Operacionais, Simulador, Sistemas Operacionais" />
    <meta name="keywords" content="Unisantos, Universidade Católica de Santos" />
	<meta name="msvalidate.01" content="8E5B78FB688E236FBD4D6659F8089AD6" />
	<meta name="y_key" content="3406b834d5e1d184" />
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/favicon.ico" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset-grid.min.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dark-hive/jquery-ui.css" />">
	<script type="text/javascript" src="<c:url value="/resources/js/head.load.min.js" />"></script>
	<!--[if IE]><script type="text/javascript" src="<c:url value="/resources/js/ie/html5.min.js" />"></script><![endif]-->
	<!--[if IE]><script type="text/javascript" src="<c:url value="/resources/js/ie/excanvas.min.js" />"></script><![endif]-->
	<script type="text/javascript">
		var _gaq = _gaq || [];
		  _gaq.push(['_setAccount', 'UA-7076509-2']);
		  _gaq.push(['_trackPageview']);
  		head.js('<c:url value="/resources/js/jquery/jquery-pack-plugins.min.js" />',
  				'<c:url value="/resources/js/canvas/colors.js" />',
  				'<c:url value="/resources/js/canvas/processo-chart.js" />',
  				'<c:url value="/resources/js/google-analytics.js" />'
  		);
  		head.ready(function(){
			$('nav a.left').button({icons: {primary: 'ui-icon-home'}});
			$('nav a.right').button({icons: {primary: 'ui-icon-comment'}});
			$('nav a.middle').button({icons: {primary: 'ui-icon-triangle-1-e'}});
		});
  	</script>
    <decorator:head />
  </head>
  <body>
  	<div class="container_12 main">
	  	<header class="clearfix">
	  		<h1 class="clearfix">Projeto SimulaRSO</h1>
	  		<nav class="clearfix ">
	  			<a class="left" href="<c:url value="/home" />">Home</a>
				<a class="middle" href="<c:url value="/escalonamento-processo" />">Escalonamento de processos</a>
				<a class="middle" href="<c:url value="/escalonamento-disco" />">Escalonamento de disco</a>
				<a class="middle" href="<c:url value="/paginacao-memoria" />">Paginação de memória</a>
				<a class="right" href="<c:url value="/sobre" />">Sobre</a>
			</nav>
	  	</header>
	  	<hr>
	  	<article class="clearfix">
			<section class="clearfix">
		  		<c:if test="${not empty errors}">
					<c:forEach var="error" items="${errors}">
					    <strong class="clearfix error-message">${error.message}</strong>
					</c:forEach>
				</c:if>
				<decorator:body />
		  	</section>	  	
	  	</article>
	  	<hr>
		<footer class="clearfix">
				<p><a href="http://www.unisantos.br" target="_blank"><strong>Universidade Católica de Santos</strong></a></p>
		  		<p><strong>Orientador:</strong> André Luiz Vizine Pereira - <a href="mailto:vizine@unisantos.br">vizine@unisantos.br</a></p>
		  		<p><strong>Aluno:</strong> André de Araújo Rodrigues - <a href="mailto:and.arodrigues@gmail.com">and.arodrigues@gmail.com</a></p>
		  		<p><strong>Aluno:</strong> Caio Ribeiro Pereira - <a href="mailto:caio.ribeiro.pereira@gmail.com">caio.ribeiro.pereira@gmail.com</a></p>
		  		<p><strong>Curso:</strong> Sistemas de Informação - 2011</p>
				<ul class="clearfix images" id="tecnologias">
			  		<li><a class="java" title="Tecnologia Java" href="http://www.oracle.com/br/technologies/java/index.html" target="_blank"></a></li>
			  		<li><a class="html5" title="Tecnologia HTML5" href="http://www.w3schools.com/html5/default.asp" target="_blank"></a></li>
			  		<li><a class="vraptor" title="Tecnologia VRaptor" href="http://vraptor.caelum.com.br" target="_blank"></a></li>
			 		<li><a class="gae" title="Cloud Host Google App Engine" href="http://appengine.google.com/" target="_blank"></a></li>
			 		<li><a class="github" title="Repositório GitHub Social Coding" href="http://github.com/" target="_blank"></a></li>
			 	</ul>		  		
	  	</footer>
  	</div>
  </body>
</html>