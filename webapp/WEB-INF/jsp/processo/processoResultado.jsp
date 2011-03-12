<!DOCTYPE html>
<head>
	<title>Simulação dos Algoritmos de Escalonamento de Processos</title>
	<script type="text/javascript">
		head.ready(function(){
			$('table.result-panel tr.result-line:odd').css('background-color','#DDD');
			$('table.result-panel tr.result-line:even').css('background-color','#CCC');
		});
	</script>
</head>	
<body>
	<h1 class="clearfix">Simulação dos Algoritmos de Escalonamento de Processos</h1>
	<c:forEach var="resultList" items="${resultadosDosAlgoritmos}">
		<h2 class="clearfix">Resultado do escalonamento: ${resultList.algoritmoNome}</h2>
		<div class="graphic-panel clearfix">
			<canvas id="processo-chart-${resultList.algoritmoNome}"></canvas>
		</div>
		<table class="grid_12 result-panel">
			<thead>
				<tr>
					<td colspan="5"><strong>Informações relevantes</strong></td>
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
				<c:forEach items="${resultList.resultadoFinal}" var="res">
				<tr class="result-line">
					<td>${res.id}</td>
					<td>${res.burst} ms</td>
					<td>${res.espera} ms</td>
					<td>${res.resposta} ms</td>
					<td>${res.turnAround} ms</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td><strong>Total de Processos</strong></td>
					<td><strong>Tempo de Execução</strong></td>
					<td><strong>Tempo de Espera Médio</strong></td>
					<td><strong>Tempo de Resposta Médio</strong></td>
					<td><strong>Turn Around Médio</strong></td>
				</tr>
				<tr class="result-line">
					<td>${resultList.totalProcessos}</td>
					<td>${resultList.tempoTotal} ms</td>
					<td>${resultList.tempoEsperaMedia} ms</td>
					<td>${resultList.tempoRespostaMedia} ms</td>
					<td>${resultList.tempoTurnAroundMedio} ms</td>
				</tr>
			</tfoot>
		</table>
		<script type="text/javascript">
			head.ready(function(){
				var processos = new Array();
				<c:set var="x" value="0" />
				<c:set var="h" value="20" />
				<c:forEach items="${resultList.resultadoGrafico}" var="pr">
					<c:set var="w" value="${pr.burstAtual * 20}" />
					<c:set var="y" value="${pr.id * 20}" />
				processos.push({x : ${x}, y : ${y}, w : ${w}, h : ${h}, cor: '${pr.cor}'});
					<c:set var="x" value="${x+w}" />
				</c:forEach>
				$('#processo-chart-${resultList.algoritmoNome}').attr('width', ((${resultList.tempoTotal} + 1) * ${h}));
				$('#processo-chart-${resultList.algoritmoNome}').attr('height', ((${resultList.totalProcessos} + 2) * ${h}));
				var canvas = document.getElementById('processo-chart-${resultList.algoritmoNome}');
				var chart = new ProcessoChart(${resultList.totalProcessos}, canvas);
				chart.background();
				chart.draw(processos);
			});
		</script>
	</c:forEach>
	<div class="clearfix">
		<a href="<c:url value="/escalonamento-processo" />">Realizar nova simulação...</a>
	</div>
</body>