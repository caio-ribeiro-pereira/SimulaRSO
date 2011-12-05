head.ready(function() {
	var MAXBURST = 98;
	var MAXCHEGADA = 100;
	var MAXPRIORIDADE = 11;

	$('#random').click(function() {
		var total = $('#total').val();
		for ( var i = 1; i <= total; i++) {
			var randomBurst = Math.floor(Math.random() * MAXBURST) + 1;
			var randomChegada = Math.floor(Math.random() * MAXCHEGADA);
			var randomPrioridade = Math.floor(Math.random() * MAXPRIORIDADE);
			$('input[type="text"]#burst-' + i).val(randomBurst);
			$('input[type="text"]#chegada-' + i).val(randomChegada);
			$('input[type="text"]#prioridade-' + i).val(randomPrioridade);
		}
	});

	$('#process-form').submit(function() {
		$('button').attr('disabled', 'disabled');
	});

	$('#modo').change(
			function() {
				if (this.value == 2) {
					$('#alg2').fadeIn();
				} else {
					if ($('#algoritmo1').val() != 'RR'
							&& $('#algoritmo2').val() == 'RR') {
						$('#quantum').val('').change();
						$('#quantum').hide();
					}
					$('#algoritmo2').val('').change();
					$('#alg2').hide();
				}
			}).trigger('change');

	$('#algoritmo1').change(
			function() {
				if (this.value == 'RR' || $('#algoritmo2').val() == 'RR') {
					$('#quantum').fadeIn();
				} else {
					$('#quantum').val('').change();
					$('#quantum').hide();
				}
				if (this.value == 'RR' || this.value == 'SRT'
						|| $('#algoritmo2').val() == 'RR'
						|| $('#algoritmo2').val() == 'SRT') {
					$('#contexto').fadeIn();
				} else {
					$('#contexto').val('').change();
					$('#contexto').hide();
				}
			}).trigger('change');

	$('#algoritmo2').change(
			function() {
				if (this.value == 'RR' || $('#algoritmo1').val() == 'RR') {
					$('#quantum').fadeIn();
				} else {
					$('#quantum').val('').change();
					$('#quantum').hide();
				}
				if (this.value == 'RR' || this.value == 'SRT'
						|| $('#algoritmo1').val() == 'RR'
						|| $('#algoritmo1').val() == 'SRT') {
					$('#contexto').fadeIn();
				} else {
					$('#contexto').val('').change();
					$('#contexto').hide();
				}
			}).trigger('change');

	$('#total').change(function() {
		var content = $('#process-menu').empty().hide();
		var total = $('#total').val();
		if (total > 0) {
			var processos = new Array();
			var cores = simulaRSO.cores();
			for ( var i = 0; i < total; i++) {
				processos.push({
					inputBurst : 'burst-' + (i + 1),
					inputChegada : 'chegada-' + (i + 1),
					inputPrioridade : 'prioridade-' + (i + 1),
					prCor : cores[i],
					prDivId : 'processo-' + (i + 1),
					prId : (i + 1)
				});
			}
			var template = $('#processTemplate').tmpl(processos);
			content.append(template).fadeIn();
			$('input[type="text"].burst').onlyNumeric();
			$('input[type="text"].chegada').onlyNumeric();
			$('input[type="text"].prioridade').onlyNumeric();
		}
	});

	$('#rules-dialog').modal({
		closeOnEscape : true
	});
	$('#close-dialog').click(function() {
		$('#rules-dialog').modal('hide');
	});
});