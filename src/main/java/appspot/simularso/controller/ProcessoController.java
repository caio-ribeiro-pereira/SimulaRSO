package appspot.simularso.controller;

import java.util.ArrayList;
import java.util.HashMap;

import appspot.simularso.exception.ProcessosNaoCarregadosException;
import appspot.simularso.exception.TempoQuantumException;
import appspot.simularso.logic.Executor;
import appspot.simularso.logic.Escalonador.AlgoritmoProcesso;
import appspot.simularso.model.Processo;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class ProcessoController {

	private final Result result;
	private final Validator validator;
	private final Executor executor;

	public ProcessoController(Result result, Validator validator, Executor executor) {
		this.result = result;
		this.validator = validator;
		this.executor = executor;
	}

	@Get("/escalonamento-processo")
	public void processoInicio() {
		result.include("algoritmoProcesso", AlgoritmoProcesso.values());
	}

	@Post("/executar-escalonamento-processo")
	public void processoExecutar(ArrayList<AlgoritmoProcesso> algs, ArrayList<Processo> pr, int qt) {
		try {
			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = new ArrayList<HashMap<String, Object>>();
			for (AlgoritmoProcesso alg : algs) {
				HashMap<String, Object> resultado = executor.executar(alg, pr, qt);
				resultadosDosAlgoritmos.add(resultado);
			}
			resultadosDosAlgoritmos.trimToSize();
			result.include("resultadosDosAlgoritmos", resultadosDosAlgoritmos);
			result.redirectTo(this).processoResultado();

		} catch (ProcessosNaoCarregadosException e) {
			validator.add(new ValidationMessage("Alguns processos não foram configurados corretamente.", ""));
			validator.onErrorRedirectTo(this).processoInicio();

		} catch (TempoQuantumException e) {
			validator.add(new ValidationMessage("Nao definido um tempo de corte para o algoritmo.", ""));
			validator.onErrorRedirectTo(this).processoInicio();

		} catch (IllegalArgumentException e) {
			validator.add(new ValidationMessage("Nenhum algoritmo foi selecionado.", ""));
			validator.onErrorRedirectTo(this).processoInicio();
		}
	}

	@Get("/resultado-escalonamento-processo")
	public void processoResultado() {
		if (!result.included().containsKey("resultadosDosAlgoritmos")) {
			validator.add(new ValidationMessage("Configure um algoritmo para simular um escalonamento.", ""));
			validator.onErrorRedirectTo(this).processoInicio();
		}
	}
}