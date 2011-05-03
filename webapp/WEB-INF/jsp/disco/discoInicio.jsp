<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${discoController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title>Algoritmos de escalonamento de disco</title>
		<meta name="keywords" content="Escalonamento de disco, Algoritmos de escalonamento de disco" />
		<meta name="keywords" content="disco, cilindro cabeça, cilindro, LOOK, FCFS, SCAN, CSCAN, SSTF" />
		<%@ include file="../templates/script-loader.jsp"%>
		<script type="text/javascript">
			head.ready(function(){
				$("#alg2").hide();
				$('input[type="text"]#cabeca').spinner({ min: 1, max: 99, showOn: 'always' }).onlyNumeric();
				$('button').button({icons : {primary : 'ui-icon-gear'}}).next().button({icons : {primary : 'ui-icon-shuffle'}});
				$('#totalRequisicao').change(function(){
					var content = $('#disco-menu').empty().hide();
					var cilindros = new Array();
					var total = $('#totalRequisicao').val();
					if(total > 0){
						for(var i=0;i<total;i++){
							cilindros.push({cilindroId : "cilindro-"+(i+1), cilindroName: "requisicoes[].cilindro", cilindroLabel: "Cilindro "+(i+1)});
						}
						var template = $("#discoTemplate").tmpl(cilindros);
						content.append(template).show();
						$('.cilindro-requisicao').spinner({ min: 1, max: 99, showOn: 'always' }).onlyNumeric();
					}else{
						$('#disco-menu').html('<strong class="clearfix info-message">Determine o total de cilindros.</strong>').show();
					}
				}).trigger('change');

				$('#disco-form').submit(function() {
					$('button').attr('disabled', 'disabled');
				});
				
				$('#random').click(function() {
					var total = $('#totalRequisicao').val();
					var MAXREF = 100;
					var randomCabeca = Math.floor(Math.random() * MAXREF);
					$('input[type="text"]#cabeca').val(randomCabeca);
					for ( var i = 1; i <= total; i++) {
						var randomRef = Math.floor(Math.random() * MAXREF);
						$('input[type="text"]#cilindro-' + i).val(randomRef);
					}
				});
				
				$("#modo").change(function(){
					if($("#modo").val() == 2){
						$("#alg2").show();
					}else{				
						$("#alg2").hide();
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
					
			});
		</script>
	</head>
	<body>
		<div class="container_12 main">
			<%@ include file="../templates/header.jsp"%>
			<article class="clearfix">
				<h2 class="clearfix subtitle">Algoritmos de escalonamento de disco</h2>
				<%@ include file="../templates/error-message.jsp"%>
				<section class="clearfix main-info">
					<p>
						<strong>Regras para simulação:</strong>
					</p>
					<p>
						1 - Determine o modo de simulação: <strong>(Única ou
							Comparativa).</strong>
					</p>
					<p>
						2 - *Defina o algoritmo de escalonamento de processos: <strong>(FC-FS,
							SSTF, LOOK, SCAN ou C-SCAN).</strong>
					</p>
					<p>
						3 - Determine a posição inicial do **cilindro cabeça do disco.
					</p>
					<p>
						4 - Escolha o total de requisições que serão realizados durante a simulação: 
							<strong>(Mínino	2 e Máximo 30)</strong>, ao escolher o total de requisições de cilindros,
							será exibido um painel para configurar cada posição do cilindro do disco. 
					<p>
						5 - Ao lado do botão <strong>executar</strong> existe a opção: <strong>Configuração
							automática</strong>, que serve para configurar aleatóriamente os atributos de cada processo.
					</p>
					<p>6 - Clique em executar para visualizar o resultado da execução do algoritmo de escalonamento.</p>
					<p>
						<strong>Observações:</strong>
					</p>
					<p>
						<strong>*</strong> Caso seja determinado o modo <strong>comparativo</strong>
						será necessário definir dois algoritmos de escalonamento distintos.
					</p>
					<p>
						<strong>**</strong> Cilindro cabeça é o primeiro cilindro pelo qual o algoritmo inicia sua execução.
					</p>
				</section>
				<section class="clearfix main-info">
					<form action="<c:url value="/executar-escalonamento-disco" />" method="post" id="disco-form">
						<div id="main-menu" class="clearfix menu">
							<p class="painel-config">
								<strong>Painel de configuração</strong>
							</p>
							<div class="grid_3">
								<label>Simulação</label>
								<select id="modo" name="modo" tabindex="1">
									<option value="1">Único</option>
									<option value="2">Comparativo</option>
								</select>
							</div>	
							<div id="alg1" class="grid_4">
								<label>Algoritmo 1:</label>
								<select id="algoritmo1" name="algDisco[0]" tabindex="2">
									<option value="">Selecione...</option>
									<c:forEach items="${algoritmoDisco}" var="alg">
										<option value="${alg}">${alg.nome}</option>		
									</c:forEach>
								</select> 
							</div>
							<div id="alg2" class="grid_4">
								<label>Algoritmo 2:</label>	
								<select id="algoritmo2" name="algDisco[1]" tabindex="3">
									<option value="">Selecione...</option>
									<c:forEach items="${algoritmoDisco}" var="alg">
										<option value="${alg}">${alg.nome}</option>
									</c:forEach>
								</select>
							</div>
							<div class="grid_8 menu">
							    <label>Total de requisições:</label>
								<select id="totalRequisicao" tabindex="4">
									<option value="">Selecione...</option>
									<c:forEach var="p" begin="2" end="30" step="1">
										<option value="${p}">${p} requisições</option>
									</c:forEach>
								</select>
							</div>
							<div class="grid_8 cilindro-cabeca menu">
								<label for="cabeca">Cilindro cabeça:</label>
							   	<input type="text" id="cabeca" name="cabeca.cilindro" maxlength="2" value="0" tabindex="5">
							</div>
						</div>
						<div id="disco-menu" class="clearfix menu"></div>
						<script id="discoTemplate" type="text/x-jquery-tmpl">
							<div class="disco-input-box">
								<label for="\${cilindroId}"><small>\${cilindroLabel}</small></label>
								<input type='text' class="cilindro-requisicao" id="\${cilindroId}" maxlength="2" name="\${cilindroName}" value="1">
							</div>
						</script>
						<strong id="error" class="clearfix error-message menu"></strong>
						<div class="clearfix execute-panel">
							<p>
								<button id="random" type="button" tabindex="8">Configuração automática</button>
								<button id="execute" type="submit" tabindex="7">Executar</button>
							</p>
						</div>
					</form>
				</section>
			</article>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>