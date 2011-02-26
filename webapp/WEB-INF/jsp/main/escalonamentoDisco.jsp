<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
	<title>SimulaEDP - Escalonamento de Disco</title>
</head>
<body>
	<h2 class="grid_12">Escalonamento de Disco</h2>
	<canvas id="disco-canvas" width="940" height="400"></canvas>
	<script type="text/javascript" src="<c:url value="/resources/js/escalonameno-disco-canvas.js" />"></script>
</body>