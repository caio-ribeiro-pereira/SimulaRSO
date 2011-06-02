package com.appspot.simularso.infra;

import java.util.ResourceBundle;

import javax.annotation.PreDestroy;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class NoticeReader {

	private ResourceBundle bundle;

	public NoticeReader() {
		bundle = ResourceBundle.getBundle("idioma");
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
