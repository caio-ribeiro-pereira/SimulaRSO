<!DOCTYPE html>
<head>
	<title>SimulaEDP - Escalonamento de Processos</title>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-tmpl.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/escalonameno-processo-canvas.js" />"></script>
</head>	
<body>
	<h1 class="clearfix">Escalonamento de Processos</h1>
	<form id="process-form">
		<div id="main-menu" class="clearfix menu">
			<p>
				<strong>Modo: </strong>
				<select id="modo">
					<c:forEach var="modoSimulacao" items="${modosDeSimulacao}">
						<option value="${modoSimulacao}">${modoSimulacao.modo}</option>
					</c:forEach>
				</select>
				<strong>Total de processos: </strong>
				<select id="total">
					<c:forEach begin="2" end="10" step="1" var="p">
						<option value="${p}">${p}</option>
					</c:forEach>
				</select>
				<button id="preparar">Preparar processos</button>
			</p>
		</div>
		<div id="algoritmo-menu" class="clearfix menu">
			<strong>Algoritmo 1: </strong>
			<select name="alg[0]" id="algoritmo">
				<c:forEach var="algoritmo" items="${algoritmosDeProcesso}">
					<option value="${algoritmo}">${algoritmo.nome}</option>
				</c:forEach>
			</select>
			<strong>Algoritmo 2: </strong>
			<select name="alg[1]" id="algoritmo">
				<c:forEach var="algoritmo" items="${algoritmosDeProcesso}">
					<option value="${algoritmo}">${algoritmo.nome}</option>
				</c:forEach>
			</select>
		</div>
		<div id="process-menu" class="clearfix menu">
			<div id="processo-1" class="input-box">
				<p>
					<small>Processo 1: </small>
					<input type="hidden" name="pr[0].id" value="1">
				</p>
				<p>
					<label class="grid_1" for="burst-1"><small>Burst: </small></label>
					<input type="text" class="grid_1" name="pr[0].burst" id="burst-1" maxlength="2">
				</p>
				<p>
					<label class="grid_1" for="chegada-1"><small>Chegada: </small></label>
					<input type="text" class="grid_1" name="pr[0].chegada" id="chegada-1" maxlength="2">
				</p>
				<p>
					<label class="grid_1" for="prioridade-1"><small>Prioridade: </small></label>
					<input type="text" class="grid_1" name="pr[0].prioridade" id="prioridade-1" maxlength="2">
				</p>
			</div>
			<div id="processo-2" class="input-box">
				<p>
					<small>Processo 2: </small>
					<input type="hidden" name="pr[1].id" value="2">
				</p>
				<p>
					<label class="grid_1" for="burst-2"><small>Burst: </small></label>
					<input type="text" class="grid_1" name="pr[1].burst" id="burst-2" maxlength="2">
				</p>
				<p>
					<label class="grid_1" for="chegada-2"><small>Chegada: </small></label>
					<input type="text" class="grid_1" name="pr[1].chegada" id="chegada-2" maxlength="2">
				</p>
				<p>
					<label class="grid_1" for="prioridade-2"><small>Prioridade: </small></label>
					<input type="text" class="grid_1" name="pr[1].prioridade" id="prioridade-2" maxlength="2">
				</p>
			</div>
			<button id="executar">Executar</button>
		</div>
	</form>
	<div class="graphic-panel clearfix">
		<canvas id="processo-canvas" class="clearfix" width="1900" height="400"></canvas>
	</div>
	<script type="text/javascript">
		$(function(){
			$('button#executar').click(function(){
				$.getJSON('<c:url value="/executar-escalonamento-processo"/>',
						$('form#process-form').serialize(),
						function(json) {
					   		alert(json.resultadoMap);
						}
				);
			});
			
			
			/*$('#preparar').click(function(){
				var content = $('#process-menu').empty().hide();
				var total = $('#total').val();
				var processos = new Array();
				for(var i = 0; i < total; i++){
				    processos.push(
				    	{processoId : "processo-"+i,
						 burstId : "burst-"+i,
						 chegadaId : "chegada-"+i,
						 prioridadeId : "prioridade-"+i,
						 processoName : "Processo: "+i,
						 burstName : "processo["+i+"].burst", 
						 chegadaName : "processo["+i+"].tempoChegada", 
						 prioridadeName : "processo["+i+"].prioridade"}
				    );
				}
				var template = $('#processTemplate').tmpl(processos);
				content.append(template).show();
			});
			
			var procCanvas = new ProcessoCanvas();
			procCanvas.setup();
			procCanvas.timeLine();
			procCanvas.background();
			procCanvas.resultado();
			*/
		});
	</script>
</body>