/*
 *   DiscoChart - Desenvolvido por André de Araújo Rodrigues
 *   Gráfico de linha feito em Canvas - html5 
 *	 Gráfico que simula um comportamento de um escalonador de disco 
 *	 @autor Andre de Araujo Rodrigues
 *   @since 13/04/2011
 *   @email and.arodrigues@gmail.com
 */
function DiscoChart(canvas,spaceX,spaceY,cilindros){
	var ctx = canvas.getContext("2d");
	var width = canvas.width;
	var height = canvas.height;	
	ctx.clearRect(0,0,width,height);
	ctx.font = "10px Arial";
	// Gerador de positions
	var pos = new Array();
	var y = 10;
	for(var i = 0; i < cilindros.length;i++){
		var x = (cilindros[i] * spaceX) + 5;
		y += spaceY;
		pos.push({x:x,y:y});
	}
	// Linha do tempo
	var timeY = 30;
	ctx.beginPath();
	ctx.moveTo(0,timeY);
	ctx.lineTo(width,timeY);
	for(var i = 0; i < cilindros.length; i++){
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
	var anim = window.setInterval(function(){
		if(flag){
			if(i < pos.length){
				//Pontos
				ctx.beginPath();
				ctx.arc(pos[i].x, pos[i].y, 5, 0, Math.PI*2, true);
				ctx.fill();
				ctx.closePath();
				i++;
			}else{
				flag = false;
			}
			//Linhas
			if(j < pos.length-1){
				ctx.beginPath();
				ctx.moveTo(pos[j].x,pos[j].y);
				ctx.lineTo(pos[j+1].x,pos[j+1].y);
				ctx.stroke();
				ctx.closePath();
				j++;
			}
		}else{
			window.clearInterval(anim);
		}
	}, 500);
};