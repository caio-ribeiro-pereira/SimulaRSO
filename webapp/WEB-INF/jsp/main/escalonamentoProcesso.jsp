<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<head>
	<title>SimulaEDP - Escalonamento de Processos</title>
</head>	
<body>
	<h1 class="clearfix">Escalonamento de Processos</h1>
	<div id="main-menu" class="clearfix menu">
		<p>
			<strong>Modo: </strong>
			<select name="modo" id="modo">
				<c:forEach var="modoSimulacao" items="${modosDeSimulacao}">
					<option value="${modoSimulacao}">${modoSimulacao.modo}</option>
				</c:forEach>
			</select>
			<strong>Algoritmo: </strong>
			<select name="algoritmo" id="algoritmo">
				<c:forEach var="algoritmo" items="${algoritmosDeProcesso}">
					<option value="${algoritmo}">${algoritmo.nome}</option>
				</c:forEach>
			</select>
			<strong>Total de processos: </strong>
			<select name="total" id="total">
				<c:forEach begin="2" end="10" step="1" var="p">
					<option value="${p}">${p}</option>
				</c:forEach>
			</select>
			<button id="preparar">Preparar processos</button>
		</p>
	</div>
	<div id="process-menu" class="clearfix menu">
		<script id="processoTemplate" type="text/x-jquery-tmpl">		
			<div id="processo-${id}" class="input-box">
				<p><small>Processo ${id}</small></p>
				<p>
					<label class="grid_1" for="burst-${id}"><small>Burst: </small></label>
					<input type="text" class="grid_1" name="${processoBurst}" id="burst-${id}" maxlength="2">
				</p>
				<p>
					<label class="grid_1" for="chegada-${id}"><small>Chegada: </small></label>
					<input type="text" class="grid_1" name="${processoTempoChegada}" id="chegada-${id}" maxlength="2">					
				</p>
				<p>
					<label class="grid_1" for="prioridade-${id}"><small>Prioridade: </small></label>
					<input type="text" class="grid_1" name="${processoPrioridade}" id="prioridade-${id}" maxlength="2">
				</p>
			</div>
		</script>
	</div>
	<div class="graphic-panel clearfix">
		<canvas id="processo-canvas" class="clearfix" width="1900" height="400"></canvas>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/escalonameno-processo-canvas.js" />"></script>
	<script type="text/javascript">
		$(function(){
			$('#preparar').click(function(){
				var content = $('#process-menu').empty().hide();
				var total = $('#total').val();
				var processos = [];
				for(var i = 0; i < total; i++){
				    processos.push({
				        id : (i+1),
				        processoBurst : 'processo['+i+'].burst',
				        processoTempoChegada : 'processo['+i+'].tempoChegada',
				        processoPrioridade : 'processo['+i+'].prioridade'
				    });
				}
				var template = $("script#processoTemplate").tmpl(processos);
				content.append(template).fadeIn();
			});
			/*
			var procCanvas = new ProcessoCanvas();
			procCanvas.setup();
			procCanvas.timeLine();
			procCanvas.background();
			procCanvas.resultado();
			*/
		});
	</script>
</body>