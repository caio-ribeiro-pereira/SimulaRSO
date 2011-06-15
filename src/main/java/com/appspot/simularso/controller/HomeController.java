package com.appspot.simularso.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.appspot.simularso.infra.Idioma;

@Resource
public class HomeController extends ApplicationController {

	private final Result result;

	public HomeController(Idioma idioma, Result result) {
		super(idioma);
		this.result = result;
	}

	@Get("/")
	public void home() {
	}

	@Post("/idioma")
	public void idioma(String idioma) {
		super.setIdioma(idioma);
		result.redirectTo(this).home();
	}

	public String getIdioma() {
		return super.getIdioma();
	}
}