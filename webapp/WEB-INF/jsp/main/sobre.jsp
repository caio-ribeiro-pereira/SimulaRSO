<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${mainController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="sobre.titulo" /></title>
		<meta name="keywords" content="FCFS, SJR, SRT, Round Robin, SSTF, SCAN, C-SCAN, C-LOOK, FIFO, LRU, OPT, MRU" />
		<%@include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<div class="container_12 main">
			<%@include file="../templates/header.jsp"%>
			<article class="clearfix">
				<section class="clearfix">
					<h3 class="clearfix subtitle"><fmt:message key="sobre.titulo" /></h3>
					<p class="text"><fmt:message key="sobre.msg" /></p>
					<p class="info-message"><a href="https://github.com/caio-ribeiro-pereira/SimulaRSO" target="_blank"><strong>Clique aqui para visualizar o código-fonte do projeto.</strong></a></p>
					<hr>
					<h3 class="clearfix subtitle">Funcionalidades do projeto</h3>
					<ul>
						<li><small class="checked" title="Funcionalidade liberada.">Simular os principais algoritmos de escalonamento de processos com até 20 processos.</small></li>
						<li><small class="checked" title="Funcionalidade em desenvolvimento.">Simular os principais algoritmos de escalonamento de disco com até 30 requisições de (I/O) em disco.</small></li>
						<li><small class="checked" title="Funcionalidade liberada.">Simular os principais algoritmos de substituição de página de memória virtual com até 30 palavras de bytes na escrita.</small></li>
						<li><small class="checked" title="Funcionalidade liberada.">Realizar simulação comparativa para analisar o comportamento de dois algoritmos distintos.</small></li>
						<li><small class="checked" title="Funcionalidade liberada.">Exibição comportamental dos algoritmos através de gráficos intuitivos.</small></li>
						<li><small title="Funcionalidade em desenvolvimento.">Projeto internacionalizado com suporte aos idiomas inglês e português.</small></li> 
					</ul>
					<hr>
					<h3 class="clearfix subtitle">Tecnologias utilizadas</h3>
					<%@include file="../templates/tecnologias.jsp"%>
					<ul>
						<li><strong>Infra-estrutura:</strong></li>
						<li><small style="margin-left: 30px;"><a href="http://code.google.com/appengine/" target="_blank"><strong>Google App Engine</strong></a> - Serviço de hospedagem em Cloud Computing para aplicações Java e Python.</small></li>
						<li><small style="margin-left: 30px;"><a href="http://www.github.com" target="_blank"><strong>GitHub</strong></a> - Repositório para projetos open-source.</small></li>
						<li><strong>Server-side:</strong>
						<li><small style="margin-left: 30px;"><a href="http://www.oracle.com/br/technologies/java/index.html" target="_blank"><strong>Java 6</strong></a> - Linguagem principal do projeto.</small></li>
						<li><small style="margin-left: 30px;"><a href="http://www.junit.org" target="_blank"><strong>JUnit 4.8</strong></a> - Framework para realizar testes unitário nos algoritmos.</small></li>
						<li><small style="margin-left: 30px;"><a href="http://vraptor.caelum.com.br" target="_blank"><strong>VRaptor 3.3.1</strong></a> - Framework MVC Brasileiro desenvolvido pela equipe da <a href="http://www.caelum.com.br">Caelum</a>.</small></li>
						<li><small style="margin-left: 30px;"><a href="http://jstl.java.net" target="_blank"><strong>JSTL 1.2</strong></a> - Tags Java para incorporar funcionalidades em uma página JSP.</small></li>
						<li><strong>Client-side:</strong></li>
						<li><small style="margin-left: 30px;"><a href="http://www.w3schools.com/html5/default.asp" target="_blank"><strong>HTML 5</strong></a> - Estrutura do projeto utilizando as boas práticas de HTML 5 de acordo com as normas <a href="http://www.w3schools.com" target="_blank">W3C</a>.</small></li>
						<li><small style="margin-left: 30px;"><a href="http://www.w3schools.com/css3/default.asp" target="_blank"><strong>CSS 3</strong></a> - Para estilização do layout do projeto, também seguindo as normas <a href="http://www.w3schools.com" target="_blank">W3C</a>.</small></li>
						<li><small style="margin-left: 30px;"><a href="http://developer.mozilla.org/en/Canvas_tutorial" target="_blank"><strong>Canvas</strong></a> - Elemento principal do projeto, que permite renderizar elementos gráficos em 2D.</small></li>
						<li><small style="margin-left: 30px;"><a href="http://jquery.com/" target="_blank"><strong>JQuery 1.5.1</strong></a> - Biblioteca Javascript Cross-browser para manipulação de elementos DOM HTML.</small></li>
						<li><small style="margin-left: 30px;"><a href="http://jqueryui.com/" target="_blank"><strong>JQuery-UI 1.8.11</strong></a> - Conjunto de interfaces gráficas prontas e totalmente compatível com JQuery.</small></li>
						<li><small style="margin-left: 30px;"><a href="http://www.jgeppert.com/jquery-spinner/" target="_blank"><strong>JQuery-UI Spinner Plugin 1.24</strong></a> - Plugin JQuery para implementar um spinner control.</small></li>
						<li><small style="margin-left: 30px;"><a href="http://headjs.com/" target="_blank"><strong>Head JS 0.9</strong></a> - Script para carregamento rápido de arquivos javascript.</small>
						<li><small style="margin-left: 30px;"><a href="http://960.gs/" target="_blank"><strong>960 Grid System</strong></a> - Framework CSS para posicionamento de objetos DOM Cross-Browser em formato de grid.</small>
					</ul>
					<hr>
					<h3 class="clearfix subtitle">Compatibilidade com os navegadores</h3>
					<%@include file="../templates/browsers.jsp"%>
				</section>
			</article>
			<%@include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>