package br.com.simulaedp.model.enumerator;

public enum AlgoritmoProcesso {

	FCFS("FC-FS"), SJF("SJF"), PRIORIDADE("Prioridade"), SRT("SRT"), ROUNDROBIN("Round Robin");
	
	private String algortimo;
	
	AlgoritmoProcesso(String algortimo){
		this.algortimo = algortimo;
	}
	
	public String getAlgoritmo(){
		return this.algortimo;
	}
}
