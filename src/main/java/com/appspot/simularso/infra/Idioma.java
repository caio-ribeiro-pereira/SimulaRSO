package com.appspot.simularso.infra;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@SessionScoped
@Component
public class Idioma {

	private static final String DEFAULT = "pt_BR";
	private String idioma;

	public Idioma() {
		this.idioma = DEFAULT;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		if (idioma == null || idioma.isEmpty())
			idioma = DEFAULT;
		this.idioma = idioma;
	}

}
