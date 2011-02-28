<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
	<title>SimulaEDP - Escalonamento de Processos</title>
</head>	
<body>
	<h2 class="grid_12">Escalonamento de Processos</h2>
	<canvas id="processo-canvas" width="940" height="400"></canvas>
	<div class="process-menu">
		<p>
			<strong>Total de processos: </strong>
			<select name="total" id="total">
				<c:forEach begin="2" end="20" step="1" var="p">
					<option value="${p}">${p}</option>
				</c:forEach>
			</select>
		</p>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/escalonameno-processo-canvas.js" />"></script>
	<script type="text/javascript">
		$(function(){
			var procCanvas = new ProcessoCanvas();
			procCanvas.setup();
			procCanvas.timeLine();
			procCanvas.background();
			procCanvas.resultado();
		});
	</script>
</body>