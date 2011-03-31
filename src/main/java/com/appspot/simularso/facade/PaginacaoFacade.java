package com.appspot.simularso.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.paginator.logic.PaginacaoMemoriaVirtual;
import com.appspot.simularso.paginator.logic.PaginacaoMemoriaVirtual.AlgoritmoPaginacao;
import com.appspot.simularso.paginator.logic.impl.FIFO;
import com.appspot.simularso.paginator.logic.impl.OPT;

@Component
public class PaginacaoFacade implements Serializable {

	private static final long serialVersionUID = -9131767706141519250L;

	public ArrayList<HashMap<String, Object>> executar(final ArrayList<AlgoritmoPaginacao> algs, final List<Integer> stringRef,
			final Integer frames) {

		if (algs != null && !algs.isEmpty()) {
			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = new ArrayList<HashMap<String, Object>>();

			for (AlgoritmoPaginacao alg : algs) {
				HashMap<String, Object> resultado = gerarResultado(alg, stringRef, frames);
				resultadosDosAlgoritmos.add(resultado);
			}

			resultadosDosAlgoritmos.trimToSize();
			return resultadosDosAlgoritmos;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private HashMap<String, Object> gerarResultado(final AlgoritmoPaginacao algortimo, final List<Integer> stringReferencia,
			final Integer frames) {
		PaginacaoMemoriaVirtual paginacao = null;
		if (algortimo == AlgoritmoPaginacao.FIFO) {
			paginacao = new FIFO(stringReferencia, frames);
		} else if (algortimo == AlgoritmoPaginacao.OPT) {
			paginacao = new OPT(stringReferencia, frames);
		} else {
			throw new IllegalArgumentException();
		}
		return gerarResultadoMap(paginacao);
	}

	private HashMap<String, Object> gerarResultadoMap(PaginacaoMemoriaVirtual paginacao) {
		HashMap<String, Object> resultado = new HashMap<String, Object>();
		resultado.put("resultadoGrafico", paginacao.resultadoGraficoFinal());
		resultado.put("stringReferencia", paginacao.stringReferencia());
		resultado.put("totalPageFault", paginacao.totalPageFault());
		resultado.put("algoritmoNome", paginacao.algoritmoNome());
		return resultado;
	}
}
