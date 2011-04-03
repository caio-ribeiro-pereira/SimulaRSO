<!DOCTYPE html>
<fmt:setLocale value="pt-BR" />
<html lang="pt-BR" >
	<head>
		<title>Algoritmos de paginação</title>
		<meta name="keywords" content="Simulação de paginação, algoritmos de substituição de páginas" />
		<meta name="keywords" content="string, referência, byte, páginas, paginação, FIFO, LRU, OPT" />
		<%@include file="../templates/script-loader.jsp"%>
		<%-- <script type="text/javascript">
			head.ready(function(){
				$('#stringRefTotal').change(function(){
					var content = $('#pagination-menu').empty().hide();
					var total = $('#stringRefTotal').val();
					if(total > 0){
						var paginator = new Array();
						for(var i = 0; i < total; i++){
							paginator.push({inputStringRef:'stringRef'+(i+1), stringRefName:'stringRef['+i+']', labelStringRef:'Palavra '+(i+1)});
						}
						var template = $('#paginationTemplate').tmpl(paginator);
						content.append(template).show();
						$('input[type="text"].stringReferencia').spinner({ min: 0, max: 10, showOn: 'always' }).onlyNumeric();
					}else {
						$('#pagination-menu').html('<strong class="clearfix info-message">Determine o tamanho da string de referência para simular.</strong>').show();
					}
				}).trigger('change');
			});
		</script> --%>
	</head>
	<body>
		<div class="container_12 main">
			<%@ include file="../templates/header.jsp"%>
			<article class="clearfix">
				<section class="clearfix">
					<h2 class="clearfix subtitle">Algoritmos de paginação</h2>
					<%@ include file="../templates/error-message.jsp"%>
					<strong class="clearfix error-message">Em desenvolvimento...</strong>
					<%-- <form id="pagination-form" action="<c:url value="/executar-paginacao-memoria-virtual"/>" method="post">
						<div id="main-menu" class="clearfix menu">
							<div class="grid_3">
								<strong>Simulação: </strong>
								<select id="modo">
									<option value="UNICO">Única</option>
									<option value="COMPARATIVO">Comparativa</option>
								</select>
							</div>
							<div class="grid_5">
								<strong>Tamanho da String de Referência: </strong>
								<select id="stringRefTotal">
									<option value="" selected="selected">Selecione...</option>
									<c:forEach begin="10" end="40" step="10" var="sr">
										<option value="${sr}">${sr}</option>
									</c:forEach>
								</select>
							</div>
							<div class="grid_4">
								<strong>Total de Frames: </strong>
								<select name="frames">
									<option value="" selected="selected">Selecione...</option>
									<c:forEach begin="2" end="10" step="1" var="fr">
										<option value="${fr}">${fr} frames</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="algoritmo-menu" class="clearfix menu">
							<div id="alg1" class="grid_3">
								<strong>Algoritmo 1: </strong>
								<select name="algs[0]" id="algoritmo1">
									<option value="" selected="selected">Selecione...</option>
									<c:forEach var="alg" items="${algoritmoPaginacao}">
										<option value="${alg}">${alg.nome}</option>	
									</c:forEach>
								</select>
							</div>
							<div id="alg2" class="grid_3">
								<strong>Algoritmo 2: </strong>
								<select name="algs[1]" id="algoritmo2">
									<option value="" selected="selected">Selecione...</option>
									<c:forEach var="alg" items="${algoritmoPaginacao}">
										<option value="${alg}">${alg.nome}</option>	
									</c:forEach>
								</select>			
							</div>
						</div>
						<div id="pagination-menu" class="clearfix menu"></div>
						<script id="paginationTemplate" type="text/x-jquery-tmpl">
							<div class="grid_2 pagination-input-box">
								<label class="clearfix" for="\${inputStringRef}"><small>\${labelStringRef}:</small></label>
								<input type="text" class="grid_1 stringReferencia" name="\${stringRefName}" id="\${inputStringRef}" maxlength="2">
							</div>
						</script>
						<div class="clearfix execute-panel">
							<p>
								<button id="execute" type="submit">Executar</button>
								<button id="random" type="button">Geração aleatória</button>			
							</p>
						</div>
					</form> --%>
				</section>
			</article>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>