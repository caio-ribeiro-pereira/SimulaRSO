<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<head>
	<title>SimulaEDP - Escalonamento de Disco</title>
</head>
<body>
	<h1 class="clearfix">Escalonamento de Disco</h1>
	<div class="graphic-panel clearfix">
		<canvas id="disco-canvas" class="clearfix" width="1900" height="400"></canvas>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/escalonameno-disco-canvas.js" />"></script>
</body>