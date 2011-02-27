package br.com.simulaedp.model;

public enum Algoritmo {

	FCFS("FC-FS"), SJF("SJF"), PRIORIDADE("Prioridade"), SRT("SRT"), ROUNDROBIN("Round Robin");
	
	private String tipo;
	
	Algoritmo(String tipo){
		this.tipo = tipo;
	}
	
	public String getAlgoritmo(){
		return this.tipo;
	}
}
