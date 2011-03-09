<!DOCTYPE html>
<head>
	<title>SimulaEDP - Escalonamento de Processos</title>
</head>	
<body>
	<h1 class="clearfix">Simulação do Algoritmo ${algoritmo[0].nome}</h1>
	<div class="graphic-panel clearfix">
		<canvas id="processo-canvas" class="clearfix" width="1900" height="400"></canvas>
	</div>
	<div class="clearfix">
		<a href="<c:url value="/escalonamento-processo" />">Realizar nova simulação...</a>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/escalonameno-processo-canvas.js" />"></script>
	<script type="text/javascript">
		var procCanvas = new ProcessoCanvas();
		procCanvas.setup();
		procCanvas.timeLine();
		procCanvas.background();
		procCanvas.resultado();
	</script>
</body>
</html>