<!DOCTYPE html>
<head>
	<title>Simulação do Algoritmo ${algoritmo[0].nome}</title>
	<c:set var="resultadoFinal" value="${resultadoProcesso.resultadoFinal}" />
	<c:set var="resultadoGrafico" value="${resultadoProcesso.resultadoGrafico}" />
	<c:set var="esperaMedia" value="${resultadoProcesso.tempoEsperaMedia}" />
	<c:set var="respostaMedia" value="${resultadoProcesso.tempoRespostaMedia}" />
	<c:set var="turnAroundMedio" value="${resultadoProcesso.tempoTurnAroundMedio}" />
	<c:set var="tempoTotal" value="${resultadoProcesso.tempoTotal}" />
	<c:set var="totalProcessos" value="${resultadoProcesso.totalProcessos}" />
</head>	
<body>
	<h1 class="clearfix">Simulação do Algoritmo ${algoritmo[0].nome}</h1>
	<div class="graphic-panel clearfix">
		<canvas id="processo-chart" height="300"></canvas>
	</div>
		<table class="grid_12 result-panel">
			<thead>
				<tr>
					<td colspan="5"><strong>Resultado da simulação: ${algoritmo[0].nome}</strong></td>
				</tr>
				<tr>
					<td><strong>Processo</strong></td>
					<td><strong>Burst CPU</strong></td>
					<td><strong>Tempo de Espera</strong></td>
					<td><strong>Tempo de Resposta</strong></td>
					<td><strong>Turn Around</strong></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${resultadoFinal}" var="res">
				<tr class="result-line">
					<td>${res.id}</td>
					<td>${res.burst}</td>
					<td>${res.espera} ms</td>
					<td>${res.resposta} ms</td>
					<td>${res.turnAround} ms</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5"><strong>Informações relevantes</strong></td>
				</tr>
				<tr>
					<td><strong>Total de Processos</strong></td>
					<td><strong>Tempo de Execução</strong></td>
					<td><strong>Tempo de Espera Médio</strong></td>
					<td><strong>Tempo de Resposta Médio</strong></td>
					<td><strong>Turn Around Médio</strong></td>
				</tr>
				<tr class="result-line">
					<td>${totalProcessos}</td>
					<td>${tempoTotal} ms</td>
					<td>${esperaMedia} ms</td>
					<td>${respostaMedia} ms</td>
					<td>${turnAroundMedio} ms</td>
				</tr>
			</tfoot>
		</table>
	<div class="clearfix">
		<a href="<c:url value="/escalonamento-processo" />">Realizar nova simulação...</a>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/processo-chart.js" />"></script>
	<script type="text/javascript">
		$(function(){
			var total = ${totalProcessos};
			var espaco = 20;
			$('#processo-chart').attr('width', ((${tempoTotal} + 1) * espaco));
			var chart = new ProcessoChart(total, espaco);
			chart.background();
		});
	</script>
</body>
</html>