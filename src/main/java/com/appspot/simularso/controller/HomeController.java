package com.appspot.simularso.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

import com.appspot.simularso.infra.Idioma;

@Resource
public class HomeController {

	private Idioma idioma;

	public HomeController(Idioma idioma) {
		this.idioma = idioma;
	}

	@Get("/")
	public void home() {
	}

	public String getIdioma() {
		return this.idioma.getIdioma();
	}
}