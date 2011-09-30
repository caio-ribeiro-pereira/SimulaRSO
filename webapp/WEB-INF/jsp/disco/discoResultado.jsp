<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${discoController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="disco.resultado.titulo" /></title>
		<%@ include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<%@ include file="../templates/menu.jsp"%>
		<div class="container">
			<%@ include file="../templates/header.jsp"%>
			<hr>
			<section>
				<article>
					<h2><fmt:message key="disco.resultado.titulo" /></h2>
					<c:forEach var="resultList" items="${resultadoDosAlgoritmos}">
						<h4 class="center"><fmt:message key="misc.algoritmo" />:&nbsp;${resultList.algoritmoNome}</h4>
						<p><strong><fmt:message key="disco.total.movimentacao.cilindros" />:</strong>&nbsp;${resultList.movimentoTotalCilindros}</p>
						<h4 class="center"><fmt:message key="misc.simulacao.grafica" />:&nbsp;${resultList.algoritmoNome}</h4>	
						<div class="center row">
							<button class="btn success" id="run-${resultList.algoritmoNome}"><fmt:message key="misc.simulacao.ver" /></button>
						</div>
						<div class="row center hide chart-panel" id="simulation-${resultList.algoritmoNome}">
							<canvas id="disco-chart-${resultList.algoritmoNome}" width="1520">
								<fmt:message key="misc.canvas.erro" />
							</canvas>
						</div>
						<br>
						<script type="text/javascript">
							head.ready(function(){
								var espacoX = 15;
								var espacoY = 35;
								var requisicoes = new Array();
							<c:forEach items="${resultList.resultados}" var="disco">
								requisicoes.push(${disco.cilindro});
							</c:forEach>
								$('#disco-chart-${resultList.algoritmoNome}').attr('height', ((${resultList.totalRequisicoes} * espacoY) + 30));
								var canvas = document.getElementById("disco-chart-${resultList.algoritmoNome}");
								$('#run-${resultList.algoritmoNome}').click(function(){
									$('#run-${resultList.algoritmoNome}').hide();
									$('#simulation-${resultList.algoritmoNome}').show();
									simulaRSO.chart.disco(canvas,espacoX,espacoY,requisicoes);
								});
							});
						</script>
					</c:forEach>
				</article>
			</section>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>