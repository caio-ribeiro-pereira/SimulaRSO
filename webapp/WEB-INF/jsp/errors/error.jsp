<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${param.idioma}" scope="session"/>
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="error.titulo" /></title>
		<%@include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<%@ include file="../templates/menu.jsp"%>
		<div class="container">
			<%@ include file="../templates/header.jsp"%>
	  		<section>
				<article>
					<h4 class="center"><fmt:message key="error.titulo" /></h4>
					<p class="center"><fmt:message key="error.msg1" /></p>
					<p class="center"><fmt:message key="error.msg2" /></p>
					<p class="center"><a href="https://github.com/caio-ribeiro-pereira/SimulaRSO/issues" title="GitHub Issues" target="_blank" rel="me"><fmt:message key="error.msg3" /></a></p>
				</article>
			</section>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>