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
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;

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
		result.include("modosDeSimulacao", ModoSimulacao.values());
		result.include("algoritmosDeProcesso", AlgoritmoProcesso.values());
	}

	@Get("/escalonamento-disco")
	public void escalonamentoDisco() {

	}

	@Get("/executar-escalonamento-processo")
	public void executarEscalonamentoProcesso(List<AlgoritmoProcesso> alg, ArrayList<Processo> pr, int qt) {
		try {
			TreeMap<String, Object> resultadoMap = executor.executar(alg.get(0), pr, qt);
			result.use(Results.json()).from(resultadoMap, "resultadoMap").serialize();
		} catch (NegativoBurstException e) {
			validator.add(new ValidationMessage("Algum dos processos possui um Burst com valor negativo.", ""));
			validator.onErrorUse(Results.json()).from(null, "resultadoMap").serialize();
		} catch (NegativoQuantumException e) {
			validator.add(new ValidationMessage("Foi definido um valor negativo no tempo quantum do algoritmo.", ""));
			validator.onErrorUse(Results.json()).from(null, "resultadoMap").serialize();
		} catch (ProcessosNaoCarregadosException e) {
			validator.add(new ValidationMessage("Nenhum processo foi carregado.", ""));
			validator.onErrorUse(Results.json()).from(null, "resultadoMap").serialize();
		} catch (Exception e) {
			validator.add(new ValidationMessage("Ocorreu um erro inesperado no aplicativo.", ""));
			validator.onErrorUse(Results.json()).from(null, "resultadoMap").serialize();
		}
	}

	@Get("/executar-escalonamento-disco")
	public void executarEscalonamentoDisco(List<Processo> processos) {

	}

}
