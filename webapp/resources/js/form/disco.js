head.ready(function() {

	$('#totalRequisicao').change(function() {
		var content = $('#disco-menu').empty().hide();
		var total = $('#totalRequisicao').val();
		if (total > 0) {
			var cilindros = new Array();
			for ( var i = 0; i < total; i++) {
				cilindros.push({
					cilindroId : 'cilindro-' + (i + 1),
					cilindroLabelId : (i + 1)
				});
			}
			var template = $('#discoTemplate').tmpl(cilindros);
			content.append(template).fadeIn();
			$('input[type="text"].cilindro').onlyNumeric();
		}
	});

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

	$('#modo').change(function() {
		if ($('#modo').val() == 2) {
			$('#alg2').fadeIn();
		} else {
			$('#alg2').hide();
			$('#algoritmo2').val('').change();
		}
	}).trigger('change');

	$('#rules-dialog').modal({
		closeOnEscape : true
	});
	$('#close-dialog').click(function() {
		$('#rules-dialog').modal('hide');
	});
});