package com.appspot.simularso.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.appspot.simularso.infra.ProjectLocale;

@Resource
public class HomeController {

	private final Result result;
	private final ProjectLocale locale;

	public HomeController(ProjectLocale locale, Result result) {
		this.locale = locale;
		this.result = result;
	}

	@Get("/")
	public void home() {
	}

	@Post("/idioma")
	public void idioma(String idioma) {
		this.locale.setIdioma(idioma);
		result.redirectTo(this).home();
	}

	public String getIdioma() {
		return locale.getIdioma();
	}
}