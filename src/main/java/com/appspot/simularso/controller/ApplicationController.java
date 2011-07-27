package com.appspot.simularso.controller;

import br.com.caelum.vraptor.Result;

import com.appspot.simularso.infra.Idioma;
import com.appspot.simularso.infra.Notice;
import com.appspot.simularso.logic.disc.EscalonadorDiscoAlgoritmo;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaAlgoritmo;
import com.appspot.simularso.logic.process.EscalonadorProcessoAlgoritmo;

public abstract class ApplicationController {

	protected Idioma idioma;
	protected Notice notice;
	protected final Result result;

	public ApplicationController(Result result, Notice notice, Idioma idioma) {
		this.result = result;
		this.notice = notice;
		this.idioma = idioma;
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
