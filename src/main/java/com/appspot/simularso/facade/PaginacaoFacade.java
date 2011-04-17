package com.appspot.simularso.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.logic.PaginacaoMemoria;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaAlgoritmo;
import com.appspot.simularso.util.EnumToClass;

@Component
public class PaginacaoFacade implements Serializable {

	private static final long serialVersionUID = 3719454176545234285L;
	private final EnumToClass enumToClass;

	public PaginacaoFacade(EnumToClass enumToClass) {
		this.enumToClass = enumToClass;
	}

	public ArrayList<HashMap<String, Object>> executar(final List<PaginacaoMemoriaAlgoritmo> algs,
			final List<Integer> stringRef, final Integer frames, final int modo) {

		if (algs != null && !algs.isEmpty() && algs.size() == modo) {
			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = new ArrayList<HashMap<String, Object>>();
			for (PaginacaoMemoriaAlgoritmo alg : algs) {
				HashMap<String, Object> resultado = gerarResultado(alg, stringRef, frames);
				resultadosDosAlgoritmos.add(resultado);
			}
			resultadosDosAlgoritmos.trimToSize();
			return resultadosDosAlgoritmos;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private HashMap<String, Object> gerarResultado(PaginacaoMemoriaAlgoritmo algoritmo, List<Integer> stringReferencia,
			Integer frames) {
		Class<?> klass = new Mirror().reflectClass(enumToClass.extractClassFromEnum(algoritmo));
		PaginacaoMemoria paginacao = (PaginacaoMemoria) new Mirror().on(klass).invoke().constructor()
				.withArgs(stringReferencia, frames);
		return gerarResultadoMap(paginacao);
	}

	private HashMap<String, Object> gerarResultadoMap(PaginacaoMemoria paginacao) {
		HashMap<String, Object> resultado = new HashMap<String, Object>();
		resultado.put("resultadoGrafico", paginacao.resultadoGraficoFinal());
		resultado.put("stringReferencia", paginacao.stringReferencia());
		resultado.put("totalStringReferencia", paginacao.stringReferencia().size());
		resultado.put("totalFrames", paginacao.totalFrames());
		resultado.put("totalPageFault", paginacao.totalPageFault());
		resultado.put("algoritmoNome", paginacao.algoritmoNome());
		return resultado;
	}
}
