package com.appspot.simularso.infra;

import java.io.Serializable;
import java.text.MessageFormat;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class Notice implements Serializable {

	private static final long serialVersionUID = -5033032180310098585L;

	private static final String NOTICE = "warning";

	private final Result result;
	private final NoticeReader reader;

	public Notice(Result result, NoticeReader reader) {
		this.result = result;
		this.reader = reader;
	}

	public void warning(String key, Object... args) {
		String msg = MessageFormat.format(reader.find(key), args);
		result.include(NOTICE, msg);
	}
}
