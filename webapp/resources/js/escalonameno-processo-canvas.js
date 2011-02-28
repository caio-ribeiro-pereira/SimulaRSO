/**
 * Canvas-Gantt Chart Desenvolvido por Caio R. Pereira Data: 18/02/2011
 * 
 * Gráfico de Gantt para exibição de toda simulação dos algoritmos de
 * escalonamento de processos.
 * 
 */
function ProcessoCores() {
	this.cores = new Array();
	this.cores['fonte'] = '#000000';
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
	return this.cores;
};

function ProcessoLabels() {
	this.labels = new Array();
	this.labels['tempo'] = 'Tempo em milisegundos';
	this.labels['legenda'] = 'Legenda';
	this.labels['processo'] = 'Processo';
	return this.labels;
};

function ProcessoCanvas() {
	var canvas = document.getElementById('processo-canvas');
	this.ctx = canvas.getContext('2d');
	this.width = canvas.width;
	this.height = canvas.height;
	this.espaco = 20;
	this.inicio = (this.espaco * 2);
	this.cores = new ProcessoCores();
	this.labels = new ProcessoLabels();
	this.total = document.getElementById('total').value;
};

ProcessoCanvas.prototype.setup = function() {
	this.ctx.clearRect(0, 0, this.width, this.height);
};

ProcessoCanvas.prototype.timeLine = function() {
	var texto_x = ((this.width / 2) - this.inicio);
	var texto_y = 15;
	this.ctx.fillText(this.labels['tempo'], texto_x, texto_y);
	var numero_y = 37;
	var tempo = 0;
	for ( var numero_x = (this.inicio - 5); numero_x < (this.width - 10); numero_x += this.espaco) {
		this.ctx.fillText(tempo.valueOf(), numero_x, numero_y);
		tempo += 10;
	}
};

ProcessoCanvas.prototype.background = function() {
	this.ctx.beginPath();
	this.strokeStyle = this.cores['font'];
	var folga_inicial = 3;
	var folga_final = folga_inicial * 2;
	// linhas
	for ( var y = this.inicio; y < this.height; y += this.espaco) {
		this.ctx.moveTo(this.inicio, y + folga_inicial);
		this.ctx.lineTo((this.width - this.espaco) + folga_final, y
				+ folga_inicial);
		// colunas
		for ( var x = this.inicio; x < this.width; x += this.espaco) {
			this.ctx.moveTo(x + folga_inicial, this.inicio);
			this.ctx.lineTo(x + folga_inicial, (this.height - this.espaco)
					+ folga_final);
		}
	}
	this.ctx.stroke();
};

ProcessoCanvas.prototype.resultado = function() {
	this.ctx.beginPath();
	for ( var i = 0; i < this.total; i++) {
		this.ctx.fillStyle = this.cores['cor' + (i + 1)];
		this.ctx.fillRect(i * 11, i * 11, 20, 20);
	}
	this.ctx.fill();
};