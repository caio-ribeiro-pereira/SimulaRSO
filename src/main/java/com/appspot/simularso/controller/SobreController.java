package com.appspot.simularso.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

import com.appspot.simularso.infra.ProjectLocale;

@Resource
public class SobreController {

	private final ProjectLocale locale;

	public SobreController(ProjectLocale locale) {
		this.locale = locale;
	}

	@Get("/sobre")
	public void sobre() {
	}

	public String getIdioma() {
		return locale.getIdioma();
	}
}
