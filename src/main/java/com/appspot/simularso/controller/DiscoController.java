package com.appspot.simularso.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;

@Resource
public class DiscoController {

	@Get("/escalonamento-disco")
	public void discoInicio() {

	}

	@Post("/executar-escalonamento-disco")
	public void discoExecutar() {
	}

	@Get("/resultado-escalonamento-disco")
	public void discoResultado() {
	}
}
