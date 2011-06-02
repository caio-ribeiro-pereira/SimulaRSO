package com.appspot.simularso.infra;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class Notice {

	private static final String NOTICE = "notices";

	private final Result result;
	private final HttpServletRequest request;
	private final NoticeReader reader;

	public Notice(Result result, HttpServletRequest request, NoticeReader reader) {
		this.result = result;
		this.request = request;
		this.reader = reader;
	}

	public void success(String key, Object... args) {
		notice("success", key, args);
	}

	public void warning(String key, Object... args) {
		notice("warning", key, args);
	}

	public void info(String key, Object... args) {
		notice("info", key, args);
	}

	public void failure(String key, Object... args) {
		notice("failure", key, args);
	}

	private void notice(String type, String key, Object... args) {
		Map<String, String> notices = (Map<String, String>) request
				.getAttribute(NOTICE);

		if (notices == null) {
			notices = new HashMap<String, String>();
		}

		String msg = MessageFormat.format(reader.find(key), args);
		notices.put(type, msg);

		result.include(NOTICE, notices);
	}
}
