package com.appspot.simularso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

import com.appspot.simularso.exception.ProcessosConfiguracaoException;
import com.appspot.simularso.exception.ProcessosNaoCarregadosException;
import com.appspot.simularso.exception.TempoQuantumException;
import com.appspot.simularso.facade.ProcessoFacade;
import com.appspot.simularso.infra.Idioma;
import com.appspot.simularso.infra.Notice;
import com.appspot.simularso.logic.process.EscalonadorProcessoAlgoritmo;
import com.appspot.simularso.model.Processo;

@Resource
public class ProcessoController {

	private final Result result;
	private final Validator validator;
	private final ProcessoFacade facade;
	private final Idioma idioma;
	private final Notice notice;

	public ProcessoController(Result result, Validator validator,
			ProcessoFacade facade, Idioma idioma, Notice notice) {
		this.result = result;
		this.validator = validator;
		this.facade = facade;
		this.idioma = idioma;
		this.notice = notice;
	}

	@Get("/escalonamento-processo")
	public void processoInicio() {
		result.include("escalonadorProcessoAlgoritmo",
				EscalonadorProcessoAlgoritmo.values());
	}

	@Post("/executar-escalonamento-processo")
	public void processoExecutar(List<EscalonadorProcessoAlgoritmo> algs,
			ArrayList<Processo> pr, int qt, int modo) {
		try {

			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = facade
					.executar(algs, pr, qt, modo);
			result.include("resultadosDosAlgoritmos", resultadosDosAlgoritmos);
			result.redirectTo(this).processoResultado();

		} catch (ProcessosConfiguracaoException e) {
			notice.warning("processo.processos.vazio");
			validator.onErrorRedirectTo(this).processoInicio();
		} catch (ProcessosNaoCarregadosException e) {
			notice.warning("processo.nao.carregado");
			validator.onErrorRedirectTo(this).processoInicio();
		} catch (TempoQuantumException e) {
			notice.warning("processo.tempo.corte.vazio");
			validator.onErrorRedirectTo(this).processoInicio();
		} catch (IllegalArgumentException e) {
			notice.warning("misc.selecionar.algoritmos");
			validator.onErrorRedirectTo(this).processoInicio();
		} catch (Exception e) {
			notice.warning("misc.falha");
			validator.onErrorRedirectTo(this).processoInicio();
		}
	}

	@Get("/resultado-escalonamento-processo")
	public void processoResultado() {
		if (!result.included().containsKey("resultadosDosAlgoritmos")) {
			notice.warning("processo.selecione");
			validator.onErrorRedirectTo(this).processoInicio();
		}
	}

	public String getIdioma() {
		return idioma.getIdioma();
	}
}