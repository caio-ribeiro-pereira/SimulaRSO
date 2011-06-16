package com.appspot.simularso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.appspot.simularso.exception.CilindroCabecaVaziaException;
import com.appspot.simularso.exception.RequisicaoCilindroException;
import com.appspot.simularso.exception.RequisicoesVaziaException;
import com.appspot.simularso.facade.DiscoFacade;
import com.appspot.simularso.infra.Idioma;
import com.appspot.simularso.infra.Notice;
import com.appspot.simularso.logic.disc.EscalonadorDiscoAlgoritmo;
import com.appspot.simularso.model.Disco;

@Resource
public class DiscoController extends ApplicationController {

	private final Result result;
	private final DiscoFacade discoFacade;
	private final Notice notice;

	public DiscoController(Result result, DiscoFacade discoFacade, Idioma idioma, Notice notice) {
		super(idioma);
		this.result = result;
		this.discoFacade = discoFacade;
		this.notice = notice;
	}

	@Get("/escalonamento-disco")
	public void discoInicio() {
	}

	@Post("/executar-escalonamento-disco")
	public void discoExecutar(List<EscalonadorDiscoAlgoritmo> algDisco, LinkedList<Disco> requisicoes, Disco cabeca, int modo) {
		try {
			ArrayList<HashMap<String, Object>> resultadoDosAlgoritmos = discoFacade.executar(algDisco, requisicoes, cabeca, modo);
			result.include("resultadoDosAlgoritmos", resultadoDosAlgoritmos);
			result.redirectTo(this).discoResultado();

		} catch (CilindroCabecaVaziaException e) {
			notice.warning("disco.cilindro.cabeca.vazio");
			result.of(this).discoInicio();
		} catch (RequisicoesVaziaException e) {
			notice.warning("disco.fila.vazia");
			result.of(this).discoInicio();
		} catch (RequisicaoCilindroException e) {
			notice.warning("disco.cilindros.vazio");
			result.of(this).discoInicio();
		} catch (IllegalArgumentException e) {
			notice.warning("misc.selecionar.algoritmos");
			result.of(this).discoInicio();
		} catch (IllegalStateException e) {
			notice.warning("misc.algoritmo.erro");
			result.of(this).discoInicio();
		} catch (Exception e) {
			notice.warning("misc.falha");
			result.of(this).discoInicio();
		}
	}

	@Get("/resultado-escalonamento-disco")
	public void discoResultado() {
		if (!result.included().containsKey("resultadoDosAlgoritmos")) {
			notice.warning("disco.selecione");
			result.of(this).discoInicio();
		}
	}

	public EscalonadorDiscoAlgoritmo[] getAlgoritmos() {
		return super.getEscalonadorDiscoAlgoritmo();
	}

	public String getIdioma() {
		return super.getIdioma();
	}
}