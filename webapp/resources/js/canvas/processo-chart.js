/**
 * Canvas Plugin Gantt Chart
 * 
 * Gráfico de Gantt para exibição de toda simulação dos algoritmos de
 * escalonamento de processos.
 * 
 * @autor Caio R. Pereira  
 * @since 18/02/2011
 * @twitter crp_underground
 * @email caio.ribeiro.pereira@gmail.com
 * 
 */
function ProcessoChart(total, canvas, espaco, p, time) {
	var ctx = canvas.getContext('2d');
	var width = canvas.width;
	var height = canvas.height;
	var inicio_x = 0;
	var inicio_y = 0;
	ctx.clearRect(inicio_x, inicio_y, width, height);
	
	ctx.beginPath();
	strokeStyle = '#000000';
	// linhas
	for ( var y = inicio_y; y < height; y += espaco) {
		ctx.moveTo(inicio_y, y);
		ctx.lineTo((width - espaco) + 1, y);
		// colunas
		for ( var x = inicio_x; x < width; x += espaco) {
			ctx.moveTo(x, inicio_x);
			ctx.lineTo(x, (height - espaco) + 1);
		}
	}
	// Imprimindo Linha do tempo
	ctx.font = "9px Arial";
	ctx.fillStyle = '#000000';
	var tempo = 0;
	for(var t = inicio_x; t < (width - espaco); t += espaco){
		ctx.fillText(tempo++, t + 3, 17);
	}
	ctx.stroke();
	// Animacao
	var j = 0;
	var i = 0;
	var interval = p[i].w;
	var anim = window.setInterval(function(){
		// Processos
		if(i < p.length){
			ctx.fillStyle = p[i].cor;
			if(interval > 0){
				ctx.fillRect((j++ * espaco), (p[i].y * espaco), (1 * espaco), (p[i].h * espaco));
				interval--;
			}else{
				interval = (++i < p.length) ? p[i].w : 0;
			}
		}else{
			window.clearInterval(anim);
		}
		ctx.stroke();
	}, time);
};