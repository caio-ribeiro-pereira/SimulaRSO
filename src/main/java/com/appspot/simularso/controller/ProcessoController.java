package com.appspot.simularso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.appspot.simularso.exception.ProcessosConfiguracaoException;
import com.appspot.simularso.exception.ProcessosNaoCarregadosException;
import com.appspot.simularso.exception.TempoQuantumException;
import com.appspot.simularso.facade.ProcessoFacade;
import com.appspot.simularso.logic.process.EscalonadorProcessoAlgoritmo;
import com.appspot.simularso.model.Processo;

@Resource
public class ProcessoController {

	private final Result result;
	private final Validator validator;
	private final ProcessoFacade facade;

	public ProcessoController(Result result, Validator validator, ProcessoFacade facade) {
		this.result = result;
		this.validator = validator;
		this.facade = facade;
	}

	@Get("/escalonamento-processo")
	public void processoInicio() {
		result.include("escalonadorProcessoAlgoritmo", EscalonadorProcessoAlgoritmo.values());
	}

	@Post("/executar-escalonamento-processo")
	public void processoExecutar(List<EscalonadorProcessoAlgoritmo> algs, ArrayList<Processo> pr, int qt, int modo) {
		try {

			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = facade.executar(algs, pr, qt, modo);
			result.include("resultadosDosAlgoritmos", resultadosDosAlgoritmos);
			result.redirectTo(this).processoResultado();

		} catch (ProcessosConfiguracaoException e) {

			validator.add(new ValidationMessage("Alguns processos não foram configurados corretamente.", ""));
			validator.onErrorRedirectTo(this).processoInicio();

		} catch (ProcessosNaoCarregadosException e) {

			validator.add(new ValidationMessage("Nenhum processo foi carregado..", ""));
			validator.onErrorRedirectTo(this).processoInicio();

		} catch (TempoQuantumException e) {

			validator.add(new ValidationMessage("Não definido um tempo de corte para este algoritmo.", ""));
			validator.onErrorRedirectTo(this).processoInicio();

		} catch (IllegalArgumentException e) {

			validator.add(new ValidationMessage("Selecione os algoritmos.", ""));
			validator.onErrorRedirectTo(this).processoInicio();
		} catch (Exception e) {

			validator.add(new ValidationMessage("Ocorreu uma falha na execução do algoritmo.", ""));
			validator.onErrorRedirectTo(this).processoInicio();
		}
	}

	@Get("/resultado-escalonamento-processo")
	public void processoResultado() {
		if (!result.included().containsKey("resultadosDosAlgoritmos")) {
			validator.add(new ValidationMessage("Selecione um algoritmo para simular um escalonamento.", ""));
			validator.onErrorRedirectTo(this).processoInicio();
		}
	}
}