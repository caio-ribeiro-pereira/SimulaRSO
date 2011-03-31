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
import com.appspot.simularso.paginator.logic.PaginacaoMemoriaVirtual.AlgoritmoPaginacao;

@Resource
public class PaginacaoController {

	private final Result result;
	private final Validator validator;
	private final PaginacaoFacade facade;

	public PaginacaoController(Result result, Validator validator, PaginacaoFacade facade) {
		this.result = result;
		this.validator = validator;
		this.facade = facade;
	}

	@Get("/paginacao-memoria-virtual")
	public void paginacaoInicio() {
		result.include("algoritmoPaginacao", AlgoritmoPaginacao.values());
	}

	@Post("/executar-paginacao-memoria-virtual")
	public void paginacaoExecutar(ArrayList<AlgoritmoPaginacao> algs, List<Integer> stringRef, Integer frames) {
		try {

			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = facade.executar(algs, stringRef, frames);
			result.include("resultadosDosAlgoritmos", resultadosDosAlgoritmos);
			result.redirectTo(this).paginacaoResultado();

		} catch (IllegalArgumentException e) {

			validator.add(new ValidationMessage("Nenhum algoritmo foi selecionado.", ""));
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

	@Get("/resultado-paginacao-memoria-virtual")
	public void paginacaoResultado() {
		if (!result.included().containsKey("resultadosDosAlgoritmos")) {
			validator.add(new ValidationMessage("Selecione um algoritmo para simular uma paginação de memória virtual.", ""));
			validator.onErrorRedirectTo(this).paginacaoInicio();
		}
	}
}
