package com.appspot.simularso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.appspot.simularso.exception.FramesInvalidoException;
import com.appspot.simularso.exception.StringReferenciaInvalidaException;
import com.appspot.simularso.facade.PaginacaoFacade;
import com.appspot.simularso.infra.Idioma;
import com.appspot.simularso.infra.Notice;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaAlgoritmo;

@Resource
public class PaginacaoController extends ApplicationController {

	private final Result result;
	private final PaginacaoFacade facade;
	private final Notice notice;

	public PaginacaoController(Result result, PaginacaoFacade facade, Idioma idioma, Notice notice) {
		super(idioma);
		this.result = result;
		this.facade = facade;
		this.notice = notice;
	}

	@Get("/paginacao-memoria")
	public void paginacaoInicio() {
	}

	@Post("/executar-paginacao-memoria")
	public void paginacaoExecutar(List<PaginacaoMemoriaAlgoritmo> algs, List<Integer> stringRef, Integer frames, int modo) {
		try {

			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = facade.executar(algs, stringRef, frames, modo);
			result.include("resultadosDosAlgoritmos", resultadosDosAlgoritmos);
			result.redirectTo(this).paginacaoResultado();

		} catch (StringReferenciaInvalidaException e) {
			notice.warning("paginacao.string.referencia.invalida");
			result.of(this).paginacaoInicio();
		} catch (FramesInvalidoException e) {
			notice.warning("paginacao.frame.minimo");
			result.of(this).paginacaoInicio();
		} catch (IllegalArgumentException e) {
			notice.warning("misc.selecionar.algoritmos");
			result.of(this).paginacaoInicio();
		} catch (IllegalStateException e) {
			notice.warning("misc.algoritmo.erro");
			result.of(this).paginacaoInicio();
		} catch (Exception e) {
			notice.warning("misc.falha");
			result.of(this).paginacaoInicio();
		}
	}

	@Get("/resultado-paginacao-memoria")
	public void paginacaoResultado() {
		if (!result.included().containsKey("resultadosDosAlgoritmos")) {
			notice.warning("paginacao.selecione");
			result.of(this).paginacaoInicio();
		}
	}

	public PaginacaoMemoriaAlgoritmo[] getAlgoritmos() {
		return super.getPaginacaoMemoriaAlgoritmo();
	}

	public String getIdioma() {
		return super.getIdioma();
	}

}