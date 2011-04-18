<!DOCTYPE html>
<fmt:setLocale value="pt-BR" />
<html lang="pt-BR">
<head>
<title>Algoritmos de escalonamento de processos</title>
	<meta name="keywords" content="Algoritmos de escalonamento de processos, Processos, Processo" />
	<meta name="keywords" content="FC-FS, SJF, SRT, Round-Robin" />
	<%@include file="../templates/script-loader.jsp"%>
	<script type="text/javascript">
		if (head.browser.ie && head.browser.version !== "9.0") {
			head.js('<c:url value="/resources/js/ie/excanvas.min.js" />');
		}
		head.js('<c:url value="/resources/js/canvas/colors.js" />');
		head.ready(function() {
				var MAXBURST = 98;
				var MAXCHEGADA = 100;
				var MAXPRIORIDADE = 11;

				$('button').button({icons : {primary : 'ui-icon-gear'}}).next().button({icons : {primary : 'ui-icon-shuffle'}});

				$('#alg2').hide();
				$('#quantum').hide();

				$('#random').click(function() {
					var total = $('#total').val();
					for ( var i = 1; i <= total; i++) {
						var randomBurst = Math.floor(Math.random() * MAXBURST) + 1;
						var randomChegada = Math.floor(Math.random() * MAXCHEGADA);
						var randomPrioridade = Math.floor(Math.random() * MAXPRIORIDADE);
						$('input[type="text"]#burst-' + i).val(randomBurst);
						$('input[type="text"]#chegada-' + i).val(randomChegada);
						$('input[type="text"]#prioridade-' + i).val(randomPrioridade);
					}
				});

				$('#process-form').submit(function() {
					$('button').attr('disabled', 'disabled');
				});

				$('#modo').change(function() {
					if (this.value == 2) {
						$('#alg2').show();
					} else {
						if ($('#algoritmo1').val() != 'RR' && $('#algoritmo2').val() == 'RR') {
							$('#quantum').hide();
							$('#quantum select option:first-child').attr('selected','selected');
						}
						$('#alg2').hide();
						$('#alg2 select option:first-child').attr('selected', 'selected');
					}
				}).trigger('change');

				$('#algoritmo1').change(function() {
					$('#error').text('');
					$('#execute').removeAttr('disabled');
					if (this.value == 'RR' || $('#algoritmo2').val() == 'RR') {
						$('#quantum').show();
					} else {
						$('#quantum').hide();
						$('#quantum select option:first-child').attr('selected', 'selected');
					}
					if(this.value == $('#algoritmo2').val() && this.value != ''){
						$('#error').text('Escolha um algoritmo diferente.').show();
						$('#execute').attr('disabled', 'disabled');
					}
				}).trigger('change');

				$('#algoritmo2').change(function() {
					$('#error').text('');
					$('#execute').removeAttr('disabled');
					if (this.value == 'RR' || $('#algoritmo1').val() == 'RR') {
						$('#quantum').show();
					} else {
						$('#quantum').hide();
						$('#quantum select option:first-child').attr('selected', 'selected');
					}
					if(this.value == $('#algoritmo1').val() && this.value != ''){
						$('#error').text('Escolha um algoritmo diferente.').show();
						$('#execute').attr('disabled', 'disabled');
					}
				}).trigger('change');

				$('#total').change(function() {
					var content = $('#process-menu').empty().hide();
					var total = $('#total').val();
					if (total > 0) {
						var processos = new Array();
						var colors = new Colors();
						for ( var i = 0; i < total; i++) {
							processos.push({inputBurst : "burst-"+ (i + 1),
											inputChegada : "chegada-"+ (i + 1),
											inputPrioridade : "prioridade-"+ (i + 1),
											prCor : colors[i],
											prDivId : "processo-"+ (i + 1),
											prLabel : "Processo: "+ (i + 1),
											prId : (i + 1)
										});
						}
						var template = $('#processTemplate').tmpl(processos);
						content.append(template).show();
						$('input[type="text"].burst').spinner({min : 1,max : 99,showOn : 'always'}).onlyNumeric();
						$('input[type="text"].chegada').spinner({min : 0,max : 99,showOn : 'always'}).onlyNumeric();
						$('input[type="text"].prioridade').spinner({min : 1,max : 10,showOn : 'always'}).onlyNumeric();
					} else {
						$('#process-menu').html('<strong class="clearfix info-message">Determine o número de processos para simular.</strong>').show();
					}
				}).trigger('change');
			});
