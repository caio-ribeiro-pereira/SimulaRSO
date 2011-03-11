package appspot.simulaedp.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import appspot.simulaedp.logic.impl.FCFS;
import appspot.simulaedp.logic.impl.Prioridade;
import appspot.simulaedp.logic.impl.RoundRobin;
import appspot.simulaedp.logic.impl.SJF;
import appspot.simulaedp.logic.impl.SRT;
import appspot.simulaedp.model.Processo;
import appspot.simulaedp.model.Processo.AlgoritmoProcesso;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class Executor implements Serializable {

	private static final long serialVersionUID = -1275343850239840523L;

	public Executor() {
	}

	public TreeMap<String, Object> executar(final AlgoritmoProcesso algortimo, final ArrayList<Processo> processos,
			final int quantum) {
		Escalonador escalonador = null;
		if (algortimo != null) {
			if (algortimo == AlgoritmoProcesso.FCFS) {
				escalonador = new FCFS(processos);
			} else if (algortimo == AlgoritmoProcesso.SJF) {
				escalonador = new SJF(processos);
			} else if (algortimo == AlgoritmoProcesso.PRIORIDADE) {
				escalonador = new Prioridade(processos);
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

	private TreeMap<String, Object> gerarResultadoMap(Escalonador escalonador) {
		escalonador.executar();
		TreeMap<String, Object> resultado = new TreeMap<String, Object>();
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