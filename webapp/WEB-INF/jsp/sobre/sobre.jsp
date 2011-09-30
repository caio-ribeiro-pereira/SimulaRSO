<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sobreController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="sobre.titulo" /></title>
		<%@include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<%@ include file="../templates/menu.jsp"%>
		<div class="container">
			<%@include file="../templates/header.jsp"%>
			<hr>
			<section>
				<article>
					<h3><fmt:message key="sobre.titulo" /></h3>
					<p class="justify"><fmt:message key="sobre.msg" /></p>
					<p class="center"><a href="https://github.com/caio-ribeiro-pereira/SimulaRSO" target="_blank"><strong><fmt:message key="sobre.codigo.fonte" /></strong></a></p>
				</article>
				<hr>
				<article>
					<h3><fmt:message key="sobre.funcionalidade.titulo" /></h3>
					<ul>
						<li><fmt:message key="sobre.funcionalidade.msg1" /></li>
						<li><fmt:message key="sobre.funcionalidade.msg2" /></li>
						<li><fmt:message key="sobre.funcionalidade.msg3" /></li>
						<li><fmt:message key="sobre.funcionalidade.msg4" /></li>
						<li><fmt:message key="sobre.funcionalidade.msg5" /></li>
						<li><fmt:message key="sobre.funcionalidade.msg6" /></li>
					</ul>
				</article>
				<hr>
				<article>
					<h3><fmt:message key="sobre.tecnologias.titulo" /></h3>
					<%@include file="../templates/tecnologias.jsp"%>
					<ul>
						<li><strong><fmt:message key="sobre.tecnologias.infra" />:</strong></li>
						<li><a href="http://code.google.com/appengine/" title="Google App Engine" rel="no-follow" target="_blank">Google App Engine</a> - <fmt:message key="sobre.tecnologias.gae" /></li>
						<li><a href="http://www.github.com" title="Github" rel="no-follow" target="_blank">GitHub</a> - <fmt:message key="sobre.tecnologias.github" /></li>
						<li><strong><fmt:message key="sobre.tecnologias.server" />:</strong>
						<li><a href="http://www.oracle.com/br/technologies/java/index.html" title="Java" rel="no-follow" target="_blank">Java 6</a> - <fmt:message key="sobre.tecnologias.java" /></li>
						<li><a href="http://www.junit.org" title="JUnit" rel="no-follow" target="_blank">JUnit 4.8</a> - <fmt:message key="sobre.tecnologias.junit" /></li>
						<li><a href="http://vraptor.caelum.com.br" title="VRaptor" rel="no-follow" target="_blank">VRaptor 3.3.1</a> - <fmt:message key="sobre.tecnologias.vraptor" /><a href="http://www.caelum.com.br" target="_blank">&nbsp;<fmt:message key="sobre.tecnologias.caelum"/></a></li>
						<li><a href="http://jstl.java.net" title="JSTL" rel="no-follow" target="_blank">JSTL 1.2</a> - <fmt:message key="sobre.tecnologias.jstl" /></li>
						<li><strong><fmt:message key="sobre.tecnologias.cliente" />:</strong></li>
						<li><a href="http://www.w3schools.com/html5/default.asp" title="HTML5" rel="no-follow" target="_blank">HTML 5</a> - <fmt:message key="sobre.tecnologias.html5" />&nbsp;<a href="http://www.w3schools.com" target="_blank"><fmt:message key="sobre.tecnologias.w3c"/></a></li>
						<li><a href="http://www.w3schools.com/css3/default.asp" title="CSS3" rel="no-follow" target="_blank">CSS 3</a> - <fmt:message key="sobre.tecnologias.css3" />&nbsp;<a href="http://www.w3schools.com" target="_blank"><fmt:message key="sobre.tecnologias.w3c"/></a></li>
						<li><a href="http://developer.mozilla.org/en/Canvas_tutorial" title="Canvas" rel="no-follow" target="_blank">Canvas</a> - <fmt:message key="sobre.tecnologias.canvas" /></li>
						<li><a href="http://jquery.com/" title="JQuery" rel="no-follow" target="_blank">JQuery 1.6.4</a> - <fmt:message key="sobre.tecnologias.jquery" /></li>
						<li><a href="http://twitter.github.com/bootstrap/" title="Bootstrap" rel="no-follow" target="_blank">Bootstrat 1.3.0</a> - <fmt:message key="sobre.tecnologias.bootstrap" /></li>
						<li><a href="http://headjs.com/" title="HeadJS" rel="no-follow" target="_blank">Head JS 0.9</a> - <fmt:message key="sobre.tecnologias.headjs" />
					</ul>
				</article>
				<hr>
				<article>
					<h3><fmt:message key="sobre.browsers.titulo" /></h3>
					<%@include file="../templates/browsers.jsp"%>
				</article>
			</section>
			<%@include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>