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
		<div class="container_12 main">
			<%@ include file="../templates/header.jsp"%>
				<article class="clearfix">
			  		<section class="clearfix main-info">
						<strong class="clearfix info-message"><fmt:message key="notfound.msg1" /></strong>
						<strong class="clearfix info-message"><fmt:message key="notfound.msg2" /></strong>
					</section>
				</article>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>