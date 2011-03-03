<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<script id="videosTemplate" type="text/x-jquery-tmpl"> 		
			<div id="processo-1" class="input-box">
				<p><small>Processo ${id}</small></p>
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
			<br>
		</script>
	</div>
	<div class="graphic-panel clearfix">
		<canvas id="processo-canvas" class="clearfix" width="1900" height="400"></canvas>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/escalonameno-processo-canvas.js" />"></script>
	<script type="text/javascript">
		$(function(){
			
			$('#preparar').click(function(){
				$('#process-menu').empty();
				var bursts = [5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100];
				var chegadas = [0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100];
				var prioridades = [0,1,2,3,4,5,6,7,8,9,10];
				var total = $('#total').val();
				var limit = 5;
				var line = 0;
				for(var i = 0; i < total; i++){
					if(i % limit == 0){
						$('#process-menu').append('<br>');
						++line;
					}
					var processoID = 'processo-'+(i+1);
					var processoLabel = 'Processo '+(i+1)+' :';
					
					// Append da div + processo label
					var divProcesso = $('<div />', {
						id : processoID,
						'class' : 'input-box'
					});
					
					divProcesso.append($('<small />').text(processoLabel));
					divProcesso.append('<label for="burst-'+processoID+'" /a').text('Burst: ');
					divProcesso.append('<select class="grid_1" name="burst['+i+']" id="burst-'+processoID+'" />');
					for(var b = 0; b < bursts.length; b++){
						$('select#burst-'+processoID).append('<option />').val(bursts[b]).text(bursts[b]+' ms');
					}
					// Append label burst + select burst
					divProcesso.append('<label for="chegada-'+processoID+'" />').text('Chegada: ');
					divProcesso.append('<select class="grid_1" name="chegada['+i+']" id="chegada-'+processoID+'" />');
					for(var c = 0; c < chegadas.length; c++){
						$('select#chegada-'+processoID).append('<option />').val(chegadas[c]).text(chegadas[c]+' ms');
					}
					// Append label prioridade + select prioridade
					divProcesso.append('<label for="prioridade-'+processoID+'" />').text('Prioridade: ');
					divProcesso.append('<select class="grid_1" name="prioridade['+i+']" id="prioridade-'+processoID+'" />');
					
					$('p#line-'+line).append(divProcesso);
					for(var p = 0; p < prioridades.length; p++){
						$('select#prioridade-'+processoID).append('<option />').val(prioridades[p]).text(prioridades[p]);
					}
				}
			});
			
			// FAZER JSON COM TEMPLATE JQUERY
			
			// INCLUIR LIMITE 5 DIVS POR LINHA
			
			$('body').append($('#videosTemplate').tmpl(json));
			
			
			
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