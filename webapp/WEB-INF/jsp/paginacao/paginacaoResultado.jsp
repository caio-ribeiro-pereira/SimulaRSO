<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${paginacaoController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="paginacao.resultado.titulo" /></title>
		<%@ include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<%@ include file="../templates/menu.jsp"%>
		<div class="container">
			<%@ include file="../templates/header.jsp"%>
			<hr>
			<section>
				<article>
					<h2><fmt:message key="paginacao.resultado.titulo" /></h2>
					<c:forEach var="resultList" items="${resultadosDosAlgoritmos}">
						<h4 class="center"><fmt:message key="misc.algoritmo" />:&nbsp;${resultList.algoritmoNome}</h4>
						<p><strong><fmt:message key="paginacao.resultado.string.referencia" />:</strong>&nbsp;${resultList.totalStringReferencia}</p>
						<p><strong><fmt:message key="paginacao.resultado.frames" />:</strong>&nbsp;${resultList.totalFrames}</p>
						<p><strong><fmt:message key="paginacao.resultado.falhas.pagina" />:</strong>&nbsp;${resultList.totalPageFault}</p>
						<h4 class="center"><fmt:message key="misc.simulacao.grafica" />:&nbsp;${resultList.algoritmoNome}</h4>
						<div class="center row">
							<button class="btn success" id="run-${resultList.algoritmoNome}"><fmt:message key="misc.simulacao.ver" /></button>
						</div>
						<div class="row center hide chart-panel" id="simulation-${resultList.algoritmoNome}">
							<canvas id="paginacao-chart-${resultList.algoritmoNome}" width="960">
								<fmt:message key="misc.canvas.erro" />
							</canvas>
						</div>
						<br>
						<script type="text/javascript">
							head.ready(function(){
								var espaco = 22;
								var time = 750;
								var paginacao = new Array();
							<c:forEach items="${resultList.resultadoGrafico}" var="pg">
								paginacao.push({palavras: ${pg.palavrasToString}, pageFault: ${pg.pageFault}});
							</c:forEach>
								var stringRef = new Array();
							<c:forEach items="${resultList.stringReferencia}" var="strRef">
								stringRef.push(${strRef});
							</c:forEach>
								$('#paginacao-chart-${resultList.algoritmoNome}').attr('height', ((${resultList.totalFrames} * espaco) + 120));
								var canvas = document.getElementById('paginacao-chart-${resultList.algoritmoNome}');
								$('#run-${resultList.algoritmoNome}').click(function(){
									$('#run-${resultList.algoritmoNome}').hide();
									$('#simulation-${resultList.algoritmoNome}').show();
									var strRefLabel = '<fmt:message key="paginacao.string.referencia" />';
									var frameLabel = '<fmt:message key="paginacao.frames" />';
									simulaRSO.chart.paginacao(10, canvas, espaco,stringRef, paginacao, time, strRefLabel, frameLabel);
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