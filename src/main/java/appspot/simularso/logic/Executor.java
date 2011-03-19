package appspot.simularso.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import appspot.simularso.logic.Escalonador.AlgoritmoProcesso;
import appspot.simularso.logic.impl.FCFS;
import appspot.simularso.logic.impl.RoundRobin;
import appspot.simularso.logic.impl.SJF;
import appspot.simularso.logic.impl.SRT;
import appspot.simularso.model.Processo;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class Executor implements Serializable {

	private static final long serialVersionUID = 251934838519507853L;

	public Executor() {
	}

	public HashMap<String, Object> executar(final AlgoritmoProcesso algortimo, final ArrayList<Processo> processos, final int quantum) {
		Escalonador escalonador = null;
		if (algortimo != null) {
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