</script>
</head>
<body>
	<div class="container_12 main">
		<%@ include file="../templates/header.jsp"%>
		<article class="clearfix">
			<section class="clearfix">
				<h2 class="subtitle">Algoritmos de escalonamento de processos</h2>
				<%@ include file="../templates/error-message.jsp"%>
			</section>
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
						SJF, SRT ou **Round Robin).</strong>
				</p>
				<p>
					3 - Escolha o total de processos que serão simulados: <strong>(Mínino
						2 e Máximo 20)</strong>, ao escolher o total de processos será exibido um painel 
						para configuração de cada processo definido no total de processos. 
						Em cada campo será possível configurar as
						características principais de um processo para ser hipotéticamente
						executado. Os atributos de um processo são: <strong>(***Burst-CPU,
						****Tempo de chegada, *****Prioridade).</strong>
				<p>4 - Clique em executar para visualizar o resultado da execução do algoritmo de escalonamento.</p>
				<p>
					5 - Ao lado do botão <strong>executar</strong> existe a opção: <strong>Configuração
						automática</strong>, que serve para configurar aleatóriamente os atributos de cada processo.
				</p>
				<p>
					<strong>Observações:</strong>
				</p>
				<p>
					<strong>*</strong> Caso seja determinado o modo <strong>comparativo</strong>
					será necessário definir dois algoritmos de escalonamento distintos.
				</p>
				<p>
					<strong>**</strong> Ao escolher o algoritmo <strong>Round Robin</strong> 
					surgirá um campo chamado <strong>Tempo de corte</strong> que deve ser definido 
					para o funcionamento deste algoritmo.
				</p>
				<p>
					<strong>*** Burst-CPU:</strong> É tempo de surto de um processo, é o
					tempo necessário que o processo possui para ser alocado na CPU.
				</p>
				<p>
					<strong>**** Tempo de chegada:</strong> É o tempo em que o processo
					será iniciado, ou seja, é o tempo exato que o processo será alocado na
					CPU ou ficará na fila de processos em espera.
				</p>
				<p>
					<strong>***** Prioridade:</strong> É a prioridade que um processo
					possui em relação aos demais processos. A regra de prioridade
					definido neste simulador é: <strong>1 (prioridade mais
						alta)</strong> e <strong>10 (prioridade mais baixa).</strong>
				</p>
			</section>
			<section class="clearfix main-info">
				<form id="process-form"	action="<c:url value="/executar-escalonamento-processo"/>" method="post">
					<div id="main-menu" class="clearfix menu">
						<p class="painel-config">
							<strong>Painel de configuração</strong>
						</p>
						<div class="grid_3">
							<strong>Simulação: </strong> 
							<select id="modo" name="modo" tabindex="1">
								<option value="1">Única</option>
								<option value="2">Comparativa</option>
							</select>
						</div>
						<div id="alg1" class="grid_4">
							<strong>Algoritmo 1: </strong> 
							<select name="algs[0]" id="algoritmo1" tabindex="3">
								<option value="">Selecione...</option>
								<c:forEach var="alg" items="${escalonadorProcessoAlgoritmo}">
									<option value="${alg}">${alg.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div id="alg2" class="grid_4">
							<strong>Algoritmo 2: </strong> 
							<select name="algs[1]" id="algoritmo2" tabindex="4">
								<option value="">Selecione...</option>
								<c:forEach var="alg" items="${escalonadorProcessoAlgoritmo}">
									<option value="${alg}">${alg.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="grid_8 menu">
							<strong>Total de processos: </strong> 
							<select id="total" tabindex="2">
								<option value="">Selecione...</option>
								<c:forEach begin="2" end="20" step="1" var="p">
									<option value="${p}">${p} procesos</option>
								</c:forEach>
							</select>
						</div>
						<div id="quantum" class="grid_8 menu">
							<strong>Tempo de corte: </strong> 
							<select name="qt" tabindex="5">
								<option value="">Selecione</option>
								<c:forEach begin="1" end="4" step="1" var="qt1">
									<option value="${qt1}">${qt1} ms</option>
								</c:forEach>
								<c:forEach begin="5" end="100" step="5" var="qt2">
									<option value="${qt2}">${qt2} ms</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div id="process-menu" class="clearfix menu"></div>
					<script id="processTemplate" type="text/x-jquery-tmpl">
							<div id="\${prDivId}" class="processo-input-box">
								<p class="clearfix processo">
									<strong>\${prLabel}: </strong>
									<input type="hidden" name="pr[].id" value="\${prId}">
									<input type="hidden" name="pr[].cor" value="\${prCor}">
								</p>
								<p class="clearfix processo">
									<label class="grid_1" for="\${inputBurst}"><small>Burst: </small></label>
									<input type="text" class="grid_1 burst" name="pr[].burst" id="\${inputBurst}" value="10" maxlength="2">
								</p>
								<p class="clearfix processo">
									<label class="grid_1" for="\${inputChegada}"><small>Chegada: </small></label>
									<input type="text" class="grid_1 chegada" name="pr[].chegada" id="\${inputChegada}" value="0" maxlength="2">
								</p>
								<p class="clearfix processo">
									<label class="grid_1" for="\${inputPrioridade}"><small>Prioridade: </small></label>
									<input type="text" class="grid_1 prioridade" name="pr[].prioridade" id="\${inputPrioridade}" value="1" maxlength="2">
								</p>
							</div>
						</script>
					<strong id="error" class="clearfix error-message"></strong>
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