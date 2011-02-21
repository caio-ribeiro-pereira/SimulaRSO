/**
 * 	Canvas-Gantt Chart
 *  Desenvolvido por Caio R. Pereira
 *  Data: 18/02/2011
 *  
 *  Gráfico de Gantt para exibição de toda simulação dos
 *  algoritmos de escalonamento de processos.
 * 
 */
var SPACING = 10;
var HEIGHT = 0;
var WIDTH = 0;
var TOTAL = 10;
var CTX;
$(function(){
	
	setupCanvas();
	
	draw_background(CTX, WIDTH, HEIGHT, SPACING);
	$("#run").hide();
	$("#process_setup").hide();
	
	$("#setup").click(function(){
		TOTAL = parseInt($("#process_total").val());
		if(TOTAL === NaN || TOTAL < 10){TOTAL = 10;}
		$("#process_setup").empty();
		for(var i = 0; i < TOTAL; i++){
			var input = "<input type='text' id='p-"+(i+1)+"' size='2' maxlength='2'>";
			var label = "<label for='p-"+(i+1)+"'>Processo "+(i+1)+" :</label>";
			$("#process_setup").append(label+input);
			$("#process_setup").show();
		}
		
		$("#run").show();	
	});
	$("#run").click(function(){
		//DRAW THE PROCESS
	});
})

function setupCanvas(){
	//CORRIGIR
	$("canvas").attr("width", screen.width - 50);
	$("canvas").attr("height", 600);
	
	var canvas = document.getElementByTag("canvas");
	WIDTH = canvas.width;
	HEIGHT = canvas.height;
	CTX = canvas.getContext('2d');	
	
}

function draw_background(ctx, width, height, spacing) {
	ctx.clearRect(0, 0, width, height);
	spacing = parseInt(spacing);
	if (spacing === NaN || spacing < 10) {
		spacing = 10;
	}
	ctx.beginPath();
	ctx.strokeStyle = "#CCC";
	// linhas
	for ( var y = spacing; y < height; y += spacing) {
		ctx.moveTo(spacing, y);
		ctx.lineTo(width - spacing, y);
		// colunas
		for ( var x = spacing; x < width; x += spacing) {
			ctx.moveTo(x, spacing);
			ctx.lineTo(x, height - spacing);
		}
	}
	ctx.stroke();
}
