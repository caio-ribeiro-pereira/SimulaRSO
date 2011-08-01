package com.appspot.simularso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.appspot.simularso.facade.ProcessoFacade;
import com.appspot.simularso.infra.Idioma;
import com.appspot.simularso.infra.Notice;
import com.appspot.simularso.logic.process.EscalonadorProcessoAlgoritmo;
import com.appspot.simularso.model.Processo;

@Resource
public class ProcessoController extends ApplicationController {

	private final ProcessoFacade facade;

	public ProcessoController(Result result, ProcessoFacade facade, Idioma idioma, Notice notice) {
		super(result, notice, idioma);
		this.facade = facade;
	}

	@Get("/escalonamento-processo")
	public void processoInicio(List<EscalonadorProcessoAlgoritmo> algs, ArrayList<Processo> pr, int qt, int contexto, int modo, int total) {
		result.include("algs", algs);
		result.include("pr", pr);
		result.include("qt", qt);
		result.include("contexto", contexto);
		result.include("modo", modo);
		result.include("total", total);
	}

	@Post("/executar-escalonamento-processo")
	public void processoExecutar(List<EscalonadorProcessoAlgoritmo> algs, ArrayList<Processo> pr, int qt, int contexto, int modo, int total) {
		try {
			if (entradaEstaValida(algs, pr, qt, contexto, modo, total)) {
				ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = facade.executar(algs, pr, qt, contexto, modo);
				result.include("resultadosDosAlgoritmos", resultadosDosAlgoritmos);
				result.redirectTo(this).processoResultado();
			} else {
				result.forwardTo(this).processoInicio(algs, pr, qt, contexto, modo, total);
			}
		} catch (Exception e) {
			notice.warning("misc.falha");
			result.forwardTo(this).processoInicio(algs, pr, qt, contexto, modo, total);
		}
	}

	@Get("/resultado-escalonamento-processo")
	public void processoResultado() {
		if (!result.included().containsKey("resultadosDosAlgoritmos")) {
			notice.warning("processo.selecione");
			result.forwardTo(this).processoInicio(null, null, 0, 0, 0, 0);
		}
	}

	private boolean entradaEstaValida(List<EscalonadorProcessoAlgoritmo> algs, ArrayList<Processo> pr, int qt, int contexto, int modo,
			int total) {
		if (algs == null || algs.isEmpty() || algs.size() != modo) {
			notice.warning("misc.selecionar.algoritmos");
			return false;
		}
		if (algs.size() == 2 && algs.get(0).equals(algs.get(1))) {
			notice.warning("misc.algoritmo.erro");
			return false;
		}
		if (pr == null || pr.isEmpty() || total <= 0) {
			notice.warning("processo.nao.carregado");
			return false;
		}
		if (contexto < 0) {
			notice.warning("processo.tempo.contexto.negativo");
			return false;
		}
		if (algs.contains(EscalonadorProcessoAlgoritmo.RR) && qt <= 0) {
			notice.warning("processo.tempo.corte.vazio");
			return false;
		}
		for (Processo processo : pr) {
			if (processo.getBurstTotal() <= 0 || processo.getChegada() < 0 || processo.getPrioridade() < 0) {
				notice.warning("processo.processos.vazio");
				return false;
			}
		}
		return true;
	}

	public EscalonadorProcessoAlgoritmo[] getAlgoritmos() {
		return super.getEscalonadorProcessoAlgoritmo();
	}

	public String getIdioma() {
		return super.idioma.getIdioma();
	}
}