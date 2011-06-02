package com.appspot.simularso.infra;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class NoticeReader implements Serializable {

	private static final long serialVersionUID = -4051745102251095713L;

	private static final String PROPERTIES = "idioma";
	private ResourceBundle bundle;
	private final Idioma idioma;

	public NoticeReader(Idioma idioma) {
		this.idioma = idioma;
	}

	@PostConstruct
	public void init() {
		Locale locale = new Locale(idioma.getLanguage(), idioma.getCountry());
		bundle = ResourceBundle.getBundle(PROPERTIES, locale);
	}

	public String find(String key) {
		if (!bundle.containsKey(key)) {
			return "Incluir properties: " + key;
		}
		return bundle.getString(key);
	}

	@PreDestroy
	public void destroy() {
		bundle = null;
	}
}
