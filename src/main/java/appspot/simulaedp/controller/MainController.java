package appspot.simulaedp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import appspot.simulaedp.exception.NegativoBurstException;
import appspot.simulaedp.exception.NegativoQuantumException;
import appspot.simulaedp.exception.ProcessosNaoCarregadosException;
import appspot.simulaedp.logic.Escalonador.ModoSimulacao;
import appspot.simulaedp.logic.Executor;
import appspot.simulaedp.model.Processo;
import appspot.simulaedp.model.Processo.AlgoritmoProcesso;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class MainController {

	private final Result result;
	private final Validator validator;
	private final Executor executor;

	public MainController(Result result, Validator validator, Executor executor) {
		this.result = result;
		this.validator = validator;
		this.executor = executor;
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

	@Path("/resultado-escalonamento-processo")
	@Get
	public void resultadoEscalonamentoProcesso(TreeMap<String, Object> resultadoMap) {
		result.include("resultadoMap", resultadoMap);
	}

	@Path("/escalonamento-disco")
	@Get
	public void escalonamentoDisco() {

	}

	@Path("/executar-escalonamento-processo")
	@Post
	public void executarEscalonamentoProcesso(ModoSimulacao modo, AlgoritmoProcesso algoritmo,
			ArrayList<Processo> processos, int quantum) {
		try {
			TreeMap<String, Object> resultadoMap = executor.executar(algoritmo, processos, quantum);
			result.redirectTo(this).resultadoEscalonamentoProcesso(resultadoMap);
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
