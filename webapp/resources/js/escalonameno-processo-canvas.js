/**
 * 	Canvas-Gantt Chart
 *  Desenvolvido por Caio R. Pereira
 *  Data: 18/02/2011
 *  
 *  Gráfico de Gantt para exibição de toda simulação dos
 *  algoritmos de escalonamento de processos.
 * 
 */
var ESPACO = 20;
var HEIGHT = 0;
var WIDTH = 0;
var INICIO = ESPACO * 2;
var LINE_COLOR = '#333';
var TIME_LABEL = 'Tempo em milisegundos';
var CTX;

$(function(){
	var canvas = document.getElementById('processo-canvas');
	WIDTH = canvas.width;
	HEIGHT = canvas.height;
	CTX = canvas.getContext('2d');

	setup_context();
	draw_timeline();
	draw_background();
	
});

function setup_context(){
	CTX.clearRect(0, 0, WIDTH, HEIGHT);
}
function draw_timeline(){
	var texto_x = ((WIDTH / 2) - INICIO);
	var texto_y = 15;
	CTX.fillText(TIME_LABEL,texto_x, texto_y);
	var numero_y = 37;
	var tempo = 0;
	for(var numero_x = (INICIO - 5); numero_x < (WIDTH - 10); numero_x += ESPACO) {
		CTX.fillText(tempo.valueOf(), numero_x, numero_y);
		tempo += 10;
	}
}
function draw_background() {
	CTX.beginPath();
	CTX.strokeStyle = LINE_COLOR;
	var folga_inicial = 3;
	var folga_final = folga_inicial * 2;
	// linhas
	for ( var y = INICIO; y < HEIGHT; y += ESPACO) {
		CTX.moveTo(INICIO, y + folga_inicial);
		CTX.lineTo((WIDTH - ESPACO) + folga_final, y + folga_inicial);
		// colunas
		for ( var x = INICIO; x < WIDTH; x += ESPACO) {
			CTX.moveTo(x + folga_inicial, INICIO);
			CTX.lineTo(x + folga_inicial, (HEIGHT - ESPACO) + folga_final);
		}
	}
	CTX.stroke();
}

