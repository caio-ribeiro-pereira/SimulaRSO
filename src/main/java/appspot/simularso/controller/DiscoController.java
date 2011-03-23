package appspot.simularso.controller;

import appspot.simularso.scheduler.process.logic.Executor;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class DiscoController {

	private final Result result;
	private final Validator validator;
	private final Executor executor;

	public DiscoController(Result result, Validator validator, Executor executor) {
		this.result = result;
		this.validator = validator;
		this.executor = executor;
	}

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
