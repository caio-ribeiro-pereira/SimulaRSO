/**
 * Canvas Plugin Gantt Chart
 * 
 * Gráfico de Gantt para exibição de toda simulação dos algoritmos de
 * substituição de páginas - Paginação de memória.
 * 
 * @autor Caio R. Pereira  
 * @since 07/04/2011
 * @twitter crp_underground
 * @email caio.ribeiro.pereira@gmail.com
 * 
 */
function PaginacaoChart(total, canvas, espaco, strRef, pg, time) {
	var ctx = canvas.getContext('2d');
	var width = canvas.width;
	var height = canvas.height;
	var x = 0;
	var y = 0;
	var i = 0;
	ctx.clearRect(x, y, width, height);
	// String de Referencia
	var str_x = x + 2;
	var str_y = y + 45;
	var str_w = espaco;
	var str_h = espaco;
	// Paginacao
	var pg_x = x + 2;
	var pg_w = espaco;
	var pg_h = espaco;
	var pg_y = y + 105;
	
	// Imprimindo String de Referencia
	ctx.beginPath();
	ctx.strokeStyle = '#000000';
	ctx.fillStyle = '#000000';
	ctx.font = "14px Arial";
	ctx.fillText('String de Referência:', x, y + 30);
	ctx.fillText('Frames:', x, y + 90);
	
	var anim = window.setInterval(function(){
		if(i < strRef.length && i < pg.length){
			ctx.strokeRect(str_x, str_y, str_w, str_h);
			ctx.fillText(strRef[i], (str_x + 7), (str_y + 17));
			str_x += espaco + 10;
			if(pg[i].pageFault){
				var palavras = pg[i].palavras;
				pg_y = y + 105;
				for(var p = 0; p < palavras.length; p++){
					ctx.strokeRect(pg_x, pg_y, pg_w, pg_h);
					ctx.fillText(palavras[p], (pg_x + 7), (pg_y + 17));
					pg_y += espaco + 5;
				}	
			}
			pg_x += espaco + 10;
			i++;
		}else{
			window.clearInterval(anim);
		}
		ctx.stroke();
	}, time);
}