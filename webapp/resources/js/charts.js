/*
 * Canvas Plugin Gantt Chart
 * 
 * Gráfico de Gantt para simulação dos recursos de sistemas operacionais.
 * 
 * @autor Caio R. Pereira  
 * @autor Andre A. Rodrigues
 * @since 18/02/2011
 * @email and.arodrigues@gmail.com
 * @email caio.ribeiro.pereira@gmail.com
 * 
 */
(function($) {
	var simulaRSO = {
		cores : function() {
			colors = new Array();
			colors[0] = '#FF0000';
			colors[1] = '#00CCCC';
			colors[2] = '#006400';
			colors[3] = '#DC143C';
			colors[4] = '#6495ED';
			colors[5] = '#F6A5F0';
			colors[6] = '#0000FF';
			colors[7] = '#8B008B';
			colors[8] = '#A9A9A9';
			colors[9] = '#FF1493';
			colors[10] = '#FFC600';
			colors[11] = '#E9967A';
			colors[12] = '#000080';
			colors[13] = '#20B2AA';
			colors[14] = '#00CC00';
			colors[15] = '#7B68EE';
			colors[16] = '#333333';
			colors[17] = '#8B4513';
			colors[18] = '#FFBB00';
			colors[19] = '#008080';
			return colors;
		},
		chart : {
			processo : function(total, canvas, espaco, p, time) {
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
				for ( var t = inicio_x; t < (width - espaco); t += espaco) {
					ctx.fillText(tempo++, t + 3, 17);
				}
				ctx.stroke();
				// Animacao
				var i = 0;
				var j = p[i].x;
				var interval = p[i].w;
				var anim = window.setInterval(function() {
					// Processos
					if (i < p.length) {
						ctx.fillStyle = p[i].cor;
						if (interval > 0) {
							ctx.fillRect((j++ * espaco), (p[i].y * espaco),
									(espaco), (p[i].h * espaco));
							interval--;
						} else {
							if (++i < p.length) {
								interval = p[i].w;
								j = p[i].x;
							} else {
								interval = 0;
							}
						}
					} else {
						window.clearInterval(anim);
					}
					ctx.stroke();
				}, time);
				return false;
			},

			disco : function(canvas, spaceX, spaceY, cilindros, time) {
				var ctx = canvas.getContext("2d");
				var width = canvas.width;
				var height = canvas.height;
				ctx.clearRect(0, 0, width, height);
				ctx.font = "10px Arial";
				// Gerador de positions
				var pos = new Array();
				var y = 10;
				for ( var i = 0; i < cilindros.length; i++) {
					var x = (cilindros[i] * spaceX) + 5;
					y += spaceY;
					pos.push({
						x : x,
						y : y
					});
				}
				// Linha do tempo
				var timeY = 30;
				ctx.beginPath();
				ctx.moveTo(0, timeY);
				ctx.lineTo(width, timeY);
				for ( var i = 0; i < cilindros.length; i++) {
					var posX = (cilindros[i] * spaceX) + 5;
					ctx.fillText(cilindros[i], (posX - 5), (timeY - 20));
					ctx.moveTo(posX, (timeY - 5));
					ctx.lineTo(posX, (timeY + 5));
					ctx.stroke();
				}
				ctx.closePath();
				// Animacao do escalonamento
				var i = 0;
				var j = 0;
				var flag = true;
				window.setInterval(function() {
					if (flag) {
						if (i < pos.length) {
							// Pontos
							ctx.beginPath();
							ctx
									.arc(pos[i].x, pos[i].y, 5, 0, Math.PI * 2,
											true);
							ctx.fill();
							ctx.closePath();
							i++;
						} else {
							flag = false;
						}
						// Linhas
						if (j < pos.length - 1) {
							ctx.beginPath();
							ctx.moveTo(pos[j].x, pos[j].y);
							ctx.lineTo(pos[j + 1].x, pos[j + 1].y);
							ctx.stroke();
							ctx.closePath();
							j++;
						}
					} else {
						window.clearInterval(this);
					}
				}, time);
				return false;
			},
			paginacao : function(total, canvas, espaco, strRef, pg, time,
					strRefLabel, frameLabel) {
				var ctx = canvas.getContext('2d');
				var width = canvas.width;
				var height = canvas.height;
				var x = 0;
				var y = 0;
				var i = 0;
				ctx.clearRect(x, y, width, height);
				// String de Referencia
				var str_x = x + 2;
				var str_y = y + 30;
				var str_w = espaco;
				var str_h = espaco;
				// Paginacao
				var pg_x = x + 2;
				var pg_w = espaco;
				var pg_h = espaco;
				var pg_y = y + 90;

				// Imprimindo String de Referencia
				ctx.beginPath();
				ctx.strokeStyle = '#000000';
				ctx.fillStyle = '#000000';
				ctx.font = "14px Arial";
				ctx.fillText(strRefLabel, x, y + 15);
				ctx.fillText(frameLabel, x, y + 80);
				// Animacao
				var anim = window.setInterval(function() {
					if (i < strRef.length && i < pg.length) {
						ctx.strokeRect(str_x, str_y, str_w, str_h);
						ctx.fillText(strRef[i], (str_x + 7), (str_y + 17));
						str_x += espaco + 10;
						if (pg[i].pageFault) {
							var palavras = pg[i].palavras;
							pg_y = y + 95;
							for ( var p = 0; p < palavras.length; p++) {
								ctx.strokeRect(pg_x, pg_y, pg_w, pg_h);
								ctx.fillText(palavras[p], (pg_x + 7),
										(pg_y + 17));
								pg_y += espaco + 5;
							}
						}
						pg_x += espaco + 10;
						i++;
					} else {
						window.clearInterval(anim);
					}
					ctx.stroke();
				}, time);
				return false;
			}
		}
	};
	
	window.simulaRSO = simulaRSO;
	
})(jQuery);