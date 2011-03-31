<!DOCTYPE html>
<head>
	<title>Simulação de escalonamento de processos</title>
	<script type="text/javascript">
		head.ready(function(){
			var MAXBURST = 98;
			var MAXCHEGADA = 100;
			var MAXPRIORIDADE = 11;
			
			$('button').button({
				icons: {primary: 'ui-icon-gear'}	
			}).next().button({
				icons: {primary: 'ui-icon-shuffle'}
			});
			
			$('#alg2').hide();
			$('#quantum').hide();
			
			$('#random').click(function(){
				var total = $('#total').val();
				for(var i = 1; i <= total; i++){
					var randomBurst = Math.floor(Math.random() * MAXBURST) + 1;
					var randomChegada = Math.floor(Math.random()* MAXCHEGADA);
					var randomPrioridade = Math.floor(Math.random() * MAXPRIORIDADE);
					$('input[type="text"]#burst-'+i).val(randomBurst);
					$('input[type="text"]#chegada-'+i).val(randomChegada);
					$('input[type="text"]#prioridade-'+i).val(randomPrioridade);
				}
			});
			
			$('#process-form').submit(function(){
				$('button').attr('disabled','disabled');
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
			
			$('#total').bind('change',function(){
				var content = $('#process-menu').empty().hide();
				var total = $('#total').val();
				if(total > 0){
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
							 prChegadaName : "pr["+i+"].chegada", 
							 prPrioridadeName : "pr["+i+"].prioridade",
							 prCorName : "pr["+i+"].cor"}
					    );
					}
					var template = $('#processTemplate').tmpl(processos);
					content.append(template).show();
					$('input[type="text"].burst').spinner({ min: 1, max: 99, showOn: 'always' }).onlyNumeric();
					$('input[type="text"].chegada').spinner({ min: 0, max: 99, showOn: 'always' }).onlyNumeric();
					$('input[type="text"].prioridade').spinner({ min: 0, max: 10, showOn: 'always' }).onlyNumeric();	
				}else {
					$('#process-menu').html('<strong class="clearfix info-message">Determine o número de processos para simular.</strong>').show();
				}
			}).trigger('change');
		});
	</script>
</head>	
<body>
	<h2 class="clearfix">Algoritmos de escalonamento de processos</h2>
	<form id="process-form" action="<c:url value="/executar-escalonamento-processo"/>" method="post">
		<div id="main-menu" class="clearfix menu">
			<div class="grid_3">
				<strong>Simulação: </strong>
				<select id="modo">
					<option value="UNICO">Única</option>
					<option value="COMPARATIVO">Comparativa</option>
				</select>
			</div>
			<div class="grid_4">
				<strong>Total de processos: </strong>
				<select id="total">
					<option value="" selected="selected">Selecione...</option>
					<c:forEach begin="2" end="20" step="1" var="p">
						<option value="${p}">${p} procesos</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div id="algoritmo-menu" class="clearfix menu">
			<div id="alg1" class="grid_3">
				<strong>Algoritmo 1: </strong>
				<select name="algs[0]" id="algoritmo1">
					<option value="" selected="selected">Selecione...</option>
					<c:forEach var="alg" items="${algoritmoProcesso}">
						<option value="${alg}">${alg.nome}</option>	
					</c:forEach>
				</select>
			</div>
			<div id="alg2" class="grid_3">
				<strong>Algoritmo 2: </strong>
				<select name="algs[1]" id="algoritmo2">
					<option value="" selected="selected">Selecione...</option>
					<c:forEach var="alg" items="${algoritmoProcesso}">
						<option value="${alg}">${alg.nome}</option>	
					</c:forEach>
				</select>			
			</div>
			<div id="quantum" class="grid_4">
				<strong>Tempo de corte: </strong>
				<select name="qt">
					<c:forEach begin="1" end="4" step="1" var="qt1">
						<option value="${qt1}">${qt1} ms</option>					
					</c:forEach>
					<c:forEach begin="5" end="100" step="5" var="qt2">
						<option value="${qt2}">${qt2} ms</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div id="process-menu" class="clearfix menu"></div>
		<script id="processTemplate" type="text/x-jquery-tmpl">
			<div id="\${prDivId}" class="input-box">
				<p class="clearfix processo">
					<strong>\${prLabel}: </strong>
					<input type="hidden" name="\${prIdName}" value="\${prId}">
					<input type="hidden" name="\${prCorName}" value="\${prCor}">
				</p>
				<p class="clearfix processo">
					<label class="grid_1" for="\${inputBurst}"><small>Burst: </small></label>
					<input type="text" class="grid_1 burst" name="\${prBurstName}" id="\${inputBurst}" value="10" maxlength="2">
				</p>
				<p class="clearfix processo">
					<label class="grid_1" for="\${inputChegada}"><small>Chegada: </small></label>
					<input type="text" class="grid_1 chegada" name="\${prChegadaName}" id="\${inputChegada}" maxlength="2">
				</p>
				<p class="clearfix processo">
					<label class="grid_1" for="\${inputPrioridade}"><small>Prioridade: </small></label>
					<input type="text" class="grid_1 prioridade" name="\${prPrioridadeName}" id="\${inputPrioridade}" maxlength="2">
				</p>
			</div>
		</script>
		<div class="clearfix execute-panel">
			<p>
				<button id="execute" type="submit">Executar</button>
				<button id="random" type="button">Geração aleatória</button>			
			</p>
		</div>
	</form>
</body>