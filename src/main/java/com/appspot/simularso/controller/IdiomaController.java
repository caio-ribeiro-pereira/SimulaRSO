package com.appspot.simularso.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.appspot.simularso.infra.Idioma;

@Resource
public class IdiomaController {

	private Idioma idioma;
	private Result result;

	public IdiomaController(Idioma idioma, Result result) {
		this.idioma = idioma;
		this.result = result;
	}

	@Get("/idioma/{idioma}")
	public void locale(String idioma) {
		this.idioma.setIdioma(idioma);
		result.redirectTo(HomeController.class).home();
	}

	public String getIdioma() {
		return this.idioma.getIdioma();
	}
}
