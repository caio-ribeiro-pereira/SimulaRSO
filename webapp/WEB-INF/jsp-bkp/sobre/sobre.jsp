<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sobreController.idioma}" />
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
					<p style="text-align:center;"><a href="https://github.com/caio-ribeiro-pereira/SimulaRSO" target="_blank"><strong><fmt:message key="sobre.codigo.fonte" /></strong></a></p>
					<hr>
					<h3 class="clearfix subtitle"><fmt:message key="sobre.funcionalidade.titulo" /></h3>
					<ul>
						<li><span class="feature ui-icon ui-icon-check"></span><small><fmt:message key="sobre.funcionalidade.msg1" /></small></li>
						<li><span class="feature ui-icon ui-icon-check"></span><small><fmt:message key="sobre.funcionalidade.msg2" /></small></li>
						<li><span class="feature ui-icon ui-icon-check"></span><small><fmt:message key="sobre.funcionalidade.msg3" /></small></li>
						<li><span class="feature ui-icon ui-icon-check"></span><small><fmt:message key="sobre.funcionalidade.msg4" /></small></li>
						<li><span class="feature ui-icon ui-icon-check"></span><small><fmt:message key="sobre.funcionalidade.msg5" /></small></li>
						<li><span class="feature ui-icon ui-icon-check"></span><small><fmt:message key="sobre.funcionalidade.msg6" /></small></li>
					</ul>
					<hr>
					<h3 class="clearfix subtitle"><fmt:message key="sobre.tecnologias.titulo" /></h3>
					<%@include file="../templates/tecnologias.jsp"%>
					<ul>
						<li><strong><fmt:message key="sobre.tecnologias.infra" />:</strong></li>
						<li><small style="margin-left: 30px;"><a href="http://code.google.com/appengine/" target="_blank"><strong>Google App Engine</strong></a> - <fmt:message key="sobre.tecnologias.gae" /></small></li>
						<li><small style="margin-left: 30px;"><a href="http://www.github.com" target="_blank"><strong>GitHub</strong></a> - <fmt:message key="sobre.tecnologias.github" /></small></li>
						<li><strong><fmt:message key="sobre.tecnologias.server" />:</strong>
						<li><small style="margin-left: 30px;"><a href="http://www.oracle.com/br/technologies/java/index.html" target="_blank"><strong>Java 6</strong></a> - <fmt:message key="sobre.tecnologias.java" /></small></li>
						<li><small style="margin-left: 30px;"><a href="http://www.junit.org" target="_blank"><strong>JUnit 4.8</strong></a> - <fmt:message key="sobre.tecnologias.junit" /></small></li>
						<li><small style="margin-left: 30px;"><a href="http://vraptor.caelum.com.br" target="_blank"><strong>VRaptor 3.3.1</strong></a> - <fmt:message key="sobre.tecnologias.vraptor" /><a href="http://www.caelum.com.br" target="_blank">&nbsp;<fmt:message key="sobre.tecnologias.caelum"/></a></small></li>
						<li><small style="margin-left: 30px;"><a href="http://jstl.java.net" target="_blank"><strong>JSTL 1.2</strong></a> - <fmt:message key="sobre.tecnologias.jstl" /></small></li>
						<li><strong><fmt:message key="sobre.tecnologias.cliente" />:</strong></li>
						<li><small style="margin-left: 30px;"><a href="http://www.w3schools.com/html5/default.asp" target="_blank"><strong>HTML 5</strong></a> - <fmt:message key="sobre.tecnologias.html5" />&nbsp;<a href="http://www.w3schools.com" target="_blank"><fmt:message key="sobre.tecnologias.w3c"/></a></small></li>
						<li><small style="margin-left: 30px;"><a href="http://www.w3schools.com/css3/default.asp" target="_blank"><strong>CSS 3</strong></a> - <fmt:message key="sobre.tecnologias.css3" />&nbsp;<a href="http://www.w3schools.com" target="_blank"><fmt:message key="sobre.tecnologias.w3c"/></a></small></li>
						<li><small style="margin-left: 30px;"><a href="http://developer.mozilla.org/en/Canvas_tutorial" target="_blank"><strong>Canvas</strong></a> - <fmt:message key="sobre.tecnologias.canvas" /></small></li>
						<li><small style="margin-left: 30px;"><a href="http://jquery.com/" target="_blank"><strong>JQuery 1.6</strong></a> - <fmt:message key="sobre.tecnologias.jquery" /></small></li>
						<li><small style="margin-left: 30px;"><a href="http://jqueryui.com/" target="_blank"><strong>JQuery-UI 1.8.11</strong></a> - <fmt:message key="sobre.tecnologias.jqueryui" /></small></li>
						<li><small style="margin-left: 30px;"><a href="http://headjs.com/" target="_blank"><strong>Head JS 0.9</strong></a> - <fmt:message key="sobre.tecnologias.headjs" /></small>
						<li><small style="margin-left: 30px;"><a href="http://960.gs/" target="_blank"><strong>960 Grid System</strong></a> - <fmt:message key="sobre.tecnologias.960gs" /></small>
					</ul>
					<hr>
					<h3 class="clearfix subtitle"><fmt:message key="sobre.browsers.titulo" /></h3>
					<%@include file="../templates/browsers.jsp"%>
				</section>
			</article>
			<%@include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>