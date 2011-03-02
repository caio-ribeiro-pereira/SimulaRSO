package br.com.simulaedp.model.enumerator;

public enum ModoSimulacao {

	UNICO("Simula��o �nica"), COMPARATIVO("Simula��o comparativa");
	
	String modo;
	
	ModoSimulacao(String modo) {
		this.modo = modo;
	}
	
	public String getModo(){
		return this.modo;
	}
}
