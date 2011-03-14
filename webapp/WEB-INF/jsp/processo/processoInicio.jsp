<!DOCTYPE html>
<head>
	<title>SimulaEDP - Escalonamento de Processos</title>
</head>	
<body>
	<h1 class="clearfix">Escalonamento de Processos</h1>
	<form id="process-form" action="<c:url value="/executar-escalonamento-processo"/>" method="post">
		<div id="main-menu" class="clearfix menu">
			<div class="grid_3">
				<strong>Simulação: </strong>
				<select id="modo">
					<option value="UNICO">Única</option>
					<option value="COMPARATIVO">Comparativa</option>
				</select>
			</div>
			<div class="grid_3">
				<strong>Total de processos: </strong>
				<select id="total">
					<c:forEach begin="2" end="20" step="1" var="p">
						<option value="${p}">${p}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div id="algoritmo-menu" class="clearfix menu">
			<div id="alg1" class="grid_3">
				<strong>Algoritmo 1: </strong>
				<select name="algs[0]" id="algoritmo1">
					<option value="" selected>Selecione...</option>
					<c:forEach var="alg" items="${algoritmoProcesso}">
						<option value="${alg}">${alg.nome}</option>	
					</c:forEach>
				</select>
			</div>
			<div id="alg2" class="grid_3">
				<strong>Algoritmo 2: </strong>
				<select name="algs[1]" id="algoritmo2">
					<option value="" selected>Selecione...</option>
					<c:forEach var="alg" items="${algoritmoProcesso}">
						<option value="${alg}">${alg.nome}</option>	
					</c:forEach>
				</select>			
			</div>
			<div id="quantum" class="grid_4">
				<strong>Tempo de corte: </strong>
				<select name="qt">
					<option value="">Selecione...</option>
					<c:forEach begin="5" end="100" step="5" var="q">
						<option value="${q}">${q} ms</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div id="process-menu" class="clearfix menu"></div>
		<script id="processTemplate" type="text/x-jquery-tmpl">
			<div id="\${prDivId}" class="input-box">
				<p>
					<small>\${prLabel}: </small>
					<input type="hidden" name="\${prIdName}" value="\${prId}">
					<input type="hidden" name="\${prCorName}" value="\${prCor}">
				</p>
				<p>
					<label class="grid_1" for="\${inputBurst}"><small>Burst: </small></label>
					<input type="text" class="grid_1 burst" name="\${prBurstName}" id="\${inputBurst}" value="10" maxlength="2">
				</p>
				<p>
					<label class="grid_1" for="\${inputChegada}"><small>Chegada: </small></label>
					<input type="text" class="grid_1 chegada" name="\${prChegadaName}" id="\${inputChegada}" maxlength="2">
				</p>
				<p>
					<label class="grid_1" for="\${inputPrioridade}"><small>Prioridade: </small></label>
					<input type="text" class="grid_1 prioridade" name="\${prPrioridadeName}" id="\${inputPrioridade}" maxlength="2">
				</p>
			</div>
		</script>
		<div class="clearfix executar">
			<button type="submit">Executar</button>
		</div>
	</form>
	<script type="text/javascript">
		head.ready(function(){
			
			$('button').button();
			
			$('#alg2').hide();
			$('#quantum').hide();
			
			$('#process-form').submit(function(){
				$('div.executar button').attr('disabled','disabled');
			});
			
			$('#modo').change(function(){
				if(this.value == 'COMPARATIVO'){
					$('#alg2').show();
				}else{
					if($('#algoritmo1').val() != 'ROUNDROBIN' && $('#algoritmo2').val() == 'ROUNDROBIN'){
						$('#quantum').hide();
						$('#quantum select option:first-child').attr('selected','selected');
					}
					$('#alg2').hide();
					$('#alg2 select option:first-child').attr('selected','selected');
				}
			}).trigger('change');
			
			$('#algoritmo1').change(function(){
				if(this.value == 'ROUNDROBIN' || $('#algoritmo2').val() == 'ROUNDROBIN'){
					$('#quantum').show();
				}else{
					$('#quantum').hide();
					$('#quantum select option:first-child').attr('selected','selected');
				}
			}).trigger('change');
			
			$('#algoritmo2').change(function(){
				if(this.value == 'ROUNDROBIN' || $('#algoritmo1').val() == 'ROUNDROBIN'){
					$('#quantum').show();
				}else{
					$('#quantum').hide();
					$('#quantum select option:first-child').attr('selected','selected');
				}
			}).trigger('change');
			
			$('#total').change(function(){
				var content = $('#process-menu').empty().hide();
				var total = $('#total').val();
				var processos = new Array();
				var colors = new Colors();
				for(var i = 0; i < total; i++){
				    processos.push(
				    	{inputBurst : "burst-"+(i+1),
						 inputChegada : "chegada-"+(i+1),
						 inputPrioridade : "prioridade-"+(i+1),
						 prCor : colors[i],
						 prDivId : "processo-"+(i+1),
						 prLabel : "Processo: "+(i+1),
						 prId : (i+1),
						 prIdName : "pr["+i+"].id",
						 prBurstName : "pr["+i+"].burst", 
						 prChegadaName : "pr["+i+"].tempoChegada", 
						 prPrioridadeName : "pr["+i+"].prioridade",
						 prCorName : "pr["+i+"].cor"}
				    );
				}
				var template = $('#processTemplate').tmpl(processos);
				content.append(template).show();
				$('input[type="text"].burst').spinner({ min: 1, max: 99, showOn: 'both' });
				$('input[type="text"].chegada').spinner({ min: 0, max: 99, showOn: 'both' });
				$('input[type="text"].prioridade').spinner({ min: 0, max: 10, showOn: 'both' });
			}).trigger('change');
		});
	</script>
</body>