<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${paginacaoController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="paginacao.titulo" /></title>
		<%@include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<%@ include file="../templates/menu.jsp"%>
		<div class="container">
			<%@ include file="../templates/header.jsp"%>
			<hr>
			<section>
				<h2><fmt:message key="paginacao.titulo" /></h2>
				<h5 class="center"><fmt:message key="misc.painel.titulo" /></h5>
				<form id="pagination-form" action="<c:url value="/executar-paginacao-memoria"/>" method="post">
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
							<div class="span5">
								<label class="span3" for="frames"><fmt:message key="paginacao.total.frames" />:&nbsp;&nbsp;</label>
								<select class="span2 fluid" name="frames" id="frames">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach begin="2" end="10" step="1" var="fr">
										<option value="${fr}"${frames eq fr ? ' selected' : ''}>${fr}&nbsp;<fmt:message key="paginacao.frames" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="span5">
								<label class="span3" for="stringRefTotal"><fmt:message key="paginacao.string.referencia" />:&nbsp;&nbsp;</label>
								<select class="span2 fluid" id="stringRefTotal" name="total">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach begin="2" end="30" step="1" var="sr">
										<option value="${sr}"${total eq sr ? ' selected' : ''}>${sr}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="span5" id="alg1">
								<label class="span3" for="algoritmo1"><fmt:message key="misc.algoritmo" />&nbsp;1:&nbsp;&nbsp;</label>
								<select class="span2 fluid" name="algs[0]" id="algoritmo1">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach var="alg" items="${paginacaoController.algoritmos}">
										<option value="${alg}"${totalAlgoritmos > 0 and algs[0] eq alg ? ' selected' : ''}>${alg.nome}</option>	
									</c:forEach>
								</select>
							</div>
							<div class="span5 hide" id="alg2">
								<label class="span3" for="algoritmo2"><fmt:message key="misc.algoritmo" />&nbsp;2:&nbsp;&nbsp;</label>
								<select class="span2 fluid" name="algs[1]" id="algoritmo2">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach var="alg" items="${paginacaoController.algoritmos}">
										<option value="${alg}"${totalAlgoritmos > 1 and algs[1] eq alg ? ' selected' : ''}>${alg.nome}</option>	
									</c:forEach>
								</select>			
							</div>
						</div>
						<hr>
						<div id="pagination-menu" class="row center">
							<c:if test="${not empty stringRef}">
								<c:forEach items="${stringRef}" var="strRef" varStatus="status">
									<div class="span3 item">
										<p>
											<strong><fmt:message key="paginacao.palavra" />&nbsp;${status.count}:</strong>
										</p>
										<p>
											<label class="span1" for="stringReferencia-${status.count}"><fmt:message key="paginacao.valor" />:&nbsp;&nbsp;</label>
											<input type="text" class="span1 stringReferencia" name="stringRef[]" id="stringReferencia-${status.count}" value="${strRef}" maxlength="1" placeholder="(0 - 9)">
										</p>
									</div>
								</c:forEach>
							</c:if>
						</div>
						<script id="paginationTemplate" type="text/x-jquery-tmpl">
							<div class="span3 item">
								<p>
									<strong><fmt:message key="paginacao.palavra" />&nbsp;\${stringRefId}:</strong>
								</p>
								<p>
									<label class="span1" for="\${inputStringRef}"><fmt:message key="paginacao.valor" />:&nbsp;&nbsp;</label>
									<input type="text" class="span1 stringReferencia" name="stringRef[]" id="\${inputStringRef}" value="0" maxlength="1" placeholder="(0 - 9)">
								</p>
							</div>
						</script>
						<div class="right row">
							<button class="btn info" data-controls-modal="rules-dialog" data-backdrop="true" data-keyboard="true" type="button"><fmt:message key="misc.ajuda" /></button>
							<button class="btn" id="random" type="button"><fmt:message key="misc.configuracao.automatica" /></button>
							<button class="btn primary" id="execute" type="submit"><fmt:message key="misc.executar" /></button>
						</div>
					</article>
				</form>
			</section>
			<div id="rules-dialog" class="modal fade hide" title="<fmt:message key="misc.regra.titulo" />">
				<div class="modal-header">
	            	<a href="#" class="close">x</a>
	              	<h3><fmt:message key="misc.regra.titulo" /></h3>
	            </div>
	            <div class="modal-body">
					<p><strong><fmt:message key="misc.regra.titulo" />:</strong></p>
					<p class="justify"><fmt:message key="paginacao.regra.msg1" /></p>
					<p class="justify"><fmt:message key="paginacao.regra.msg2" /></p>
					<p class="justify"><fmt:message key="paginacao.regra.msg3" /></p>
					<p class="justify"><fmt:message key="paginacao.regra.msg4" /></p>
					<p class="justify"><fmt:message key="paginacao.regra.msg5" /></p>
					<p class="justify"><fmt:message key="paginacao.regra.msg6" /></p>
					<p><strong><fmt:message key="misc.observacoes" />:</strong></p>
					<p class="justify"><fmt:message key="paginacao.regra.msg7" /></p>
				</div>
				<div class="modal-footer right">
					<button id="close-dialog" class="btn primary">ok</button>
				</div>
			</div>
			<%@ include file="../templates/footer.jsp"%>
		</div>
		<script type="text/javascript">
			head.ready(function(){
				
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
						$('#alg2').fadeIn();
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
							paginator.push({inputStringRef: 'stringReferencia-'+(i+1), 
											stringRefId: (i+1) });
						}
						var template = $('#paginationTemplate').tmpl(paginator);
						content.append(template).fadeIn();
						$('input[type="text"].stringReferencia').onlyNumeric();
					}
				});
				
				$('#rules-dialog').modal({
					closeOnEscape: true
				});
				$('#close-dialog').click(function(){
					$('#rules-dialog').modal('hide');
				});
				
			});
		</script>
	</body>
</html>
</fmt:bundle>