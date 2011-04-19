package com.appspot.simularso.util;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@SessionScoped
@Component
public class Idioma {

	private String lang;

	public Idioma() {
	}

	public String getIdioma() {
		if (this.lang == null && this.lang.isEmpty()) {
			this.lang = "pt-BR";
		}
		return this.lang;
	}

	public void setIdioma(String lang) {
		this.lang = lang;
	}
}