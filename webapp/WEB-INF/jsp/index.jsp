<!DOCTYPE html>
<fmt:setLocale value="pt-BR"/>
<html lang="pt-BR">
  <head>
    <title><decorator:title default="Projeto SimulaRSO"></decorator:title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="author" content="Unisantos - Universidade Católica de Santos">
    <meta name="author" content="André de Araújo Rodrigues">
    <meta name="author" content="Caio Ribeiro Pereira">
    <meta name="description" content="SimulaRSO - Simulador de Recursos de Sistemas Operacionais">
    <meta name="keywords" content="simularso, simula-rso, simulador, recursos, sistemas, operacionais, sistema, operacional">
    <meta name="keywords" content="simulação, comparativa, única, disciplina, aplicação, web">
    <meta name="keywords" content="escalonamento, escalonador, escalonar, algoritmo, algoritmos, resultado">
	<meta name="keywords" content="processos, processo, FCFS, SJF, SRT, Round Robin">
	<meta name="keywords" content="tempo, burst, cpu, chegada, prioridade, espera, resposta, turn around">
	<meta name="keywords" content="disco, discos, (I/O), FCFS, SSTF, SCAN, C-SCAN, C-LOOK">
	<meta name="keywords" content="paginação, memória virtual, FIFO, LRU, Ótimo">
	<meta name="keywords" content="string, referência, page faults, falta, página, substituição, páginas, paginação">
	<meta name="keywords" content="unisantos, universidade, tcc, católica, santos">
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
	  			<a class="left" href="<c:url value="/" />">Home</a>
				<a class="middle" href="<c:url value="/escalonamento-processo" />">Processos</a>
				<a class="middle" href="<c:url value="/escalonamento-disco" />">Disco</a>
				<a class="middle" href="<c:url value="/paginacao-memoria-virtual" />">Memória virtual</a>
				<a class="right" href="<c:url value="/sobre" />">Sobre</a>
			</nav>
	  	</header>
	  	<hr>
	  	<section class="clearfix">
	  		<c:if test="${not empty errors}">
				<c:forEach var="error" items="${errors}">
				    <strong class="clearfix error-message">${error.message}</strong>
				</c:forEach>
			</c:if>
			<decorator:body />
	  	</section>
	  	<hr>
		<footer class="clearfix">
				<p><a href="http://www.unisantos.br" target="_blank"><strong>Universidade Católica de Santos</strong></a></p>
		  		<p><strong>Orientador:</strong> André Luiz Vizine Pereira - <a href="mailto:vizine@unisantos.br">vizine@unisantos.br</a></p>
		  		<p><strong>Aluno:</strong> André de Araújo Rodrigues - <a href="mailto:and.arodrigues@gmail.com">and.arodrigues@gmail.com</a></p>
		  		<p><strong>Aluno:</strong> Caio Ribeiro Pereira - <a href="mailto:caio.ribeiro.pereira@gmail.com">caio.ribeiro.pereira@gmail.com</a></p>
		  		<p><strong>Curso:</strong> Sistemas de Informação - 2011</p>
		  		<div class="clearfix logos">
					<a href="http://www.oracle.com/br/technologies/java/index.html" target="_blank"><img src="<c:url value="/resources/img/icon_java.gif" />" alt="Tecnologia Java" width="30" height="30" /></a>
					<a href="http://www.w3schools.com/html5/default.asp" target="_blank"><img src="<c:url value="/resources/img/icon_html5.gif" />" alt="Tecnologia HTML5" width="32" height="32" style="margin-top: 10px;" /></a>
					<a href="http://jquery.com/" target="_blank"><img src="<c:url value="/resources/img/icon_jquery.gif" />" alt="Tecnologia JQuery"  width="64" height="29"  style="margin-top: -1px;"/></a>
					<a href="http://vraptor.caelum.com.br" target="_blank"><img src="<c:url value="/resources/img/icon_vraptor.gif" />" alt="Tecnologia VRaptor da Caelum"  width="90" height="30"  /></a>
					<a href="http://appengine.google.com/" target="_blank"><img src="<c:url value="/resources/img/icon_appengine.gif" />" alt="Hospedado no Google App Engine"  width="120" height="28"  style="margin-top: -2px;"/></a>
					<a href="http://github.com/" target="_blank"><img src="<c:url value="/resources/img/icon_github.gif" />" alt="Repositório do código-fonte do projeto via GitHub"  width="48" height="24"  style="margin-top: -6px;"/></a>
				</div>
	  	</footer>
  	</div>
  </body>
</html>