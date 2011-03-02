<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
	<title>SimulaEDP - Escalonamento de Processos</title>
</head>	
<body>
	<h2 class="clearfix">Escalonamento de Processos</h2>
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
		<!--<p id="line-1">
			<div id="processo-1" class="input-box">
				<p><small>Processo 1</small></p>
				<p>
					<label class="grid_1" for="burst"><small>Burst: </small></label>
					<select class="grid_1" name="burst[0]" id="burst">
						<option>1</option>
						<option>2</option>
						<option>3</option>
					</select>
				</p>
				<p>
					<label class="grid_1" for="chegada"><small>Chegada: </small></label>
					<select class="grid_1" name="chegada[0]" id="chegada">
						<option>1</option>
						<option>2</option>
						<option>3</option>
					</select>
				</p>
				<p>
					<label class="grid_1" for="prioridade"><small>Prioridade: </small></label>
					<select class="grid_1" name="prioridade[0]" id="prioridade">
						<option>1</option>
						<option>2</option>
						<option>3</option>
					</select>
				</p>
			</div>
		</p>-->
	</div>
	<canvas id="processo-canvas" class="clearfix" width="940" height="400"></canvas>
	<script type="text/javascript" src="<c:url value="/resources/js/escalonameno-processo-canvas.js" />"></script>
	<script type="text/javascript">
		$(function(){
			
			$('#preparar').click(function(){
				var bursts = [5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100];
				var chegadas = [0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100];
				var prioridades = [0,1,2,3,4,5,6,7,8,9,10];
				
				$('#process-menu').empty();
				var total = $('#total').val();
				var limit = 5;
				var line = 0;
				for(var i = 0; i < total; i++){
					if(i % limit == 0){
						$('#process-menu').append('<p id="line-'+(++line)+'">');
					}
					var processoID = 'processo-'+(i+1);
					var processoLabel = 'Processo '+(i+1)+' :';
					// Append da div + processo label
					$('#line-'+line).html('<div id="'+processoID+'" class="input-box"></div>');
					$('#'+processoID).html('<p><small>'+processoLabel+'</small></p>');
					// Append label burst + select burst
					$('#'+processoID).html('<label for="burst"><small>Burst: </small></label>');
					$('#'+processoID).html('<select class="grid_1" name="burst['+i+']" id="select-burst">');
					for(var b = 0; b < bursts.length; b++){
						$('#select-burst').html('<option value="'+bursts[b]+'">'+bursts[b]+' ms</option>');
					}
					// Append label chegada + select chegada
					$('#'+processoID).html('<label for="chegada"><small>Chegada: </small></label>');
					$('#'+processoID).html('<select class="grid_1" name="chegada['+i+']" id="select-chegada">');
					for(var b = 0; b < chegadas.length; b++){
						$('#select-chegada').html('<option value="'+chegadas[b]+'">'+chegadas[b]+' ms</option>');
					}
					// Append label prioridade + select prioridade
					$('#'+processoID).html('<label for="prioridade"><small>Prioridade: </small></label>');
					$('#'+processoID).html('<select class="grid_1" name="prioridade['+i+']" id="select-prioridade">');
					for(var b = 0; b < prioridades.length; b++){
						$('#select-prioridade').html('<option value="'+prioridades[b]+'">'+prioridades[b]+'</option>');
					}
				}
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