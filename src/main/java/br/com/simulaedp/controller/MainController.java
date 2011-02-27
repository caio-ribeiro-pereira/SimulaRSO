package br.com.simulaedp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.simulaedp.logic.EscalonadorExecutor;
import br.com.simulaedp.model.Algoritmo;
import br.com.simulaedp.model.ModelGenerator;
import br.com.simulaedp.model.Processo;
import br.com.simulaedp.util.InputValidator;

@Resource
public class MainController {

	private EscalonadorExecutor executor;
	private final Result result;
	private final InputValidator inputValidator;
	private final ModelGenerator generator;

	public MainController(Result result, EscalonadorExecutor executor,
			InputValidator inputValidator, ModelGenerator generator) {
		this.result = result;
		this.executor = executor;
		this.inputValidator = inputValidator;
		this.generator = generator;
	}

	@Path("/")
	@Get
	public void index() {

	}

	@Path("/sobre")
	@Get
	public void sobre() {

	}

	@Path("/escalonamento-processo")
	@Get
	public void escalonamentoProcesso() {

	}

	@Path("/escalonamento-disco")
	@Get
	public void escalonamentoDisco() {

	}

	@Path("/executar-escalonamento-processo")
	@Get
	public void executarEscalonamentoProcesso(int total, Algoritmo algoritmo,
			int[] bursts, int[] chegadas, int[] prioridades, int quantum) {

		inputValidator.validar(total, algoritmo, bursts, chegadas, prioridades,
				quantum);

		ArrayList<Processo> processos = generator.gerarListaDeProcessos(total,
				bursts, chegadas, prioridades);

		executor.executar(algoritmo, processos, quantum);

		TreeMap<String, Object> resultado = executor.getResultado();

		result.use(Results.json()).from(resultado).serialize();
	}

	@Path("/executar-escalonamento-disco")
	@Get
	public void executarEscalonamentoDisco(List<Processo> processos) {

	}

}
