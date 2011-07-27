package com.appspot.simularso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.appspot.simularso.facade.PaginacaoFacade;
import com.appspot.simularso.infra.Idioma;
import com.appspot.simularso.infra.Notice;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaAlgoritmo;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaBase;

@Resource
public class PaginacaoController extends ApplicationController {

	private final PaginacaoFacade facade;

	public PaginacaoController(Result result, PaginacaoFacade facade, Idioma idioma, Notice notice) {
		super(result, notice, idioma);
		this.facade = facade;
	}

	@Get("/paginacao-memoria")
	public void paginacaoInicio(List<PaginacaoMemoriaAlgoritmo> algs, List<Integer> stringRef, Integer frames, int modo, int total) {
		result.include("algs", algs);
		result.include("stringRef", stringRef);
		result.include("frames", frames);
		result.include("modo", modo);
		result.include("total", total);
	}

	@Post("/executar-paginacao-memoria")
	public void paginacaoExecutar(List<PaginacaoMemoriaAlgoritmo> algs, List<Integer> stringRef, Integer frames, int modo, int total) {
		try {
			if (entradaEstaValida(algs, stringRef, frames, modo, total)) {
				ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = facade.executar(algs, stringRef, frames, modo);
				result.include("resultadosDosAlgoritmos", resultadosDosAlgoritmos);
				result.redirectTo(this).paginacaoResultado();
			} else {
				result.forwardTo(this).paginacaoInicio(algs, stringRef, frames, modo, total);
			}
		} catch (Exception e) {
			notice.warning("misc.falha");
			result.forwardTo(this).paginacaoInicio(algs, stringRef, frames, modo, total);
		}
	}

	@Get("/resultado-paginacao-memoria")
	public void paginacaoResultado() {
		if (!result.included().containsKey("resultadosDosAlgoritmos")) {
			notice.warning("paginacao.selecione");
			result.forwardTo(this).paginacaoInicio(null, null, null, 0, 0);
		}
	}

	private boolean entradaEstaValida(List<PaginacaoMemoriaAlgoritmo> algs, List<Integer> stringRef, Integer frames, int modo, int total) {
		if (algs == null || algs.isEmpty() || algs.size() != modo) {
			notice.warning("misc.selecionar.algoritmos");
			return false;
		}
		if (algs.size() == 2 && algs.get(0).equals(algs.get(1))) {
			notice.warning("misc.algoritmo.erro");
			return false;
		}
		if (frames == null || frames < PaginacaoMemoriaBase.MIN_FRAME) {
			notice.warning("paginacao.frame.minimo");
			return false;
		}
		if (stringRef == null || stringRef.size() <= 0 || stringRef.size() != total) {
			notice.warning("paginacao.string.referencia.invalida");
			return false;
		}
		return true;
	}

	public PaginacaoMemoriaAlgoritmo[] getAlgoritmos() {
		return super.getPaginacaoMemoriaAlgoritmo();
	}

	public String getIdioma() {
		return super.idioma.getIdioma();
	}

}