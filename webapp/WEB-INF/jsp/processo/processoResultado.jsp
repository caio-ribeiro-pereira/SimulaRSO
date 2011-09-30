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
		<%@ include file="../templates/menu.jsp"%>
		<div class="container">
			<%@include file="../templates/header.jsp"%>
			<hr>
			<section>
				<article>
					<h2><fmt:message key="processo.resultado.titulo" /></h2>
					<c:forEach var="resultList" items="${resultadosDosAlgoritmos}">
						<script type="text/javascript">
							head.ready(function(){
								var espaco = 26;
								var time = 100;
								var processos = new Array();
							<c:forEach items="${resultList.resultadoGrafico}" var="pr">
								processos.push({x : ${pr.x}, y : ${pr.y}, w : ${pr.w}, h : ${pr.h}, cor: '${pr.cor}'});
							</c:forEach>
								$('#processo-chart-${resultList.algoritmoNome}').attr('width', ((${resultList.tempoTotal} + 1) * espaco));
								$('#processo-chart-${resultList.algoritmoNome}').attr('height', ((${resultList.totalProcessos} + 2) * espaco));
								var canvas = document.getElementById('processo-chart-${resultList.algoritmoNome}');
								$('#run-${resultList.algoritmoNome}').click(function(){
									$('#run-${resultList.algoritmoNome}').hide();
									$('#simulation-${resultList.algoritmoNome}').fadeIn();
									simulaRSO.chart.processo(${resultList.totalProcessos}, canvas, espaco, processos, time);
								});
							});
						</script>
						<h4 class="center"><fmt:message key="misc.algoritmo" />:&nbsp;${resultList.algoritmoNome}</h4>
						<table>
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
									<tr ${res.id % 2 eq 1 ? 'class="even"':'class="odd"'}>
										<td><div class="block" style="background-color:${res.cor};">${res.id}</div></td>
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
								<tr class="even">
									<td>${resultList.totalProcessos}</td>
									<td>${resultList.tempoTotal} ms</td>
									<td>${resultList.tempoEsperaMedia} ms</td>
									<td>${resultList.tempoRespostaMedia} ms</td>
									<td>${resultList.tempoTurnAroundMedio} ms</td>
								</tr>
							</tfoot>
						</table>
						<hr>
						<h4 class="center"><fmt:message key="misc.simulacao.grafica" />:&nbsp;${resultList.algoritmoNome}</h4>
						<div class="center row">
							<button class="btn success" id="run-${resultList.algoritmoNome}"><fmt:message key="misc.simulacao.ver" /></button>
						</div>
						<div class="row center hide chart-panel" id="simulation-${resultList.algoritmoNome}">
							<canvas id="processo-chart-${resultList.algoritmoNome}">
								<fmt:message key="misc.canvas.erro" />
							</canvas>
						</div>
						<br>
					</c:forEach>
				</article>
			</section>
			<%@include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>