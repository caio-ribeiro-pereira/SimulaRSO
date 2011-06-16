package com.appspot.simularso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.appspot.simularso.exception.ProcessosConfiguracaoException;
import com.appspot.simularso.exception.ProcessosNaoCarregadosException;
import com.appspot.simularso.exception.TempoQuantumException;
import com.appspot.simularso.facade.ProcessoFacade;
import com.appspot.simularso.infra.Idioma;
import com.appspot.simularso.infra.Notice;
import com.appspot.simularso.logic.process.EscalonadorProcessoAlgoritmo;
import com.appspot.simularso.model.Processo;

@Resource
public class ProcessoController extends ApplicationController {

	private final Result result;
	private final ProcessoFacade facade;
	private final Notice notice;

	public ProcessoController(Result result, ProcessoFacade facade, Idioma idioma, Notice notice) {
		super(idioma);
		this.result = result;
		this.facade = facade;
		this.notice = notice;
	}

	@Get("/escalonamento-processo")
	public void processoInicio() {
	}

	@Post("/executar-escalonamento-processo")
	public void processoExecutar(List<EscalonadorProcessoAlgoritmo> algs, ArrayList<Processo> pr, int qt, int modo) {
		try {

			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = facade.executar(algs, pr, qt, modo);
			result.include("resultadosDosAlgoritmos", resultadosDosAlgoritmos);
			result.redirectTo(this).processoResultado();

		} catch (ProcessosConfiguracaoException e) {
			notice.warning("processo.processos.vazio");
			result.of(this).processoInicio();
		} catch (ProcessosNaoCarregadosException e) {
			notice.warning("processo.nao.carregado");
			result.of(this).processoInicio();
		} catch (TempoQuantumException e) {
			notice.warning("processo.tempo.corte.vazio");
			result.of(this).processoInicio();
		} catch (IllegalArgumentException e) {
			notice.warning("misc.selecionar.algoritmos");
			result.of(this).processoInicio();
		} catch (IllegalStateException e) {
			notice.warning("misc.algoritmo.erro");
			result.of(this).processoInicio();
		} catch (Exception e) {
			notice.warning("misc.falha");
			result.of(this).processoInicio();
		}
	}

	@Get("/resultado-escalonamento-processo")
	public void processoResultado() {
		if (!result.included().containsKey("resultadosDosAlgoritmos")) {
			notice.warning("processo.selecione");
			result.of(this).processoInicio();
		}
	}

	public EscalonadorProcessoAlgoritmo[] getAlgoritmos() {
		return super.getEscalonadorProcessoAlgoritmo();
	}

	public String getIdioma() {
		return super.getIdioma();
	}
}