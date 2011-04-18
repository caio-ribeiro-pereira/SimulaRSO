<!DOCTYPE html>
<fmt:setLocale value="pt-BR" />
<html>
	<head>
		<title>Simulação de Escalonamento de Disco</title>
		<%@ include file="../templates/script-loader.jsp"%>
		<script type="text/javascript">
			if(head.browser.ie && head.browser.version !== "9.0"){
				head.js('<c:url value="/resources/js/ie/excanvas.min.js" />');	
			}
			head.js('<c:url value="/resources/js/canvas/disco-chart.js" />');
		</script>
	</head>
	<body>
		<div class="container_12 main">
			<%@ include file="../templates/header.jsp"%>
			<article class="clearfix">
				<section class="clearfix">
					<h2 class="clearfix subtitle">Simulação de Escalonamento de Disco</h2>
					<c:forEach var="resultList" items="${resultadoDosAlgoritmos}">
						<p class="clearfix result-message"><strong>Algoritmo: ${resultList.algoritmoNome}</strong></p>
						<script type="text/javascript">
							head.ready(function(){
								$('#run-${resultList.algoritmoNome}').button({icons : {primary : 'ui-icon-gear'}});
								$('#simulation-${resultList.algoritmoNome}').hide();
								var requisicoes = new Array();
								<c:forEach items="${resultList.resultados}" var="disco">
								requisicoes.push(${disco.cilindro});
								</c:forEach>
								var espacoX = 15;
								var espacoY = 35;
								$('#disco-chart-${resultList.algoritmoNome}').attr('height', ((${resultList.totalRequisicoes} * espacoY) + 30));
								var canvas = document.getElementById("disco-chart-${resultList.algoritmoNome}");
								$('#run-${resultList.algoritmoNome}').click(function(){
									$('#run-${resultList.algoritmoNome}').hide();
									$('#simulation-${resultList.algoritmoNome}').show();
									new DiscoChart(canvas,espacoX,espacoY,requisicoes);
								});
							});
						</script>
						<p class="clearfix run">
							<button id="run-${resultList.algoritmoNome}">Ver simulação</button>
						</p>
						<p class="clearfix"><small>Total de movimentação de cilindros: ${resultList.movimentoTotalCilindros}</small></p>	
						<div class="graphic-panel clearfix" id="simulation-${resultList.algoritmoNome}">
							<canvas id="disco-chart-${resultList.algoritmoNome}" width="1520">
								O seu navegador não possui suporte HTML 5 para executar o elemento Canvas para renderização dos gráficos, clique no menu Sobre para saber quais versões de browsers utilizar.
							</canvas>
						</div>
						<hr>
					</c:forEach>
					<a class="clearfix" href="<c:url value="/escalonamento-disco" />">Realizar nova simulação...</a>
				</section>
			</article>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>