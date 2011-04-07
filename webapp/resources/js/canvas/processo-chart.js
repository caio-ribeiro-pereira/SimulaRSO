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
function ProcessoChart(total, canvas, espaco) {
	this.ctx = canvas.getContext('2d');
	this.width = canvas.width;
	this.height = canvas.height;
	this.inicio_x = 0;
	this.inicio_y = 0;
	this.total = total;
	this.espaco = espaco;
	this.ctx.clearRect(0, 0, this.width, this.height);
};

ProcessoChart.prototype.draw = function(p) {
	for(var i = 0; i < p.length; i++){
		this.ctx.fillStyle = p[i].cor;
		this.ctx.fillRect(p[i].x * this.espaco, p[i].y * this.espaco, p[i].w * this.espaco, p[i].h * this.espaco);
	}
	
	this.ctx.beginPath();
	this.strokeStyle = '#000000';
	// linhas
	for ( var y = this.inicio_y; y < this.height; y += this.espaco) {
		this.ctx.moveTo(this.inicio_y, y);
		this.ctx.lineTo((this.width - this.espaco) + 1, y);
		// colunas
		for ( var x = this.inicio_x; x < this.width; x += this.espaco) {
			this.ctx.moveTo(x, this.inicio_x);
			this.ctx.lineTo(x, (this.height - this.espaco) + 1);
		}
	}
	// Imprimindo Linha do tempo
	this.ctx.font = "9px Arial";
	this.ctx.fillStyle = '#000000';
	var tempo = 0;
	for(var t = this.inicio_x; t < (this.width - this.espaco); t += this.espaco){
		this.ctx.fillText(tempo++, t + 3, 17);
	}
	this.ctx.stroke();
};
