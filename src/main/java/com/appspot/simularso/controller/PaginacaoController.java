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

import com.appspot.simularso.exception.FramesInvalidoException;
import com.appspot.simularso.exception.StringReferenciaInvalidaException;
import com.appspot.simularso.facade.PaginacaoFacade;
import com.appspot.simularso.infra.Idioma;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaAlgoritmo;

@Resource
public class PaginacaoController {

	private final Result result;
	private final Validator validator;
	private final PaginacaoFacade facade;
	private final Idioma idioma;

	public PaginacaoController(Result result, Validator validator, PaginacaoFacade facade, Idioma idioma) {
		this.result = result;
		this.validator = validator;
		this.facade = facade;
		this.idioma = idioma;
	}

	@Get("/paginacao-memoria/")
	public void paginacaoInicio() {
		result.include("paginacaoMemoriaAlgoritmo", PaginacaoMemoriaAlgoritmo.values());
	}

	@Post("/executar-paginacao-memoria")
	public void paginacaoExecutar(List<PaginacaoMemoriaAlgoritmo> algs, List<Integer> stringRef, Integer frames, int modo) {
		try {

			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = facade.executar(algs, stringRef, frames, modo);
			result.include("resultadosDosAlgoritmos", resultadosDosAlgoritmos);
			result.redirectTo(this).paginacaoResultado();

		} catch (IllegalArgumentException e) {

			validator.add(new ValidationMessage("Selecione os algoritmos.", ""));
			validator.onErrorRedirectTo(this).paginacaoInicio();

		} catch (StringReferenciaInvalidaException e) {

			validator.add(new ValidationMessage("String de Referência inválida.", ""));
			validator.onErrorRedirectTo(this).paginacaoInicio();

		} catch (FramesInvalidoException e) {

			validator.add(new ValidationMessage("O número de frames de alocação mínimo é 2.", ""));
			validator.onErrorRedirectTo(this).paginacaoInicio();

		} catch (Exception e) {

			validator.add(new ValidationMessage("Ocorreu uma falha na execução do algoritmo.", ""));
			validator.onErrorRedirectTo(this).paginacaoInicio();
		}
	}

	@Get("/resultado-paginacao-memoria")
	public void paginacaoResultado() {
		if (!result.included().containsKey("resultadosDosAlgoritmos")) {
			validator.add(new ValidationMessage("Selecione um algoritmo para simular uma paginação de memória.", ""));
			validator.onErrorRedirectTo(this).paginacaoInicio();
		}
	}

	public String getIdioma() {
		return idioma.getIdioma();
	}
}
