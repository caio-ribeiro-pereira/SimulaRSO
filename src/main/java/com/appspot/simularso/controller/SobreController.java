package com.appspot.simularso.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

import com.appspot.simularso.infra.Idioma;

@Resource
public class SobreController {

	private Idioma idioma;

	public SobreController(Idioma idioma) {
		this.idioma = idioma;
	}

	@Get("/sobre")
	public void sobre() {
	}

	public String getIdioma() {
		return this.idioma.getIdioma();
	}
}
