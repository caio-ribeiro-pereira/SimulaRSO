/**
 * Canvas-Gantt Chart Desenvolvido por Caio R. Pereira Data: 18/02/2011
 * 
 * Gráfico de Gantt para exibição de toda simulação dos algoritmos de
 * escalonamento de processos.
 * 
 */
function ProcessoChart(total) {
	var canvas = document.getElementById('processo-chart');
	this.ctx = canvas.getContext('2d');
	this.width = canvas.width;
	this.height = canvas.height;
	this.inicio_x = 0;
	this.inicio_y = 0;
	this.total = total;
	this.espaco = 20;
	this.ctx.clearRect(0, 0, this.width, this.height);
};

ProcessoChart.prototype.background = function() {
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
	this.ctx.font = "9px Arial";
	this.ctx.fillStyle = '#000000';
	var tempo = 0;
	for(var t = this.inicio_x; t < (this.width - this.espaco); t += this.espaco){
		this.ctx.fillText(tempo++, t + 3, 15);
	}
	this.ctx.stroke();
};

ProcessoChart.prototype.draw = function(p) {
	for(var i = 0; i < p.length; i++){
		this.ctx.fillStyle = p[i].cor;
		this.ctx.fillRect(p[i].x, p[i].y, p[i].w, p[i].h);
	}
};
