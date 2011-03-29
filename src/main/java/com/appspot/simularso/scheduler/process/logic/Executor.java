package com.appspot.simularso.scheduler.process.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.appspot.simularso.model.Processo;
import com.appspot.simularso.scheduler.process.logic.Escalonador.AlgoritmoProcesso;
import com.appspot.simularso.scheduler.process.logic.impl.FCFS;
import com.appspot.simularso.scheduler.process.logic.impl.RoundRobin;
import com.appspot.simularso.scheduler.process.logic.impl.SJF;
import com.appspot.simularso.scheduler.process.logic.impl.SRT;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Executor implements Serializable {

	private static final long serialVersionUID = 3631322237923126747L;

	public Executor() {
	}

	public ArrayList<HashMap<String, Object>> executarAlgoritmos(final ArrayList<AlgoritmoProcesso> algs, final ArrayList<Processo> pr,
			final int qt) {
		if (algs != null && !algs.isEmpty()) {
			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = new ArrayList<HashMap<String, Object>>();
			for (AlgoritmoProcesso alg : algs) {
				ArrayList<Processo> processos = (ArrayList<Processo>) pr.clone();
				HashMap<String, Object> resultado = executar(alg, processos, qt);
				resultadosDosAlgoritmos.add(resultado);
			}
			resultadosDosAlgoritmos.trimToSize();
			return resultadosDosAlgoritmos;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private HashMap<String, Object> executar(final AlgoritmoProcesso algortimo, final ArrayList<Processo> processos, final int quantum) {
		Escalonador escalonador = null;
		if (algortimo == AlgoritmoProcesso.FCFS) {
			escalonador = new FCFS(processos);
		} else if (algortimo == AlgoritmoProcesso.SJF) {
			escalonador = new SJF(processos);
		} else if (algortimo == AlgoritmoProcesso.ROUNDROBIN) {
			escalonador = new RoundRobin(processos, quantum);
		} else if (algortimo == AlgoritmoProcesso.SRT) {
			escalonador = new SRT(processos);
		} else {
			throw new IllegalArgumentException();
		}
		return gerarResultadoMap(escalonador);
	}

	private HashMap<String, Object> gerarResultadoMap(Escalonador escalonador) {
		HashMap<String, Object> resultado = new HashMap<String, Object>();
		resultado.put("resultadoFinal", escalonador.resultadoFinal());
		resultado.put("resultadoGrafico", escalonador.resultadoGraficoFinal());
		resultado.put("tempoTotal", escalonador.tempoExecucaoTotal());
		resultado.put("tempoEsperaMedia", escalonador.tempoEsperaMedia());
		resultado.put("tempoRespostaMedia", escalonador.tempoRespostaMedia());
		resultado.put("tempoTurnAroundMedio", escalonador.tempoTurnAroundMedio());
		resultado.put("totalProcessos", escalonador.totalProcessos());
		resultado.put("algoritmoNome", escalonador.algoritmoNome());
		return resultado;
	}
}