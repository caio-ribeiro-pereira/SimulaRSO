package com.appspot.simularso.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.logic.EscalonadorProcesso;
import com.appspot.simularso.logic.process.EscalonadorProcessoAlgoritmo;
import com.appspot.simularso.model.Processo;

@Component
@ApplicationScoped
public class ProcessoFacade extends AlgoritmoFacade implements Serializable {

	private static final long serialVersionUID = -1790815433538359927L;

	public ProcessoFacade() {
		super();
	}

	public ArrayList<HashMap<String, Object>> executar(final List<EscalonadorProcessoAlgoritmo> algs, final ArrayList<Processo> pr,
			final int quantum, final int modo) {

		ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = new ArrayList<HashMap<String, Object>>();

		for (EscalonadorProcessoAlgoritmo algoritmo : algs) {
			ArrayList<Processo> processos = (ArrayList<Processo>) pr.clone();

			Class<?> klass = new Mirror().reflectClass(super.extrairAlgoritmoNome(algoritmo));
			EscalonadorProcesso escalonador = (EscalonadorProcesso) new Mirror().on(klass).invoke().constructor()
					.withArgs(processos, quantum);

			HashMap<String, Object> resultado = new HashMap<String, Object>();
			resultado.put("resultadoFinal", escalonador.resultadoFinal());
			resultado.put("resultadoGrafico", escalonador.resultadoGraficoFinal());
			resultado.put("tempoTotal", escalonador.tempoExecucaoTotal());
			resultado.put("tempoEsperaMedia", escalonador.tempoEsperaMedia());
			resultado.put("tempoRespostaMedia", escalonador.tempoRespostaMedia());
			resultado.put("tempoTurnAroundMedio", escalonador.tempoTurnAroundMedio());
			resultado.put("totalProcessos", escalonador.totalProcessos());
			resultado.put("algoritmoNome", escalonador.algoritmoNome());

			resultadosDosAlgoritmos.add(resultado);
		}

		resultadosDosAlgoritmos.trimToSize();
		return resultadosDosAlgoritmos;
	}
}