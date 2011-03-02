<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
	<title>Sobre o projeto SimulaEDP</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<strong>SimulaEDP</strong> - <strong>Simula</strong>dor de <strong>E</strong>scalonamento de <strong>D</strong>isco e <strong>P</strong>rocessos
	<p><small>O objetivo deste trabalho é o desenvolvimento de uma aplicação web
	   a ser utilizado como ferramenta de apoio para a disciplina de 
	   sistemas operacionais ou semelhantes que abordem os assuntos de 
	   Escalonamento de Disco e Escalonamento de Processos.
	   O foco principal do projeto é simular graficamente e de forma intuitiva
	   como funcionam os principais algoritmos de escalonadores de processos 
	   <strong>(FCFS, SJF, SRT, Prioridade, Round Robin)</strong> que são utilizados no gerenciamento
	   de processos concorrentes presentes e também simular o comportamento
	   dos algoritmos que escalonam as requisições de entrada e saída <strong>(I/O)</strong> de um 
	   disco rígido <strong>(FCFS, SSTF, SCAN, C-SCAN, C-LOOK).</strong></small></p>
	<hr>
	<strong>Funcionalidades do projeto:</strong>
	<ul>
		<li><small>Simular os principais algoritmos de escalonamento de processos com até 10 processos.</small></li>
		<li><small>Simular os principais algoritmos de escalonamento de disco com até 30 requisições de (I/O) em disco.</small></li>
		<li><small>Realizar simulação comparativa para analisar o comportamento de dois algoritmos distintos.</small></li>
		<li><small>Exibição comportamental dos algoritmos através de gráficos intuitivos.</small></li>
		<li><small>Projeto internacionalizado com suporte aos idiomas inglês e português.</small></li> 
	</ul>
	<hr>
	<strong>Tecnologias utilizadas:</strong>
	<ul>
		<li><small><a href="http://www.oracle.com/br/technologies/java/index.html" target="_blank"><strong>Java 6</strong></a> - Linguagem principal do projeto.</small></li>
		<li><small><a href="http://www.junit.org" target="_blank"><strong>JUnit 4.8</strong></a> - Framework para realizar testes unitário nos algoritmos.</small></li>
		<li><small><a href="http://www.vraptor.caelum.com.br" target="_blank"><strong>VRaptor 3.2.0</strong></a> - Framework MVC Brasileiro desenvolvido pela equipe da <a href="http://www.caelum.com.br">Caelum</a>.</small></li>
		<li><small><a href="http://jstl.java.net" target="_blank"><strong>JSTL 1.2</strong></a> - Tags Java para incorporar funcionalidades em um jsp.</small></li>
		<li><small><a href="http://www.opensymphony.com/sitemesh/" target="_blank"><strong>Sitemesh 2.4.2</strong></a> - Framework para modelar templates web.</small></li>
		<li><small><a href="http://jquery.com/"><strong>JQuery 1.5</strong></a> - Biblioteca Javascript Cross-browser para manipulação de elementos DOM HTML.</small></li>
		<li><small><a href="http://www.w3schools.com/html5/default.asp"><strong>HTML 5</strong></a> - Estrutura do projeto utilizando as boas práticas de HTML 5 de acordo com as normas <a href="http://www.w3schools.com" target="_blank">W3C</a>.</small></li>
		<li><small><a href="http://www.w3schools.com/css3/default.asp"><strong>CSS 3</strong></a> - Para estilização do layout do projeto, também seguindo as normas <a href="http://www.w3schools.com" target="_blank">W3C</a>.</small></li>
		<li><small><a href="https://developer.mozilla.org/en/Canvas_tutorial"><strong>Canvas</strong></a> - Elemento principal do projeto, que permite renderizar elementos gráficos em 2D.</small></li>
	</ul>
</body>