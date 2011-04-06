package com.appspot.simularso.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.paginator.memory.logic.PaginacaoMemoria;
import com.appspot.simularso.paginator.memory.logic.impl.PaginacaoMemoriaAlgoritmo;
import com.appspot.simularso.util.EnumToClass;

@Component
public class PaginacaoFacade implements Serializable {

	private static final long serialVersionUID = -7340262096330741072L;

	public ArrayList<HashMap<String, Object>> executar(final ArrayList<PaginacaoMemoriaAlgoritmo> algs, final List<Integer> stringRef,
			final Integer frames, final EnumToClass enumToClass) {

		if (algs != null && !algs.isEmpty()) {
			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = new ArrayList<HashMap<String, Object>>();

			for (PaginacaoMemoriaAlgoritmo alg : algs) {
				HashMap<String, Object> resultado = gerarResultado(alg, stringRef, frames, enumToClass);
				resultadosDosAlgoritmos.add(resultado);
			}

			resultadosDosAlgoritmos.trimToSize();
			return resultadosDosAlgoritmos;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private HashMap<String, Object> gerarResultado(PaginacaoMemoriaAlgoritmo algoritmo, List<Integer> stringReferencia, Integer frames,
			EnumToClass enumToClass) {
		Class<?> klass = new Mirror().reflectClass(enumToClass.extractClassFromEnum(algoritmo));
		PaginacaoMemoria paginacao = (PaginacaoMemoria) new Mirror().on(klass).invoke().constructor().withArgs(stringReferencia, frames);
		return gerarResultadoMap(paginacao);
	}

	private HashMap<String, Object> gerarResultadoMap(PaginacaoMemoria paginacao) {
		HashMap<String, Object> resultado = new HashMap<String, Object>();
		resultado.put("resultadoGrafico", paginacao.resultadoGraficoFinal());
		resultado.put("stringReferencia", paginacao.stringReferencia());
		resultado.put("totalPageFault", paginacao.totalPageFault());
		resultado.put("algoritmoNome", paginacao.algoritmoNome());
		return resultado;
	}
}
