package com.appspot.simularso.scheduler.process.logic.impl;

public enum EscalonadorProcessoAlgoritmo {
	FCFS("FC-FS"), SJF("SJF"), SRT("SRT"), RR("Round-Robin");

	private String nome;

	EscalonadorProcessoAlgoritmo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
}
