package com.appspot.simularso.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MainController {

	private final Result result;

	public MainController(Result result) {
		this.result = result;
	}

	@Get("/")
	public void index() {
		result.redirectTo(this).home();
	}

	@Get("/home/")
	public void home() {
	}

	@Get("/sobre")
	public void sobre() {

	}
}