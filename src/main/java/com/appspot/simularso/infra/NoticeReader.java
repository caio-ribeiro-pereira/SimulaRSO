package com.appspot.simularso.infra;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class NoticeReader implements Serializable {

	private static final long serialVersionUID = 5529825393451861845L;
	private static final String PROPERTIES = "idioma";
	private ResourceBundle bundle;

	public NoticeReader(ProjectLocale projectLocale) {
		Locale locale = new Locale(projectLocale.getLanguage(), projectLocale.getCountry());
		bundle = ResourceBundle.getBundle(PROPERTIES, locale);
	}

	public String find(String key) {
		if (!bundle.containsKey(key)) {
			return "Incluir properties: " + key;
		}
		return bundle.getString(key);
	}

}
