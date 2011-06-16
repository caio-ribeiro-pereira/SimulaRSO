<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${processoController.idioma}" />
<fmt:bundle basename="idioma">
<html>
<head>
<title><fmt:message key="processo.titulo" /></title>
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
							$('#quantum').val('').change();
						}
						$('#alg2').hide();
						$('#alg2').val('').change();
					}
				}).trigger('change');

				$('#algoritmo1').change(function() {
					if (this.value == 'RR' || $('#algoritmo2').val() == 'RR') {
						$('#quantum').show();
					} else {
						$('#quantum').hide();
						$('#quantum').val('').change();
					}
				}).trigger('change');

				$('#algoritmo2').change(function() {
					if (this.value == 'RR' || $('#algoritmo1').val() == 'RR') {
						$('#quantum').show();
					} else {
						$('#quantum').hide();
						$('#quantum').val('').change();
					}
				}).trigger('change');

				$('#total').change(function() {
					var content = $('#process-menu').empty().hide();
					var total = $('#total').val();
					if (total > 0) {
						var processos = new Array();
						var colors = new Colors();
						for ( var i = 0; i < total; i++) {
							processos.push({inputBurst : 'burst-'+ (i + 1),
											inputChegada : 'chegada-'+ (i + 1),
											inputPrioridade : 'prioridade-'+ (i + 1),
											prCor : colors[i],
											prDivId : 'processo-'+ (i + 1),
											prLabel : '<fmt:message key="processo.label" />: '+ (i + 1),
											prId : (i + 1)
										});
						}
						var template = $('#processTemplate').tmpl(processos);
						content.append(template).fadeIn();
					} 
				}).trigger('change');
				
				$('#rules-dialog').dialog({
					autoOpen : false,
					modal : true,
					width : 960,
					closeOnEscape : true,
					open : function(){ $(this).fadeIn();},
					close : function(){ $(this).fadeOut(); }
				});
				
				$('#help').click(function(){
					$('#rules-dialog').dialog('open');	
				});
			});
</script>
</head>
<body>
	<div class="container_12 main">
		<%@ include file="../templates/header.jsp"%>
		<section class="clearfix">
			<article class="clearfix">
				<h2 class="subtitle"><fmt:message key="processo.titulo" /></h2>
			</article>
			<article class="clearfix main-info">
				<form id="process-form"	action="<c:url value="/executar-escalonamento-processo"/>" method="post">
					<div id="main-menu" class="clearfix menu">
						<p class="painel-config">
							<strong><fmt:message key="misc.painel.titulo" /></strong>
						</p>
						<div class="grid_3">
							<strong><fmt:message key="misc.simulacao.titulo" />: </strong>
							<select id="modo" name="modo" tabindex="1">
								<option value="1"><fmt:message key="misc.simulacao.unica" /></option>
								<option value="2"><fmt:message key="misc.simulacao.comparativa" /></option>
							</select>
						</div>
						<div id="alg1" class="grid_4">
							<strong><fmt:message key="misc.algoritmo" /> 1: </strong> 
							<select name="algs[0]" id="algoritmo1" tabindex="3">
								<option value=""><fmt:message key="misc.selecione" /></option>
								<c:forEach var="alg" items="${processoController.algoritmos}">
									<option value="${alg}">${alg.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div id="alg2" class="grid_4">
							<strong><fmt:message key="misc.algoritmo" /> 2: </strong> 
							<select name="algs[1]" id="algoritmo2" tabindex="4">
								<option value=""><fmt:message key="misc.selecione" /></option>
								<c:forEach var="alg" items="${processoController.algoritmos}">
									<option value="${alg}">${alg.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="grid_8 menu">
							<strong><fmt:message key="processo.total.processos" />:</strong> 
							<select id="total" tabindex="2">
								<option value=""><fmt:message key="misc.selecione" /></option>
								<c:forEach begin="2" end="18" step="1" var="p">
									<option value="${p}">${p}&nbsp;<fmt:message key="processo.label.plural" /></option>
								</c:forEach>
							</select>
						</div>
						<div id="quantum" class="grid_8 menu">
							<strong><fmt:message key="processo.tempo.corte" />:</strong> 
							<select name="qt" tabindex="5">
								<option value=""><fmt:message key="misc.selecione" /></option>
								<c:forEach begin="1" end="4" step="1" var="qt1">
									<option value="${qt1}">${qt1}&nbsp;<fmt:message key="misc.ms"/></option>
								</c:forEach>
								<c:forEach begin="5" end="100" step="5" var="qt2">
									<option value="${qt2}">${qt2}&nbsp;<fmt:message key="misc.ms"/></option>
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
								<label class="grid_1" for="\${inputBurst}"><small><fmt:message key="processo.burst" />: </small></label>
								<input type="text" class="grid_1" name="pr[].burst" id="\${inputBurst}" value="10" maxlength="2">
								<span class="grid_1 info-little">(1 - 99)</span>
							</p>
							<p class="clearfix processo">
								<label class="grid_1" for="\${inputChegada}"><small><fmt:message key="processo.tempo.chegada" />: </small></label>
								<input type="text" class="grid_1" name="pr[].chegada" id="\${inputChegada}" value="0" maxlength="2">
								<span class="grid_1 info-little">(0 - 99)</span>
							</p>
							<p class="clearfix processo">
								<label class="grid_1" for="\${inputPrioridade}"><small><fmt:message key="processo.prioridade" />: </small></label>
								<input type="text" class="grid_1" name="pr[].prioridade" id="\${inputPrioridade}" value="1" maxlength="2">
								<span class="grid_1 info-little">(1 - 10)</span>
							</p>
						</div>
					</script>
					<div class="clearfix execute-panel">
						<p>
							<button id="help" type="button" tabindex="7"><fmt:message key="misc.ajuda" /></button>
							<button id="random" type="button" tabindex="8"><fmt:message key="misc.configuracao.automatica" /></button>
							<button id="execute" type="submit" tabindex="9"><fmt:message key="misc.executar" /></button>
						</p>
					</div>
				</form>
				<div id="rules-dialog" class="clearfix main-info" title="<fmt:message key="misc.regra.titulo" />">
					<p><strong><fmt:message key="misc.regra.titulo" />:</strong></p>
					<p><fmt:message key="processo.regra.msg1" /></p>
					<p><fmt:message key="processo.regra.msg2" /></p>
					<p><fmt:message key="processo.regra.msg3" /></p>
					<p><fmt:message key="processo.regra.msg4" /></p>
					<p><fmt:message key="processo.regra.msg5" /></p>
					<p><strong><fmt:message key="misc.observacoes" />:</strong></p>
					<p><fmt:message key="processo.regra.msg6" /></p>
					<p><fmt:message key="processo.regra.msg7" /></p>
					<p><fmt:message key="processo.regra.msg8" /></p>
					<p><fmt:message key="processo.regra.msg9" /></p>
					<p><fmt:message key="processo.regra.msg10" /></p>
				</div>
			</article>
		</section>
		<%@ include file="../templates/footer.jsp"%>
	</div>
</body>
</html>
</fmt:bundle>