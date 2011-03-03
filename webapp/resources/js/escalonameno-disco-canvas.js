/**
 * 	Canvas-Gantt Chart
 *  Desenvolvido por Caio R. Pereira
 *  Data: 18/02/2011
 *  
 *  Gráfico de Gantt para exibição de toda simulação dos
 *  algoritmos de escalonamento de disco.
 * 
 */
var SPACING = 10;
var HEIGHT = 0;
var WIDTH = 0;
var CTX;

$(function(){
	var canvas = document.getElementById("disco-canvas");
	WIDTH = canvas.width;
	HEIGHT = canvas.height;
	CTX = canvas.getContext('2d');
	draw_background(CTX,WIDTH,HEIGHT,SPACING);
});

function draw_background(ctx, width, height, spacing) {
	ctx.clearRect(0, 0, width, height);
	spacing = parseInt(spacing);
	if (spacing === NaN || spacing < 10) {
		spacing = 10;
	}
	ctx.beginPath();
	ctx.strokeStyle = "#555";
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
