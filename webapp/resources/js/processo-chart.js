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
	this.espaco = 10;
	this.inicio_x = 10;
	this.inicio_y = 40;
	this.cores = new ProcessoCores();
	this.total = total;
};

ProcessoChart.prototype.setup = function() {
	this.ctx.clearRect(0, 0, this.width, this.height);
};

ProcessoChart.prototype.background = function() {
	this.ctx.beginPath();
	this.strokeStyle = '#000000';
	var folga_inicial = 3;
	var folga_final = folga_inicial * 2;
	// linhas
	for ( var y = this.inicio_y; y < this.height; y += this.espaco) {
		this.ctx.moveTo(this.inicio_y, y + folga_inicial);
		this.ctx.lineTo((this.width - this.espaco) + folga_final, y
				+ folga_inicial);
		// colunas
		for ( var x = this.inicio_x; x < this.width; x += this.espaco) {
			this.ctx.moveTo(x + folga_inicial, this.inicio_x);
			this.ctx.lineTo(x + folga_inicial, (this.height - this.espaco)
					+ folga_final);
		}
	}
	this.ctx.stroke();
};

function ProcessoCores() {
	this.cores = new Array();
	this.cores['cor1'] = '#000080';
	this.cores['cor2'] = '#008000';
	this.cores['cor3'] = '#800000';
	this.cores['cor4'] = '#FF0000';
	this.cores['cor5'] = '#FFD700';
	this.cores['cor6'] = '#000000';
	this.cores['cor7'] = '#00FFFF';
	this.cores['cor8'] = '#708090';
	this.cores['cor9'] = '#DDA0DD';
	this.cores['cor10'] = '#FFE4C4';
	this.cores['cor11'] = '#000030';
	this.cores['cor12'] = '#003000';
	this.cores['cor13'] = '#300000';
	this.cores['cor14'] = '#0000DD';
	this.cores['cor15'] = '#007DFF';
	this.cores['cor16'] = '#555555';
	this.cores['cor17'] = '#33DDDD';
	this.cores['cor18'] = '#504020';
	this.cores['cor19'] = '#CCBB78';
	this.cores['cor20'] = '#696969';
	return this.cores;
};