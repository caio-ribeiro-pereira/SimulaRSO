<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${paginacaoController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="paginacao.titulo" /></title>
		<meta name="keywords" content="Simulação de paginação, algoritmos de substituição de páginas" />
		<meta name="keywords" content="string de referência, paginação, FIFO, LRU, OPT" />
		<%@include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<div class="container_12 main">
			<%@ include file="../templates/header.jsp"%>
			<section class="clearfix">
				<article class="clearfix">
					<h2 class="clearfix subtitle"><fmt:message key="paginacao.titulo" /></h2>
				</article>
				<article class="clearfix main-info">
					<form id="pagination-form" action="<c:url value="/executar-paginacao-memoria"/>" method="post">
						<div id="main-menu" class="clearfix menu">
							<c:set var="totalAlgoritmos" value="${fn:length(algs)}" />
							<p class="painel-config">
								<strong><fmt:message key="misc.painel.titulo" /></strong>
							</p>
							<div class="grid_3">
								<strong><fmt:message key="misc.simulacao.titulo" />: </strong>
								<select id="modo" name="modo">
									<option value="1"${modo eq 1 ? ' selected' : ''}><fmt:message key="misc.simulacao.unica" /></option>
									<option value="2"${modo eq 2 ? ' selected' : ''}><fmt:message key="misc.simulacao.comparativa" /></option>
								</select>
							</div>
							<div id="alg1" class="grid_4">
								<strong><fmt:message key="misc.algoritmo" /> 1: </strong>
								<select name="algs[0]" id="algoritmo1">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach var="alg" items="${paginacaoController.algoritmos}">
										<option value="${alg}"${totalAlgoritmos > 0 and algs[0] eq alg ? ' selected' : ''}>${alg.nome}</option>	
									</c:forEach>
								</select>
							</div>
							<div id="alg2" class="grid_4" style="display:none;">
								<strong><fmt:message key="misc.algoritmo" /> 2: </strong>
								<select name="algs[1]" id="algoritmo2">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach var="alg" items="${paginacaoController.algoritmos}">
										<option value="${alg}"${totalAlgoritmos > 1 and algs[1] eq alg ? ' selected' : ''}>${alg.nome}</option>	
									</c:forEach>
								</select>			
							</div>
							<div class="grid_8 menu">
								<strong><fmt:message key="paginacao.total.frames" />: </strong>
								<select name="frames">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach begin="2" end="10" step="1" var="fr">
										<option value="${fr}"${frames eq fr ? ' selected' : ''}>${fr}&nbsp;<fmt:message key="paginacao.frames" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="grid_8 menu">
								<strong><fmt:message key="paginacao.tamanho.string.referencia" />: </strong>
								<select id="stringRefTotal" name="total">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach begin="2" end="30" step="1" var="sr">
										<option value="${sr}"${total eq sr ? ' selected' : ''}>${sr}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="pagination-menu" class="clearfix menu">
							<c:if test="${not empty stringRef}">
								<c:forEach items="${stringRef}" var="strRef" varStatus="status">
									<div class="pagination-input-box">
										<p class="clearfix">
											<strong><fmt:message key="paginacao.palavra" />&nbsp;${status.count}:</strong>
										</p>
										<p class="clearfix">
											<label class="grid_1" for="stringReferencia-${status.count}"><small><fmt:message key="paginacao.valor" />:</small></label>
											<input type="text" class="grid_1 stringReferencia" name="stringRef[]" id="stringReferencia-${status.count}" value="${strRef}" maxlength="1">
											<span class="grid_1 info-little" style="margin-left:-7px;">(0 - 9)</span>
										</p>
									</div>
								</c:forEach>
							</c:if>
						</div>
						<script id="paginationTemplate" type="text/x-jquery-tmpl">
							<div class="pagination-input-box">
								<p class="clearfix">
									<strong>\${labelStringRef}:</strong>
								</p>
								<p class="clearfix">
									<label class="grid_1" for="\${inputStringRef}"><small><fmt:message key="paginacao.valor" />:</small></label>
									<input type="text" class="grid_1 stringReferencia" name="stringRef[]" id="\${inputStringRef}" value="0" maxlength="1">
									<span class="grid_1 info-little" style="margin-left:-7px;">(0 - 9)</span>
								</p>
							</div>
						</script>
						<div class="clearfix execute-panel">
							<p>
								<button id="help" type="button"><fmt:message key="misc.ajuda" /></button>
								<button id="random" type="button"><fmt:message key="misc.configuracao.automatica" /></button>
								<button id="execute" type="submit"><fmt:message key="misc.executar" /></button>
							</p>
						</div>
					</form>
					<div id="rules-dialog" class="clearfix main-info" title="<fmt:message key="misc.regra.titulo" />" style="display:none;">
						<p><strong><fmt:message key="misc.regra.titulo" />:</strong></p>
						<p><fmt:message key="paginacao.regra.msg1" /></p>
						<p><fmt:message key="paginacao.regra.msg2" /></p>
						<p><fmt:message key="paginacao.regra.msg3" /></p>
						<p><fmt:message key="paginacao.regra.msg4" /></p>
						<p><fmt:message key="paginacao.regra.msg5" /></p>
						<p><fmt:message key="paginacao.regra.msg6" /></p>
						<p><strong><fmt:message key="misc.observacoes" />:</strong></p>
						<p><fmt:message key="paginacao.regra.msg7" /></p>
					</div>
				</article>
			</section>
			<%@ include file="../templates/footer.jsp"%>
		</div>
		<script type="text/javascript">
			head.ready(function(){
				
				$('button').button({icons : {primary : 'ui-icon-gear'}}).next().button({icons : {primary : 'ui-icon-shuffle'}});
				
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
					if (this.value == 2) {
						$('#alg2').show();
					} else {
						$('#algoritmo2').val('').change();
						$('#alg2').hide();
					}
				}).trigger('change');
				
				$('#stringRefTotal').change(function(){
					var content = $('#pagination-menu').empty().hide();
					var total = $('#stringRefTotal').val();
					if(total > 0){
						var paginator = new Array();
						for(var i = 0; i < total; i++){
							paginator.push({inputStringRef:'stringReferencia-'+(i+1), 
											labelStringRef:'<fmt:message key="paginacao.palavra" /> '+(i+1)});
						}
						var template = $('#paginationTemplate').tmpl(paginator);
						content.append(template).fadeIn();
						$('input[type="text"].stringReferencia').onlyNumeric();
					}
				});
				
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
	</body>
</html>
</fmt:bundle>