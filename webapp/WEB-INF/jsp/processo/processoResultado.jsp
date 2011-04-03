<!DOCTYPE html>
<head>
	<title>Simulação Gráfica de Escalonamento de Processos</title>
</head>	
<body>
	<h2 class="clearfix subtitle">Simulação de Escalonamento de Processos</h2>
	<c:forEach var="resultList" items="${resultadosDosAlgoritmos}">
		<script type="text/javascript">
			if(head.browser.ie && head.browser.version !== "9.0"){
				head.js('<c:url value="/resources/js/ie/excanvas.min.js" />');	
			}
			head.js('<c:url value="/resources/js/canvas/processo-chart.js" />')
				.js('<c:url value="/resources/js/canvas/colors.js" />');
			head.ready(function(){
				var processos = new Array();
				<c:forEach items="${resultList.resultadoGrafico}" var="pr">
				processos.push({x : ${pr.x}, y : ${pr.y}, w : ${pr.w}, h : ${pr.h}, cor: '${pr.cor}'});
				</c:forEach>
				var espaco = 26;				
				$('#processo-chart-${resultList.algoritmoNome}').attr('width', ((${resultList.tempoTotal} + 1) * espaco));
				$('#processo-chart-${resultList.algoritmoNome}').attr('height', ((${resultList.totalProcessos} + 2) * espaco));
				var canvas = document.getElementById('processo-chart-${resultList.algoritmoNome}');
				var chart = new ProcessoChart(${resultList.totalProcessos}, canvas, espaco);
				chart.draw(processos);
				chart.background();
			});
		</script>
		<strong class="clearfix result-message">Algoritmo: ${resultList.algoritmoNome}</strong>
		<table class="clearfix result-panel">
			<thead>
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
					<tr class="result-line" ${res.id % 2 eq 1 ? 'id="even"':'id="odd"'}>
						<td><div class="processo-cor" style="background-color:${res.cor};"></div></td>
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
				<tr class="result-line" id="odd">
					<td>${resultList.totalProcessos}</td>
					<td>${resultList.tempoTotal} ms</td>
					<td>${resultList.tempoEsperaMedia} ms</td>
					<td>${resultList.tempoRespostaMedia} ms</td>
					<td>${resultList.tempoTurnAroundMedio} ms</td>
				</tr>
			</tfoot>
		</table>
		<strong class="clearfix result-message">Simulação gráfica: ${resultList.algoritmoNome}</strong>
		<div class="graphic-panel clearfix">
			<canvas id="processo-chart-${resultList.algoritmoNome}">
				O seu navegador não possui suporte HTML 5 para executar o elemento Canvas para renderização dos gráficos, clique no menu Sobre para saber quais versões de browsers utilizar.
			</canvas>
		</div>
		<hr>
	</c:forEach>
	<a class="clearfix" href="<c:url value="/escalonamento-processo" />">Realizar nova simulação...</a>
</body>