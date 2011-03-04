package br.com.simulaedp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.simulaedp.exception.NegativoBurstException;
import br.com.simulaedp.exception.NegativoQuantumException;
import br.com.simulaedp.exception.ProcessosNaoCarregadosException;
import br.com.simulaedp.logic.SchedulerExecutor;
import br.com.simulaedp.model.Processo;
import br.com.simulaedp.model.enumerator.AlgoritmoProcesso;
import br.com.simulaedp.model.enumerator.ModoSimulacao;
import br.com.simulaedp.util.Generator;

@Resource
public class MainController {

	private SchedulerExecutor executor;
	private final Result result;
	private final Generator generator;
	private final Validator validator;

	public MainController(Result result, SchedulerExecutor executor, Generator generator, Validator validator) {
		this.result = result;
		this.executor = executor;
		this.generator = generator;
		this.validator = validator;
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
		result.include("modosDeSimulacao", ModoSimulacao.values());
		result.include("algoritmosDeProcesso", AlgoritmoProcesso.values());
	}

	@Path("/escalonamento-disco")
	@Get
	public void escalonamentoDisco() {

	}

	@Path("/executar-escalonamento-processo")
	@Post
	public void executarEscalonamentoProcesso(int total, AlgoritmoProcesso algoritmo, int[] bursts, int[] chegadas, int[] prioridades,
			int quantum) {
		try {
			ArrayList<Processo> processos = generator.gerarListaDeProcessos(total, bursts, chegadas, prioridades);

			executor.executar(algoritmo, processos, quantum);

			TreeMap<String, Object> resultado = executor.getResultado();

			result.use(Results.json()).from(resultado).serialize();
		} catch (NegativoBurstException e) {
			validator.add(new ValidationMessage("Algum dos processos possui um Burst com valor negativo.", ""));
			validator.onErrorUsePageOf(this).escalonamentoProcesso();
		} catch (NegativoQuantumException e) {
			validator.add(new ValidationMessage("Foi definido um valor negativo no tempo quantum do algoritmo.", ""));
			validator.onErrorUsePageOf(this).escalonamentoProcesso();
		} catch (ProcessosNaoCarregadosException e) {
			validator.add(new ValidationMessage("Nenhum processo foi carregado.", ""));
			validator.onErrorUsePageOf(this).escalonamentoProcesso();
		} catch (Exception e) {
			validator.add(new ValidationMessage("Ocorreu um erro inesperado no aplicativo.", ""));
			validator.onErrorUsePageOf(this).escalonamentoProcesso();
		}
	}

	@Path("/executar-escalonamento-disco")
	@Get
	public void executarEscalonamentoDisco(List<Processo> processos) {

	}

}
