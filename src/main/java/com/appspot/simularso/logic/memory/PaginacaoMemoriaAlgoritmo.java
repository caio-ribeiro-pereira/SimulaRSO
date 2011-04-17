package com.appspot.simularso.logic.memory;

public enum PaginacaoMemoriaAlgoritmo {

	FIFO("FIFO"), OPT("OPT"), LRU("LRU"), MRU("MRU");

	private String nome;

	PaginacaoMemoriaAlgoritmo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
}
