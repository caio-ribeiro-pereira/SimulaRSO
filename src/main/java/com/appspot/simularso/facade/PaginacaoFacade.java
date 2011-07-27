package com.appspot.simularso.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.logic.PaginacaoMemoria;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaAlgoritmo;

@Component
@ApplicationScoped
public class PaginacaoFacade extends AlgoritmoFacade implements Serializable {

	private static final long serialVersionUID = 7997741551032826620L;

	public PaginacaoFacade() {
		super();
	}

	public ArrayList<HashMap<String, Object>> executar(final List<PaginacaoMemoriaAlgoritmo> algs, final List<Integer> stringReferencia,
			final Integer frames, final int modo) {

		ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = new ArrayList<HashMap<String, Object>>();
		for (PaginacaoMemoriaAlgoritmo algoritmo : algs) {

			Class<?> klass = new Mirror().reflectClass(super.extrairAlgoritmoNome(algoritmo));
			PaginacaoMemoria paginacao = (PaginacaoMemoria) new Mirror().on(klass).invoke().constructor()
					.withArgs(stringReferencia, frames);

			HashMap<String, Object> resultado = new HashMap<String, Object>();
			resultado.put("resultadoGrafico", paginacao.resultadoGraficoFinal());
			resultado.put("stringReferencia", paginacao.stringReferencia());
			resultado.put("totalStringReferencia", paginacao.stringReferencia().size());
			resultado.put("totalFrames", paginacao.totalFrames());
			resultado.put("totalPageFault", paginacao.totalPageFault());
			resultado.put("algoritmoNome", paginacao.algoritmoNome());

			resultadosDosAlgoritmos.add(resultado);
		}

		resultadosDosAlgoritmos.trimToSize();
		return resultadosDosAlgoritmos;
	}

}
