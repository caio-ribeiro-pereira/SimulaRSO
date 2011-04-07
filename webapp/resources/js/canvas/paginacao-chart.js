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
function PaginacaoChart(total, canvas, espaco) {
	this.ctx = canvas.getContext('2d');
	this.width = canvas.width;
	this.height = canvas.height;
	this.espaco = espaco;
	this.ctx.clearRect(0, 0, this.width, this.height);
};

PaginacaoChart.prototype.draw = function(strRef, pg, totalPageFault){
	// Imprimindo String de Referencia
	var x = 0;
	var y = 50;
	this.ctx.beginPath();
	this.strokeStyle = '#000000';
	this.ctx.fillStyle = '#000000';
	this.ctx.font = "16px Arial";
	this.ctx.fillText('String de Referência:', x, y);
	
	// String de Referencia
	var str_x = x + 2;
	var str_y = y + 10;
	var str_w = this.espaco;
	var str_h = this.espaco;
	for(var i = 0; i < strRef.length; i++){
		this.ctx.strokeRect(str_x, str_y, str_w, str_h);
		this.ctx.fillText(strRef[i], (str_x + 2), (str_y + 17));
		str_x += this.espaco + 10;
	}
	//Paginacao
	this.ctx.fillText('Frames:', x, y + 60);
	var pg_x = x + 2;
	var pg_w = this.espaco;
	var pg_h = this.espaco;
	for(var i = 0; i < pg.length; i++){
		var pg_y = y + 70;
		if(pg[i].pageFault){
			var palavras = pg[i].palavras;
			for(var p = 0; p < palavras.length; p++){
				this.ctx.strokeRect(pg_x, pg_y, pg_w, pg_h);
				this.ctx.fillText(palavras[p], (pg_x + 2), (pg_y + 17));
				pg_y += this.espaco + 5;
			}	
		}
		pg_x += this.espaco + 10;
	}
	this.ctx.fillText('Total de PageFaults: '+totalPageFault, x, this.height - 10);
	this.ctx.stroke();
};