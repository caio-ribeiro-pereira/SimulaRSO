package com.appspot.simularso.paginator.memory.logic.impl;

public enum PaginacaoMemoriaAlgoritmo {

	FIFO("FIFO"), OPT("OPT"), LRU("LRU");

	private String nome;

	PaginacaoMemoriaAlgoritmo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
}
