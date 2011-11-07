package com.appspot.simularso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.appspot.simularso.facade.DiscoFacade;
import com.appspot.simularso.infra.ProjectLocale;
import com.appspot.simularso.infra.Notice;
import com.appspot.simularso.logic.disc.EscalonadorDiscoAlgoritmo;
import com.appspot.simularso.logic.disc.EscalonadorDiscoBase;
import com.appspot.simularso.model.Disco;

@Resource
public class DiscoController {

	private final ProjectLocale locale;
	private final Notice notice;
	private final Result result;
	private final DiscoFacade discoFacade;

	public DiscoController(Result result, DiscoFacade discoFacade, ProjectLocale locale, Notice notice) {
		this.result = result;
		this.discoFacade = discoFacade;
		this.locale = locale;
		this.notice = notice;
	}

	@Get("/escalonamento-disco")
	public void discoInicio(List<EscalonadorDiscoAlgoritmo> algDisco, LinkedList<Disco> requisicoes, Disco cabeca, int modo, int total) {
		result.include("algDisco", algDisco);
		result.include("requisicoes", requisicoes);
		result.include("cabeca", cabeca);
		result.include("modo", modo);
		result.include("total", total);
	}

	@Post("/executar-escalonamento-disco")
	public void discoExecutar(List<EscalonadorDiscoAlgoritmo> algDisco, LinkedList<Disco> requisicoes, Disco cabeca, int modo, int total) {
		try {
			if (entradaEstaValida(algDisco, requisicoes, cabeca, modo, total)) {
				ArrayList<HashMap<String, Object>> resultadoDosAlgoritmos = discoFacade.executar(algDisco, requisicoes, cabeca, modo);
				result.include("resultadoDosAlgoritmos", resultadoDosAlgoritmos);
				result.redirectTo(this).discoResultado();
			} else {
				result.forwardTo(this).discoInicio(algDisco, requisicoes, cabeca, modo, total);
			}
		} catch (Exception e) {
			notice.warning("misc.falha");
			result.forwardTo(this).discoInicio(algDisco, requisicoes, cabeca, modo, total);
		}
	}

	@Get("/resultado-escalonamento-disco")
	public void discoResultado() {
		if (!result.included().containsKey("resultadoDosAlgoritmos")) {
			notice.warning("disco.selecione");
			result.forwardTo(this).discoInicio(null, null, null, 0, 0);
		}
	}

	private boolean entradaEstaValida(List<EscalonadorDiscoAlgoritmo> algs, LinkedList<Disco> requisicoes, Disco cabeca, int modo, int total) {
		if (algs == null || algs.isEmpty() || algs.size() != modo) {
			notice.warning("misc.selecionar.algoritmos");
			return false;
		}
		if (algs.size() == 2 && algs.get(0).equals(algs.get(1))) {
			notice.warning("misc.algoritmo.erro");
			return false;
		}
		if (requisicoes == null || requisicoes.isEmpty() || requisicoes.size() != total) {
			notice.warning("disco.fila.vazia");
			return false;
		}
		if (cabeca == null || cabeca.getCilindro() < EscalonadorDiscoBase.MIN) {
			notice.warning("disco.cilindro.cabeca.vazio");
			return false;
		}
		for (Disco disco : requisicoes) {
			if (disco.getCilindro() < EscalonadorDiscoBase.MIN) {
				notice.warning("disco.cilindros.vazio");
				return false;
			}
		}
		return true;
	}

	public EscalonadorDiscoAlgoritmo[] getAlgoritmos() {
		return EscalonadorDiscoAlgoritmo.values();
	}

	public String getIdioma() {
		return locale.getIdioma();
	}
}