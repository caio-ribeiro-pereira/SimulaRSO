<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="pt-BR"/>
<html lang="pt-BR">
  <head>
    <title><decorator:title default="Projeto SimulaEDP"></decorator:title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/grid.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.5.min.js" />"></script>
    <!--[if lt IE 9]><script type="text/javascript" src="<c:url value="/resources/js/html5.js" />"></script><![endif]-->
    <decorator:head />
  </head>
  <body>
  	<div class="container_12 main">
	  	<header class="clearfix">
	  		<h1>SimulaEDP - Simulador de Escalonamento de Disco e Processos</h1>
	  		<nav>
	  			<a href="<c:url value="/" />">Home</a>
				<a href="<c:url value="/escalonamento-processo" />">Escalonamento de Processos</a>
				<a href="<c:url value="/escalonamento-disco" />">Escalonamento de Disco</a>
				<a href="<c:url value="/sobre" />">Sobre</a>
			</nav>
	  	</header>
	  	<section class="clearfix">
	  		<c:if test="${not empty errors}">
				<c:forEach var="error" items="${errors}">
				    <h2>${error.message}</h2>
				</c:forEach>
			</c:if>
			<c:if test="${not empty message}">
				<h2>${message}</h2>
			</c:if>
			<decorator:body />
	  	</section>
		<footer class="clearfix">
            <h3>Criadores do projeto:</h3>
	  		<p><strong>Orientador:</strong> André Luiz Vizine Pereira - <a href="mailto:vizine@unisantos.br">vizine@unisantos.br</a></p>
	  		<p><strong>Autor:</strong> André de Araújo Rodrigues - <a href="mailto:and.arodrigues@gmail.com">and.arodrigues@gmail.com</a></p>
	  		<p><strong>Autor:</strong> Caio Ribeiro Pereira - <a href="mailto:caio.ribeiro.pereira@gmail.com">caio.ribeiro.pereira@gmail.com</a></p>
	  		<p><strong>Curso:</strong> Sistemas de Informação</p>
	  		<p><strong>Universidade Católica de Santos</strong></p>
	  	</footer>
  	</div>
  </body>
</html>