<!DOCTYPE html>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${discoController.idioma}" />
<fmt:bundle basename="idioma">
<html>
	<head>
		<title><fmt:message key="disco.titulo" /></title>
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
						content.append(template).fadeIn();
						$('.cilindro-requisicao').spinner({ min: 1, max: 99, showOn: 'always' }).onlyNumeric();
					}else{
						$('button').removeAttr('disabled');
						$('#disco-menu').html('<strong class="clearfix info-message"><fmt:message key="disco.erro" /></strong>').show();
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
						$('#error').text('<fmt:message key="misc.algoritmo.erro" />').show();
					}
				}).trigger('change');
				
				$('#algoritmo2').change(function(){
					$('#execute').removeAttr('disabled');
					$('#error').text('');
					if(this.value == $('#algoritmo1').val() && this.value != ''){
						$('#execute').attr('disabled','disabled');
						$('#error').text('<fmt:message key="misc.algoritmo.erro" />').show();
					}
				}).trigger('change');
					
			});
		</script>
	</head>
	<body>
		<div class="container_12 main">
			<%@ include file="../templates/header.jsp"%>
			<section class="clearfix">
				<article class="clearfix">
					<h2 class="clearfix subtitle"><fmt:message key="disco.titulo" /></h2>
				</article>
				<article class="clearfix main-info">
					<p><strong><fmt:message key="misc.observacoes" />:</strong></p>
					<p><fmt:message key="disco.regra.msg1" /></p>
					<p><fmt:message key="disco.regra.msg2" /></p>
					<p><fmt:message key="disco.regra.msg3" /></p>
					<p><fmt:message key="disco.regra.msg4" /></p>
					<p><fmt:message key="disco.regra.msg5" /></p>
					<p><fmt:message key="disco.regra.msg6" /></p>
					<p><strong><fmt:message key="misc.observacoes" />:</strong></p>
					<p><fmt:message key="disco.regra.msg7" /></p>
					<p><fmt:message key="disco.regra.msg8" /></p>
				</article>
				<article class="clearfix main-info">
					<form action="<c:url value="/executar-escalonamento-disco" />" method="post" id="disco-form">
						<div id="main-menu" class="clearfix menu">
							<p class="painel-config">
								<strong><fmt:message key="misc.painel.titulo" /></strong>
							</p>
							<div class="grid_3">
								<label><fmt:message key="misc.simulacao.titulo" /></label>
								<select id="modo" name="modo" tabindex="1">
									<option value="1"><fmt:message key="misc.simulacao.unica" /></option>
									<option value="2"><fmt:message key="misc.simulacao.comparativa" /></option>
								</select>
							</div>	
							<div id="alg1" class="grid_4">
								<label><fmt:message key="misc.algoritmo" /> 1:</label>
								<select id="algoritmo1" name="algDisco[0]" tabindex="2">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach items="${algoritmoDisco}" var="alg">
										<option value="${alg}">${alg.nome}</option>		
									</c:forEach>
								</select> 
							</div>
							<div id="alg2" class="grid_4">
								<label><fmt:message key="misc.algoritmo" /> 2:</label>	
								<select id="algoritmo2" name="algDisco[1]" tabindex="3">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach items="${algoritmoDisco}" var="alg">
										<option value="${alg}">${alg.nome}</option>		
									</c:forEach>
								</select>
							</div>
							<div class="grid_8 menu">
							    <label><fmt:message key="disco.total.requisicoes" />:</label>
								<select id="totalRequisicao" tabindex="4">
									<option value=""><fmt:message key="misc.selecione" /></option>
									<c:forEach var="p" begin="2" end="30" step="1">
										<option value="${p}">${p}&nbsp;<fmt:message key="disco.requisicoes" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="grid_8 cilindro-cabeca menu">
								<label for="cabeca"><fmt:message key="disco.cilindro.cabeca" />:</label>
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
								<button id="random" type="button" tabindex="8"><fmt:message key="misc.configuracao.automatica" /></button>
								<button id="execute" type="submit" tabindex="7"><fmt:message key="misc.executar" /></button>
							</p>
						</div>
					</form>
				</article>
			</section>
			<%@ include file="../templates/footer.jsp"%>
		</div>
	</body>
</html>
</fmt:bundle>