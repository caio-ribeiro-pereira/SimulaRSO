package appspot.simulaedp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import appspot.simulaedp.exception.ProcessosNaoCarregadosException;
import appspot.simulaedp.exception.TempoQuantumException;
import appspot.simulaedp.logic.Executor;
import appspot.simulaedp.model.Processo;
import appspot.simulaedp.model.Processo.AlgoritmoProcesso;
import br.com.caelum.vraptor.Get;
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

	@Get("/")
	public void index() {

	}

	@Get("/sobre")
	public void sobre() {

	}

	@Get("/escalonamento-processo")
	public void escalonamentoProcesso() {
		result.include("algoritmoProcesso", AlgoritmoProcesso.values());
	}

	@Post("/executar-escalonamento-processo")
	public void executarEscalonamentoProcesso(ArrayList<AlgoritmoProcesso> alg, ArrayList<Processo> pr, int qt) {
		try {

			TreeMap<String, Object> resultadoProcesso = executor.executar(alg, pr, qt);
			result.include("algoritmo", alg);
			result.include("resultadoProcesso", resultadoProcesso);
			result.redirectTo(this).resultadoEscalonamentoProcesso();

		} catch (ProcessosNaoCarregadosException e) {
			validator.add(new ValidationMessage("Alguns processos não foram carregados corretamente.", ""));
			validator.onErrorRedirectTo(this).escalonamentoProcesso();

		} catch (TempoQuantumException e) {
			validator.add(new ValidationMessage("Não definido um tempo de corte para o algoritmo.", ""));
			validator.onErrorRedirectTo(this).escalonamentoProcesso();

		} catch (IllegalArgumentException e) {
			validator.add(new ValidationMessage("Nenhum algoritmo foi selecionado.", ""));
			validator.onErrorRedirectTo(this).escalonamentoProcesso();
		}
	}

	@Get("/resultado-escalonamento-processo")
	public void resultadoEscalonamentoProcesso() {
		if (!result.included().containsKey("resultadoProcesso")) {
			validator.add(new ValidationMessage("Configure um algoritmo para simular um escalonamento.", ""));
			validator.onErrorRedirectTo(this).escalonamentoProcesso();
		}
	}

	@Get("/escalonamento-disco")
	public void escalonamentoDisco() {

	}

	@Get("/executar-escalonamento-disco")
	public void executarEscalonamentoDisco(List<Processo> processos) {

	}

}
