package br.com.simulaedp.model.enumerator;

public enum ModoSimulacao {

	UNICO("Simulação única"), COMPARATIVO("Simulação comparativa");
	
	String modo;
	
	ModoSimulacao(String modo) {
		this.modo = modo;
	}
	
	public String getModo(){
		return this.modo;
	}
}
