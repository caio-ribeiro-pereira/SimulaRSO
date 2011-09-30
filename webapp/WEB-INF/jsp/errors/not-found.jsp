<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${param.idioma}" scope="session"/>
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="notfound.titulo" /></title>
		<%@include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<%@ include file="../templates/menu.jsp"%>
		<div class="container">
			<%@ include file="../templates/header.jsp"%>
		  		<section>
					<article>
						<h4 class="center"><fmt:message key="notfound.titulo" /></h4>
						<p class="center"><fmt:message key="notfound.msg1" /></p>
					</article>
				</section>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>