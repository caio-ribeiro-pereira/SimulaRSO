package com.appspot.simularso.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

import com.appspot.simularso.infra.Idioma;

@Resource
public class SobreController extends ApplicationController {

	public SobreController(Idioma idioma) {
		super(null, null, idioma);
	}

	@Get("/sobre")
	public void sobre() {
	}

	public String getIdioma() {
		return super.idioma.getIdioma();
	}
}
