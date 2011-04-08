<!DOCTYPE html>
<fmt:setLocale value="pt-BR" />
<html lang="pt-BR" >
	<head>
		<title>Simulação de Paginação de Memória</title>
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
					<h2 class="clearfix subtitle">Simulação de Paginação de Memória</h2>
					<c:forEach var="resultList" items="${resultadosDosAlgoritmos}">
						<script type="text/javascript">
								head.ready(function(){
									var paginacao = new Array();
									<c:forEach items="${resultList.resultadoGrafico}" var="pg">
									paginacao.push({palavras: ${pg.palavrasToString}, pageFault: ${pg.pageFault}});
									</c:forEach>
									var stringRef = new Array();
									<c:forEach items="${resultList.stringReferencia}" var="strRef">
									stringRef.push(${strRef});
									</c:forEach>
									var espaco = 22;				
									var canvas = document.getElementById('paginacao-chart-${resultList.algoritmoNome}');
									var chart = new PaginacaoChart(10, canvas, espaco);
									chart.draw(stringRef,paginacao, ${resultList.totalPageFault});
								});
						</script>
						<strong class="clearfix result-message">Algoritmo: ${resultList.algoritmoNome}</strong>
						<canvas id="paginacao-chart-${resultList.algoritmoNome}" width="960" height="450">
							O seu navegador não possui suporte HTML 5 para executar o elemento Canvas para renderização dos gráficos, clique no menu Sobre para saber quais versões de browsers utilizar.
						</canvas>
						<hr>
					</c:forEach>
					<a class="clearfix" href="<c:url value="/paginacao-memoria" />">Realizar nova simulação...</a>
				</section>
			</article>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>