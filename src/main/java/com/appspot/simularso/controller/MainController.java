package com.appspot.simularso.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MainController {

	private final Result result;
	private HttpServletRequest request;

	public MainController(Result result, HttpServletRequest request) {
		this.result = result;
		this.request = request;
	}

	@Get("/")
	public void index() {
		String idioma = request.getParameter("idioma");
		Locale locale = new Locale(idioma);
		Config.set(request.getSession(), Config.FMT_LOCALE, locale);
		Config.set(request.getSession(), Config.FMT_FALLBACK_LOCALE, locale);
		result.redirectTo(this).home();
	}

	@Get("/home/")
	public void home() {
	}

	@Get("/sobre")
	public void sobre() {

	}
}