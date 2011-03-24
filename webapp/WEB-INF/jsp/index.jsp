<!DOCTYPE html>
<fmt:setLocale value="pt-BR"/>
<html lang="pt-BR">
  <head>
    <title><decorator:title default="Projeto SimulaRSO"></decorator:title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />"></link>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/grid.min.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/smoothness/jquery-ui.css" />">
	<script type="text/javascript" src="<c:url value="/resources/js/head.min.js" />"></script>
	<!--[if IE]><script type="text/javascript" src="<c:url value="/resources/js/ie/html5.js" />"></script><![endif]-->
	<script type="text/javascript">
		var _gaq = _gaq || [];
		  _gaq.push(['_setAccount', 'UA-7076509-2']);
		  _gaq.push(['_trackPageview']);
		  
  		head.js('<c:url value="/resources/js/jquery/jquery.min.js" />',
  				'<c:url value="/resources/js/jquery/jquery-ui.min.js" />',
  				'<c:url value="/resources/js/jquery/jquery-ui.spinner.min.js" />',
  				'<c:url value="/resources/js/jquery/jquery-numeric.js" />',
  				'<c:url value="/resources/js/jquery/jquery-tmpl.min.js" />',
  				'<c:url value="/resources/js/canvas/colors.js" />',
  				'<c:url value="/resources/js/canvas/processo-chart.js" />');
  		
  		head.ready(
  			function() {
  			    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
  			    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
  			    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  			}		
  		);
  		
  	</script>
    <decorator:head />
  </head>
  <body>
  	<div class="container_12 main">
	  	<header class="clearfix">
	  		<h1>Projeto SimulaRSO</h1>
	  		<nav>
	  			<a class="left" href="<c:url value="/" />">Home</a>
				<a href="<c:url value="/escalonamento-processo" />">Processos</a>
				<a href="<c:url value="/escalonamento-disco" />">Disco</a>
				<a href="<c:url value="/paginacao-memoria-virtual" />">Memória virtual</a>
				<a class="right" href="<c:url value="/sobre" />">Sobre</a>
			</nav>
	  	</header>
	  	<hr>
	  	<section class="clearfix">
	  		<c:if test="${not empty errors}">
				<c:forEach var="error" items="${errors}">
				    <span class="clearfix ui-state-error ui-corner-all error-message">${error.message}</span>
				</c:forEach>
			</c:if>
			<decorator:body />
	  	</section>
	  	<hr>
		<footer class="clearfix">
				<h3>Autores do projeto:</h3>
		  		<p><strong>Orientador:</strong> André Luiz Vizine Pereira - <a href="mailto:vizine@unisantos.br">vizine@unisantos.br</a></p>
		  		<p><strong>Aluno:</strong> André de Araújo Rodrigues - <a href="mailto:and.arodrigues@gmail.com">and.arodrigues@gmail.com</a></p>
		  		<p><strong>Aluno:</strong> Caio Ribeiro Pereira - <a href="mailto:caio.ribeiro.pereira@gmail.com">caio.ribeiro.pereira@gmail.com</a></p>
		  		<p><strong>Curso:</strong> Sistemas de Informação</p>
		  		<p><strong><a href="http://www.unisantos.br" target="_blank">Universidade Católica de Santos</a></strong></p>
		  		<hr>
				<p>
					<a href="https://appengine.google.com/" target="_blank"><img src="http://code.google.com/appengine/images/appengine-silver-120x30.gif" alt="Powered by Google App Engine" /></a>
				</p>
	  	</footer>
  	</div>
  </body>
</html>