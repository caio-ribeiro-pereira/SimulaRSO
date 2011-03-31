package com.appspot.simularso.controller;

import java.util.ArrayList;
import java.util.HashMap;

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
import com.appspot.simularso.model.Processo;
import com.appspot.simularso.scheduler.process.logic.EscalonadorProcesso.AlgoritmoProcesso;

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
		result.include("algoritmoProcesso", AlgoritmoProcesso.values());
	}

	@Post("/executar-escalonamento-processo")
	public void processoExecutar(ArrayList<AlgoritmoProcesso> algs, ArrayList<Processo> pr, int qt) {
		try {

			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = facade.executar(algs, pr, qt);
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

			validator.add(new ValidationMessage("Nenhum algoritmo foi selecionado.", ""));
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