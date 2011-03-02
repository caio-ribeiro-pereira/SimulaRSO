package br.com.simulaedp.model.enumerator;

public enum AlgoritmoProcesso {

	FCFS("FC-FS"), SJF("SJF"), PRIORIDADE("Prioridade"), SRT("SRT"), ROUNDROBIN("Round Robin");
	
	private String nome;
	
	AlgoritmoProcesso(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return this.nome;
	}
}
