<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${processoController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="processo.titulo" /></title>
		<%@include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<%@ include file="../templates/menu.jsp"%>
		<div class="container">
			<%@ include file="../templates/header.jsp"%>
			<h2><fmt:message key="processo.titulo" /></h2>
			<section>
				<h6><fmt:message key="misc.painel.titulo" /></h6>
				<form id="process-form"	action="<c:url value="/executar-escalonamento-processo"/>" method="post">
					<article>
						<div class="row">
							<c:set var="totalAlgoritmos" value="${fn:length(algs)}" />
							<div class="span5">
								<label class="span3" for="modo"><fmt:message key="misc.simulacao.titulo" />:&nbsp;&nbsp;</label>
								<select class="span2 fluid" id="modo" name="modo">
									<option value="1"${modo eq 1 ? ' selected' : ''}><fmt:message key="misc.simulacao.unica" /></option>
									<option value="2"${modo eq 2 ? ' selected' : ''}><fmt:message key="misc.simulacao.comparativa" /></option>
								</select>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="span5" id="alg1">
								<label class="span3" for="algoritmo1"><fmt:message key="misc.algoritmo" /> 1:&nbsp;&nbsp;</label> 
								<select class="span2 fluid" name="algs[0]" id="algoritmo1">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach var="alg" items="${processoController.algoritmos}">
										<option value="${alg}"${totalAlgoritmos > 0 and algs[0] eq alg ? ' selected' : ''}>${alg.nome}</option>
									</c:forEach>
								</select>
							</div>
							<div class="span5 hide" id="alg2">
								<label class="span3" for="algoritmo2"><fmt:message key="misc.algoritmo" /> 2:&nbsp;&nbsp;</label> 
								<select class="span2 fluid" name="algs[1]" id="algoritmo2">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach var="alg" items="${processoController.algoritmos}">
										<option value="${alg}"${totalAlgoritmos > 1 and algs[1] eq alg ? ' selected' : ''}>${alg.nome}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="span5">
								<label class="span3" for="total"><fmt:message key="processo.total.processos" />:&nbsp;&nbsp;</label> 
								<select class="span2" id="total" name="total">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach begin="2" end="20" step="1" var="p">
										<option value="${p}"${total eq p ? ' selected' : ''}>${p}&nbsp;<fmt:message key="processo.label.plural" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="span5">
								<label class="span3" for="contexto"><fmt:message key="processo.tempo.contexto" />:&nbsp;&nbsp;</label>
								<select class="span2" name="contexto" id="contexto">
									<c:forEach begin="0" end="10" step="1" var="ctx">
										<option value="${ctx}"${contexto eq ctx ? ' selected' : ''}>${ctx}&nbsp;<fmt:message key="misc.ms"/></option>
									</c:forEach>
								</select>
							</div>
							<div class="span5 hide" id="quantum">
								<label class="span3" for="qt"><fmt:message key="processo.tempo.corte" />:&nbsp;&nbsp;</label>
								<select class="span2" name="qt" id="qt">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach begin="1" end="4" step="1" var="qt1">
										<option value="${qt1}"${qt eq qt1 ? ' selected' : ''}>${qt1}&nbsp;<fmt:message key="misc.ms"/></option>
									</c:forEach>
									<c:forEach begin="5" end="100" step="5" var="qt2">
										<option value="${qt2}"${qt eq qt2 ? ' selected' : ''}>${qt2}&nbsp;<fmt:message key="misc.ms"/></option>
									</c:forEach>
								</select>
							</div>
						</div>
						<hr>
						<div id="process-menu" class="row center">
							<c:if test="${not empty pr}">
								<c:forEach var="processo" items="${pr}" varStatus="status">
									<div id="processo-${status.count}" class="span3 process-item">
										<p>
											<strong><fmt:message key="processo.label" />&nbsp;${status.count}:</strong>
											<input type="hidden" name="pr[].id" value="${processo.id}">
											<input type="hidden" name="pr[].cor" value="${processo.cor}">
										</p>
										<p>
											<label class="span1" for="burst-${status.count}"><fmt:message key="processo.burst" />:&nbsp;</label>
											<input type="text" class="span1 burst" name="pr[].burst" id="burst-${status.count}" value="${processo.burst}" maxlength="2" placeholder="(1 - 99)">
										</p>
										<p>
											<label class="span1" for="chegada-${status.count}"><fmt:message key="processo.tempo.chegada" />:&nbsp;</label>
											<input type="text" class="span1 chegada" name="pr[].chegada" id="chegada-${status.count}" value="${processo.chegada}" maxlength="2" placeholder="(0 - 99)">
										</p>
										<p>
											<label class="span1" for="prioridade-${status.count}"><fmt:message key="processo.prioridade" />:&nbsp;</label>
											<input type="text" class="span1 prioridade" name="pr[].prioridade" id="prioridade-${status.count}" value="${processo.prioridade}" maxlength="2" placeholder="(1 - 10)">
										</p>
									</div>
								</c:forEach>
							</c:if>
						</div>
						<script id="processTemplate" type="text/x-jquery-tmpl">
							<div id="\${prDivId}" class="span3 process-item">
								<p>
									<strong><fmt:message key="processo.label" />&nbsp;\${prId}</strong>
									<input type="hidden" name="pr[].id" value="\${prId}">
									<input type="hidden" name="pr[].cor" value="\${prCor}">
								</p>
								<p>
									<label class="span1" for="\${inputBurst}"><fmt:message key="processo.burst" />:&nbsp;</label>
									<input type="text" class="span1 burst" name="pr[].burst" id="\${inputBurst}" value="10" maxlength="2" placeholder="(1 - 99)">
								</p>
								<p>
									<label class="span1" for="\${inputChegada}"><fmt:message key="processo.tempo.chegada" />:&nbsp;</label>
									<input type="text" class="span1 chegada" name="pr[].chegada" id="\${inputChegada}" value="0" maxlength="2" placeholder="(0 - 99)">
								</p>
								<p>
									<label class="span1" for="\${inputPrioridade}"><fmt:message key="processo.prioridade" />:&nbsp;</label>
									<input type="text" class="span1 prioridade" name="pr[].prioridade" id="\${inputPrioridade}" value="1" maxlength="2" placeholder="(1 - 10)">
								</p>
							</div>
						</script>
						<div class="actions right row">
							<button class="btn info" data-controls-modal="rules-dialog" data-backdrop="true" data-keyboard="true" type="button"><fmt:message key="misc.ajuda" /></button>
							<button class="btn" id="random" type="button"><fmt:message key="misc.configuracao.automatica" /></button>
							<button class="btn primary" id="execute" type="submit"><fmt:message key="misc.executar" /></button>
						</div>
					</article>
				</form>
			</section>
			<div id="rules-dialog" class="hide" title="<fmt:message key="misc.regra.titulo" />">
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
			<%@ include file="../templates/footer.jsp"%>
		</div>
		<script type="text/javascript">
			head.js('<c:url value="/resources/js/canvas/charts.js" />',
					'<c:url value="/resources/js/jquery-plugins.min.js" />');
			head.ready(function() {
					var MAXBURST = 98;
					var MAXCHEGADA = 100;
					var MAXPRIORIDADE = 11;
	
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
								$('#quantum').val('').change();
								$('#quantum').hide();
							}
							$('#algoritmo2').val('').change();
							$('#alg2').hide();
						}
					}).trigger('change');
	
					$('#algoritmo1').change(function() {
						if (this.value == 'RR' || $('#algoritmo2').val() == 'RR') {
							$('#quantum').show();
						} else {
							$('#quantum').val('').change();
							$('#quantum').hide();
						}
					}).trigger('change');
	
					$('#algoritmo2').change(function() {
						if (this.value == 'RR' || $('#algoritmo1').val() == 'RR') {
							$('#quantum').show();
						} else {
							$('#quantum').val('').change();
							$('#quantum').hide();
						}
					}).trigger('change');
	
					$('#total').change(function() {
						var content = $('#process-menu').empty().hide();
						var total = $('#total').val();
						if (total > 0) {
							var processos = new Array();
							var cores =  simulaRSO.cores();
							for (var i = 0; i < total; i++) {
								processos.push({inputBurst : 'burst-'+ (i + 1),
												inputChegada : 'chegada-'+ (i + 1),
												inputPrioridade : 'prioridade-'+ (i + 1),
												prCor : cores[i],
												prDivId : 'processo-'+ (i + 1),
												prId : (i + 1)
											});
							}
							var template = $('#processTemplate').tmpl(processos);
							content.append(template).fadeIn();
							$('input[type="text"].burst').onlyNumeric();
							$('input[type="text"].chegada').onlyNumeric();
							$('input[type="text"].prioridade').onlyNumeric();
						} 
					});
					
					$('#rules-dialog').modal();
				});
		</script>
	</body>
</html>
</fmt:bundle>