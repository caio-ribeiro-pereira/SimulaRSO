package com.appspot.simularso.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import net.vidageek.mirror.dsl.Mirror;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.model.Processo;
import com.appspot.simularso.scheduler.process.logic.EscalonadorProcesso;
import com.appspot.simularso.scheduler.process.logic.impl.EscalonadorProcessoAlgoritmo;
import com.appspot.simularso.util.EnumToClass;

@Component
public class ProcessoFacade implements Serializable {

	private static final long serialVersionUID = 2042380361685124958L;

	private final EnumToClass enumToClass;

	public ProcessoFacade(EnumToClass enumToClass) {
		this.enumToClass = enumToClass;
	}

	public ArrayList<HashMap<String, Object>> executar(final ArrayList<EscalonadorProcessoAlgoritmo> algs, final ArrayList<Processo> pr,
			final int qt) {
		if (algs != null && !algs.isEmpty()) {
			ArrayList<HashMap<String, Object>> resultadosDosAlgoritmos = new ArrayList<HashMap<String, Object>>();
			for (EscalonadorProcessoAlgoritmo alg : algs) {
				ArrayList<Processo> processos = (ArrayList<Processo>) pr.clone();
				HashMap<String, Object> resultado = gerarResultado(alg, processos, qt);
				resultadosDosAlgoritmos.add(resultado);
			}
			resultadosDosAlgoritmos.trimToSize();
			return resultadosDosAlgoritmos;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private HashMap<String, Object> gerarResultado(EscalonadorProcessoAlgoritmo algoritmo, ArrayList<Processo> processos, int quantum) {
		Class<?> klass = new Mirror().reflectClass(enumToClass.extractClassFromEnum(algoritmo));
		EscalonadorProcesso escalonador = (EscalonadorProcesso) new Mirror().on(klass).invoke().constructor().withArgs(processos, quantum);
		return gerarResultadoMap(escalonador);
	}

	private HashMap<String, Object> gerarResultadoMap(EscalonadorProcesso escalonador) {
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