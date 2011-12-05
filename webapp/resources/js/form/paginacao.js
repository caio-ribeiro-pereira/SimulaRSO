head.ready(function() {

	$('#random').click(function() {
		var total = $('#stringRefTotal').val();
		for ( var i = 1; i <= total; i++) {
			var MAXREF = 10;
			var randomRef = Math.floor(Math.random() * MAXREF);
			$('input[type="text"]#stringReferencia-' + i).val(randomRef);
		}
	});

	$('#pagination-form').submit(function() {
		$('button').attr('disabled', 'disabled');
	});

	$('#modo').change(function() {
		if (this.value == 2) {
			$('#alg2').fadeIn();
		} else {
			$('#algoritmo2').val('').change();
			$('#alg2').hide();
		}
	}).trigger('change');

	$('#stringRefTotal').change(function() {
		var content = $('#pagination-menu').empty().hide();
		var total = $('#stringRefTotal').val();
		if (total > 0) {
			var paginator = new Array();
			for ( var i = 0; i < total; i++) {
				paginator.push({
					inputStringRef : 'stringReferencia-' + (i + 1),
					stringRefId : (i + 1)
				});
			}
			var template = $('#paginationTemplate').tmpl(paginator);
			content.append(template).fadeIn();
			$('input[type="text"].stringReferencia').onlyNumeric();
		}
	});

	$('#rules-dialog').modal({
		closeOnEscape : true
	});
	$('#close-dialog').click(function() {
		$('#rules-dialog').modal('hide');
	});

});