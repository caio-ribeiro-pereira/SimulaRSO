package com.appspot.simularso.infra;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@SessionScoped
@Component
public class Idioma implements Serializable {

	private static final long serialVersionUID = 2177438632064029504L;

	private static final String DEFAULT = "pt_BR";
	private String idioma = DEFAULT;

	public Idioma() {
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		if (idioma == null || idioma.isEmpty())
			idioma = DEFAULT;
		this.idioma = idioma;
	}

	public String getLanguage() {
		return this.idioma.split("(-|_)")[0];
	}

	public String getCountry() {
		return this.idioma.split("(-|_)")[1];
	}

}
