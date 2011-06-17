<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${paginacaoController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="paginacao.resultado.titulo" /></title>
		<%@ include file="../templates/script-loader.jsp"%>
		<script type="text/javascript">
			if(head.browser.ie && head.browser.version !== "9.0"){
				head.js('<c:url value="/resources/js/ie/excanvas.min.js" />');	
			}
			head.js('<c:url value="/resources/js/canvas/paginacao-chart.js" />');
		</script>
	</head>
	<body>
		<div class="container_12 main">
			<%@ include file="../templates/header.jsp"%>
			<article class="clearfix">
				<section class="clearfix">
					<h2 class="clearfix subtitle"><fmt:message key="paginacao.resultado.titulo" /></h2>
					<c:forEach var="resultList" items="${resultadosDosAlgoritmos}">
						<p class="clearfix result-message"><strong><fmt:message key="misc.algoritmo" />: ${resultList.algoritmoNome}</strong></p>
						<script type="text/javascript">
								head.ready(function(){
									$('#run-${resultList.algoritmoNome}').button({icons : {primary : 'ui-icon-gear'}});
									$('#simulation-${resultList.algoritmoNome}').hide();
									var paginacao = new Array();
									<c:forEach items="${resultList.resultadoGrafico}" var="pg">
									paginacao.push({palavras: ${pg.palavrasToString}, pageFault: ${pg.pageFault}});
									</c:forEach>
									var stringRef = new Array();
									<c:forEach items="${resultList.stringReferencia}" var="strRef">
									stringRef.push(${strRef});
									</c:forEach>
									var espaco = 22;
									var time = 700;
									$('#paginacao-chart-${resultList.algoritmoNome}').attr('height', ((${resultList.totalFrames} * espaco) + 120));
									var canvas = document.getElementById('paginacao-chart-${resultList.algoritmoNome}');
									$('#run-${resultList.algoritmoNome}').click(function(){
										$('#run-${resultList.algoritmoNome}').hide();
										$('#simulation-${resultList.algoritmoNome}').show();
										var strRefLabel = '<fmt:message key="paginacao.string.referencia" />';
										var frameLabel = '<fmt:message key="paginacao.frames" />';
										new PaginacaoChart(10, canvas, espaco,stringRef, paginacao, time, strRefLabel, frameLabel);
									});
								});
						</script>
						<p class="clearfix run">
							<button id="run-${resultList.algoritmoNome}"><fmt:message key="misc.simulacao.ver" /></button>
						</p>
						<div class="graphic-panel clearfix" id="simulation-${resultList.algoritmoNome}">
							<canvas id="paginacao-chart-${resultList.algoritmoNome}" width="960">
								<fmt:message key="misc.canvas.erro" />
							</canvas>
						</div>
						<p class="clearfix"><small><fmt:message key="paginacao.resultado.string.referencia" />: ${resultList.totalStringReferencia}</small></p>
						<p class="clearfix"><small><fmt:message key="paginacao.resultado.frames" />: ${resultList.totalFrames}</small></p>
						<p class="clearfix"><small><fmt:message key="paginacao.resultado.falhas.pagina" />: ${resultList.totalPageFault}</small></p>
						<hr>
					</c:forEach>
					<a class="clearfix" href="<c:url value="/paginacao-memoria" />"><fmt:message key="misc.nova.simulacao" /></a>
				</section>
			</article>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>