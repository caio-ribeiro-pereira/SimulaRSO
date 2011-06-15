package com.appspot.simularso.controller;

import com.appspot.simularso.infra.Idioma;

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
}
