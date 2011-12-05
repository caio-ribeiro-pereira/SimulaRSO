<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${discoController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="disco.titulo" /></title>
		<%@ include file="../templates/script-loader.jsp"%>
	</head>
	<body>
		<%@ include file="../templates/menu.jsp"%>
		<div class="container">
			<%@ include file="../templates/header.jsp"%>
			<hr>
			<section>
				<h2><fmt:message key="disco.titulo" /></h2>
				<h5 class="center"><fmt:message key="misc.painel.titulo" /></h5>
				<form id="disco-form" action="<c:url value="/executar-escalonamento-disco" />" method="post">
					<article>
						<div class="row">
							<c:set var="totalAlgoritmos" value="${fn:length(algDisco)}" />
							<div class="span5">
								<label class="span3" for="modo"><fmt:message key="misc.simulacao.titulo" />&nbsp;:&nbsp;&nbsp;</label>
								<select class="span2 fluid" id="modo" name="modo">
									<option value="1"${modo eq 1 ? ' selected' : ''}><fmt:message key="misc.simulacao.unica" /></option>
									<option value="2"${modo eq 2 ? ' selected' : ''}><fmt:message key="misc.simulacao.comparativa" /></option>
								</select>
							</div>
							<div class="span5">
							    <label class="span3" for="totalRequisicao"><fmt:message key="disco.total.requisicoes" />&nbsp;:&nbsp;&nbsp;</label>
								<select class="span2 fluid" id="totalRequisicao" name="total">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach var="p" begin="2" end="30" step="1">
										<option value="${p}"${total eq p ? ' selected' : ''}>${p}&nbsp;<fmt:message key="disco.requisicoes" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="span5">
								<label class="span4" for="cabeca"><fmt:message key="disco.cilindro.cabeca" />&nbsp;:&nbsp;&nbsp;</label>
								<input class="span1 fluid" type="text" id="cabeca" name="cabeca.cilindro" maxlength="2" value="${not empty cabeca ? cabeca.cilindro : 0}">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="span5" id="alg1">
								<label class="span3" for="algoritmo1"><fmt:message key="misc.algoritmo" />&nbsp;1:&nbsp;&nbsp;</label>
								<select class="span2 fluid" id="algoritmo1" name="algDisco[0]">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach items="${discoController.algoritmos}" var="alg">
										<option value="${alg}"${totalAlgoritmos > 0 and algDisco[0] eq alg ? ' selected' : ''}>${alg.nome}</option>		
									</c:forEach>
								</select> 
							</div>
							<div class="span5 hide" id="alg2">
								<label class="span3" for="algoritmo2"><fmt:message key="misc.algoritmo" />&nbsp;2:&nbsp;&nbsp;</label>	
								<select class="span2 fluid" id="algoritmo2" name="algDisco[1]">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach items="${discoController.algoritmos}" var="alg">
										<option value="${alg}"${totalAlgoritmos > 1 and algDisco[1] eq alg ? ' selected' : ''}>${alg.nome}</option>		
									</c:forEach>
								</select>
							</div>
						</div>
						<hr>
						<div id="disco-menu" class="row center">
							<c:if test="${not empty requisicoes}">
								<c:forEach items="${requisicoes}" var="req" varStatus="status">
									<div class="span3 item">
										<p>
											<strong><fmt:message key="disco.cilindro" />&nbsp;${status.count}:</strong>
										</p>
										<p>
											<label class="span1" for="cilindro-${status.count}"><fmt:message key="disco.setor" />:&nbsp;</label>
											<input type="text" class="span1 cilindro" name="requisicoes[].cilindro" id="cilindro-${status.count}" maxlength="2" value="${req.cilindro}" placeholder="(1 - 99)">
										</p>
									</div>
								</c:forEach>
							</c:if>
						</div>
						<hr>
						<script id="discoTemplate" type="text/x-jquery-tmpl">
							<div class="span3 item">
								<p>
									<strong><fmt:message key="disco.cilindro" />&nbsp;\${cilindroLabelId}:</strong>
								</p>
								<p>
									<label class="span1" for="\${cilindroId}"><fmt:message key="disco.setor" />:&nbsp;</label>
									<input type="text" class="span1 cilindro" name="requisicoes[].cilindro" id="\${cilindroId}" maxlength="2" value="1" placeholder="(1 - 99)">
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
					<p><strong><fmt:message key="misc.observacoes" />:</strong></p>
					<p class="justify"><fmt:message key="disco.regra.msg1" /></p>
					<p class="justify"><fmt:message key="disco.regra.msg2" /></p>
					<p class="justify"><fmt:message key="disco.regra.msg3" /></p>
					<p class="justify"><fmt:message key="disco.regra.msg4" /></p>
					<p class="justify"><fmt:message key="disco.regra.msg5" /></p>
					<p class="justify"><fmt:message key="disco.regra.msg6" /></p>
					<p><strong><fmt:message key="misc.observacoes" />:</strong></p>
					<p class="justify"><fmt:message key="disco.regra.msg7" /></p>
					<p class="justify"><fmt:message key="disco.regra.msg8" /></p>
				</div>
				<div class="modal-footer right">
					<button id="close-dialog" class="btn primary">ok</button>
				</div>	
			</div>
			<%@ include file="../templates/footer.jsp"%>
		</div>
		<script type="text/javascript">
			head.js('<c:url value="/resources/js/form/disco.js" />');
		</script>
	</body>
</html>
</fmt:bundle>