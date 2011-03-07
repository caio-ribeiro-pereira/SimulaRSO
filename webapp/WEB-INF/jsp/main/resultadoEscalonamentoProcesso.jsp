<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<head>
	<title>SimulaEDP - Escalonamento de Processos</title>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-tmpl.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/escalonameno-processo-canvas.js" />"></script>
</head>	
<body>
	<h1 class="clearfix">Resultado Escalonamento de Processos</h1>
	<div class="graphic-panel clearfix">
		<canvas id="processo-canvas" class="clearfix" width="1900" height="400"></canvas>
	</div>
	<script type="text/javascript">
		$(function(){
			
			alert('${resultadoMap}');
			alert('${resultadoMap.get("tempoTotal")}');
			
			/*			
			var procCanvas = new ProcessoCanvas();
			procCanvas.setup();
			procCanvas.timeLine();
			procCanvas.background();
			procCanvas.resultado();
			*/
		});
	</script>
</body>