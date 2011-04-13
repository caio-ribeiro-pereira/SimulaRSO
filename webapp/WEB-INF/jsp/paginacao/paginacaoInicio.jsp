<!DOCTYPE html>
<fmt:setLocale value="pt-BR" />
<html lang="pt-BR" >
	<head>
		<title>Algoritmos de paginação de memória</title>
		<meta name="keywords" content="Simulação de paginação, algoritmos de substituição de páginas" />
		<meta name="keywords" content="string de referência, paginação, FIFO, LRU, OPT" />
		<%@include file="../templates/script-loader.jsp"%>
		<script type="text/javascript">
			head.ready(function(){
				
				$('button').button();
				$('#alg2').hide();
				
				$('#random').click(function() {
					var total = $('#stringRefTotal').val();
					for ( var i = 1; i <= total; i++) {
						var MAXREF = 10;
						var randomRef = Math.floor(Math.random() * MAXREF);
						$('input[type="text"]#stringReferencia-' + i).val(randomRef);
					}
				});
				
				$('#pagination-form').submit(function() {
					$('button').attr('disabled', 'disabled');
				});
				
				$('#modo').change(function() {
					if (this.value == 'COMPARATIVO') {
						$('#alg2').show();
					} else {
						$('#alg2').hide();
					}
				}).trigger('change');
				
				$('#algoritmo1').change(function(){
					$('#execute').removeAttr('disabled');
					$('#error').text('');
					if(this.value == $('#algoritmo2').val() && this.value != ''){
						$('#execute').attr('disabled','disabled');
						$('#error').text('Escolha um algoritmo diferente.').show();
					}
				}).trigger('change');
				
				$('#algoritmo2').change(function(){
					$('#execute').removeAttr('disabled');
					$('#error').text('');
					if(this.value == $('#algoritmo1').val() && this.value != ''){
						$('#execute').attr('disabled','disabled');
						$('#error').text('Escolha um algoritmo diferente.').show();
					}
				}).trigger('change');
				
				$('#stringRefTotal').change(function(){
					var content = $('#pagination-menu').empty().hide();
					var total = $('#stringRefTotal').val();
					if(total > 0){
						var paginator = new Array();
						for(var i = 0; i < total; i++){
							paginator.push({inputStringRef:'stringReferencia-'+(i+1), 
											labelStringRef:'Palavra '+(i+1)});
						}
						var template = $('#paginationTemplate').tmpl(paginator);
						content.append(template).show();
						$('input[type="text"].stringReferencia').spinner({ min: 0, max: 9, showOn: 'always' }).onlyNumeric();
					}else {
						$('#pagination-menu').html('<strong class="clearfix info-message">Determine o tamanho da string de referência para simular.</strong>').show();
					}
				}).trigger('change');
			});
		</script>
	</head>
	<body>
		<div class="container_12 main">
			<%@ include file="../templates/header.jsp"%>
			<article class="clearfix">
				<h2 class="clearfix subtitle">Algoritmos de paginação de memória</h2>
				<%@ include file="../templates/error-message.jsp"%>
				<section class="clearfix main-info">
					<p>
						<strong>Regras para simulação:</strong>
					</p>
					<p>
						1 - *Determine o modo de simulação: <strong>(Única ou Comparativa).</strong>
					</p>
					<p>
						2 - Defina o tamanho da <strong>String de Referência</strong> para o algoritmo de paginação:
						<strong>(Mínino	6 e Máximo 30)</strong>, ao definir o tamanho da <strong>String de Referência</strong> 
						automaticamente será apresentado um painel abaixo para configurar cada <strong>palavra hipotética</strong> 
						que	será agregada na própria <strong>String de Referência.</strong>
					</p>
					<p>
						3 - Configure o total de frames: <strong>(Mínino 2 e Máximo 10)</strong> que será 
						utilizado no processo de paginação da <strong>String de Referência.</strong>
					</p>
					<p>
						4 - Defina o algoritmo de paginação de memória: <strong>(FIFO, LRU, OPT, MRU).</strong>
					</p>
					<p>
						5 - Clique no botão executar para visualizar o comportamento do algoritmo selecionado.
					</p>
					<p>
						6 - Ao lado do botão <strong>executar</strong> existe a opção: <strong>Configuração
							automática</strong>, que serve para configurar aleatóriamente os atributos
							das <strong>palavras hipotéticas</strong> existentes.
					</p>
					<p>
						<strong>Observações:</strong>
					</p>
					<p>
						<strong>*</strong> Caso seja determinado o modo <strong>comparativo</strong>
						será necessário definir dois algoritmos distintos para simulação.
					</p>
				</section>
				<section class="clearfix main-info">
					<form id="pagination-form" action="<c:url value="/executar-paginacao-memoria"/>" method="post">
						<div id="main-menu" class="clearfix menu">
							<p class="painel-config">
								<strong>Painel de configuração</strong>
							</p>
							<div class="grid_3">
								<strong>Simulação: </strong>
								<select id="modo" tabindex="1">
									<option value="UNICO">Única</option>
									<option value="COMPARATIVO">Comparativa</option>
								</select>
							</div>
							<div id="alg1" class="grid_3">
								<strong>Algoritmo 1: </strong>
								<select name="algs[0]" id="algoritmo1" tabindex="2">
									<option value="">Selecione...</option>
									<c:forEach var="alg" items="${paginacaoMemoriaAlgoritmo}">
										<option value="${alg}">${alg.nome}</option>	
									</c:forEach>
								</select>
							</div>
							<div id="alg2" class="grid_4">
								<strong>Algoritmo 2: </strong>
								<select name="algs[1]" id="algoritmo2" tabindex="3">
									<option value="">Selecione...</option>
									<c:forEach var="alg" items="${paginacaoMemoriaAlgoritmo}">
										<option value="${alg}">${alg.nome}</option>	
									</c:forEach>
								</select>			
							</div>
							<div class="grid_8 menu">
								<strong>Total de Frames: </strong>
								<select name="frames" tabindex="4">
									<option value="">Selecione...</option>
									<c:forEach begin="2" end="10" step="1" var="fr">
										<option value="${fr}">${fr} frames</option>
									</c:forEach>
								</select>
							</div>
							<div class="grid_8 menu">
								<strong>Tamanho da String de Referência: </strong>
								<select id="stringRefTotal" tabindex="5">
									<option value="">Selecione...</option>
									<c:forEach begin="6" end="30" step="1" var="sr">
										<option value="${sr}">${sr}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="pagination-menu" class="clearfix menu"></div>
						<script id="paginationTemplate" type="text/x-jquery-tmpl">
							<div class="pagination-input-box">
								<label for="\${inputStringRef}"><small>\${labelStringRef}:</small></label>
								<input type="text" class="stringReferencia" name="stringRef[]" id="\${inputStringRef}" value="0" maxlength="1">
							</div>
						</script>
						<strong id="error" class="clearfix error-message menu"></strong>
						<div class="clearfix execute-panel">
							<p>
								<button id="random" type="button" tabindex="7">Configuração automática</button>
								<button id="execute" type="submit" tabindex="8">Executar</button>
							</p>
						</div>
					</form>
				</section>
			</article>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>