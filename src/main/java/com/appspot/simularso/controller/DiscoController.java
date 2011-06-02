package com.appspot.simularso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

import com.appspot.simularso.exception.CilindroCabecaVaziaException;
import com.appspot.simularso.exception.RequisicaoCilindroException;
import com.appspot.simularso.exception.RequisicoesVaziaException;
import com.appspot.simularso.facade.DiscoFacade;
import com.appspot.simularso.infra.Idioma;
import com.appspot.simularso.infra.Notice;
import com.appspot.simularso.logic.disc.EscalonadorDiscoAlgoritmo;
import com.appspot.simularso.model.Disco;

@Resource
public class DiscoController {

	private final Result result;
	private final Validator validator;
	private final DiscoFacade discoFacade;
	private final Idioma idioma;
	private final Notice notice;

	public DiscoController(Result result, Validator validator,
			DiscoFacade discoFacade, Idioma idioma, Notice notice) {
		this.result = result;
		this.validator = validator;
		this.discoFacade = discoFacade;
		this.idioma = idioma;
		this.notice = notice;
	}

	@Get("/escalonamento-disco")
	public void discoInicio() {
		result.include("algoritmoDisco", EscalonadorDiscoAlgoritmo.values());
	}

	@Post("/executar-escalonamento-disco")
	public void discoExecutar(List<EscalonadorDiscoAlgoritmo> algDisco,
			LinkedList<Disco> requisicoes, Disco cabeca, int modo) {
		try {
			ArrayList<HashMap<String, Object>> resultadoDosAlgoritmos = discoFacade
					.executar(algDisco, requisicoes, cabeca, modo);
			result.include("resultadoDosAlgoritmos", resultadoDosAlgoritmos);
			result.redirectTo(this).discoResultado();

		} catch (CilindroCabecaVaziaException e) {
			notice.warning("disco.cilindro.cabeca.vazio");
			validator.onErrorForwardTo(this).discoInicio();
		} catch (RequisicoesVaziaException e) {
			notice.warning("disco.fila.vazia");
			validator.onErrorForwardTo(this).discoInicio();
		} catch (RequisicaoCilindroException e) {
			notice.warning("disco.cilindros.vazio");
			validator.onErrorForwardTo(this).discoInicio();
		} catch (IllegalArgumentException e) {
			notice.warning("misc.selecionar.algoritmos");
			validator.onErrorForwardTo(this).discoInicio();
		} catch (Exception e) {
			notice.warning("misc.falha");
			validator.onErrorRedirectTo(this).discoInicio();
		}
	}

	@Get("/resultado-escalonamento-disco")
	public void discoResultado() {
		if (!result.included().containsKey("resultadoDosAlgoritmos")) {
			notice.warning("disco.selecione");
			validator.onErrorRedirectTo(this).discoInicio();
		}
	}

	public String getIdioma() {
		return idioma.getIdioma();
	}
}
