package com.appspot.simularso.controller;

import com.appspot.simularso.infra.Idioma;
import com.appspot.simularso.logic.disc.EscalonadorDiscoAlgoritmo;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaAlgoritmo;
import com.appspot.simularso.logic.process.EscalonadorProcessoAlgoritmo;

public abstract class ApplicationController {

	private final Idioma idioma;

	public ApplicationController(Idioma idioma) {
		this.idioma = idioma;
	}

	protected void setIdioma(String idioma) {
		this.idioma.setIdioma(idioma);
	}

	protected String getIdioma() {
		return this.idioma.getIdioma();
	}

	protected EscalonadorProcessoAlgoritmo[] getEscalonadorProcessoAlgoritmo() {
		return EscalonadorProcessoAlgoritmo.values();
	}

	protected EscalonadorDiscoAlgoritmo[] getEscalonadorDiscoAlgoritmo() {
		return EscalonadorDiscoAlgoritmo.values();
	}

	protected PaginacaoMemoriaAlgoritmo[] getPaginacaoMemoriaAlgoritmo() {
		return PaginacaoMemoriaAlgoritmo.values();
	}
}
