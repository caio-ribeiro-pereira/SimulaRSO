<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${processoController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="processo.resultado.titulo" /></title>
		<%@include file="../templates/script-loader.jsp"%>
	</head>	
	<body>
		<div class="container_12 main">
			<%@include file="../templates/header.jsp"%>
			<article class="clearfix">
				<section class="clearfix">
					<h2 class="clearfix subtitle"><fmt:message key="processo.resultado.titulo" /></h2>
					<c:forEach var="resultList" items="${resultadosDosAlgoritmos}">
						<script type="text/javascript">
							head.js('<c:url value="/resources/js/canvas/processo-chart.js" />');
							head.ready(function(){
								$('#run-${resultList.algoritmoNome}').button({icons : {primary : 'ui-icon-gear'}});
								$('#simulation-${resultList.algoritmoNome}').hide();
								var processos = new Array();
								<c:forEach items="${resultList.resultadoGrafico}" var="pr">
								processos.push({x : ${pr.x}, y : ${pr.y}, w : ${pr.w}, h : ${pr.h}, cor: '${pr.cor}'});
								</c:forEach>
								var espaco = 26;
								var time = 100;
								$('#processo-chart-${resultList.algoritmoNome}').attr('width', ((${resultList.tempoTotal} + 1) * espaco));
								$('#processo-chart-${resultList.algoritmoNome}').attr('height', ((${resultList.totalProcessos} + 2) * espaco));
								var canvas = document.getElementById('processo-chart-${resultList.algoritmoNome}');
								$('#run-${resultList.algoritmoNome}').click(function(){
									$('#run-${resultList.algoritmoNome}').hide();
									$('#simulation-${resultList.algoritmoNome}').fadeIn();
									new ProcessoChart(${resultList.totalProcessos}, canvas, espaco, processos, time);
								});
							});
						</script>
						<strong class="clearfix result-message"><fmt:message key="misc.algoritmo" />: ${resultList.algoritmoNome}</strong>
						<table class="result-panel">
							<thead>
								<tr>
									<td><strong><fmt:message key="processo.label" /></strong></td>
									<td><strong><fmt:message key="processo.burst.label" /></strong></td>
									<td><strong><fmt:message key="processo.tempo.espera.label" /></strong></td>
									<td><strong><fmt:message key="processo.tempo.resposta.label" /></strong></td>
									<td><strong><fmt:message key="processo.turnaround.label" /></strong></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${resultList.resultadoFinal}" var="res">
									<tr class="result-line" ${res.id % 2 eq 1 ? 'id="even"':'id="odd"'}>
										<td><div class="processo-cor" style="background-color:${res.cor};">${res.id}</div></td>
										<td>${res.burst} ms</td>
										<td>${res.espera} ms</td>
										<td>${res.resposta} ms</td>
										<td>${res.turnAround} ms</td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td><strong><fmt:message key="processo.total.processos" /></strong></td>
									<td><strong><fmt:message key="processo.tempo.execucao" /></strong></td>
									<td><strong><fmt:message key="processo.tempo.espera.medio" /></strong></td>
									<td><strong><fmt:message key="processo.tempo.resposta.medio" /></strong></td>
									<td><strong><fmt:message key="processo.turnaround.medio" /></strong></td>
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
						<strong class="clearfix result-message"><fmt:message key="misc.simulacao.grafica" />: ${resultList.algoritmoNome}</strong>
						<p class="clearfix run">
							<button id="run-${resultList.algoritmoNome}"><fmt:message key="misc.simulacao.ver" /></button>
						</p>
						<div class="graphic-panel clearfix" id="simulation-${resultList.algoritmoNome}">
							<canvas id="processo-chart-${resultList.algoritmoNome}">
								<fmt:message key="misc.canvas.erro" />
							</canvas>
						</div>
						<hr>
					</c:forEach>
					<a class="clearfix" href="<c:url value="/escalonamento-processo" />"><fmt:message key="misc.nova.simulacao" /></a>
				</section>
			</article>
			<%@include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>