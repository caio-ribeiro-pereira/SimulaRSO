<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
	<title>SimulaEDP - Escalonamento de Processos</title>
</head>	
<body>
	<h2 class="grid_12">Escalonamento de Processos</h2>
	<div id="main-menu">
		<p>
			<strong>Modo: </strong>
			<select name="modo" id="modo">
				<c:forEach var="modoSimulacao" items="${modosDeSimulacao}">
					<option value="${modoSimulacao}">${modoSimulacao.modo}</option>
				</c:forEach>
			</select>
			<strong>Algoritmo: </strong>
			<select name="algoritmo" id="algoritmo">
				<c:forEach var="algoritmo" items="${algoritmosDeProcesso}">
					<option value="${algoritmo}">${algoritmo.nome}</option>
				</c:forEach>
			</select>
			<strong>Total de processos: </strong>
			<select name="total" id="total">
				<c:forEach begin="2" end="10" step="1" var="p">
					<option value="${p}">${p}</option>
				</c:forEach>
			</select>
			<button>Preparar processos</button>
		</p>
	</div>
	<div id="process-menu">
	</div>
	<canvas id="processo-canvas" width="940" height="400"></canvas>
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