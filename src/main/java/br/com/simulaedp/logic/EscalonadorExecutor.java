package br.com.simulaedp.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import br.com.caelum.vraptor.ioc.Component;
import br.com.simulaedp.logic.impl.FCFS;
import br.com.simulaedp.logic.impl.Prioridade;
import br.com.simulaedp.logic.impl.RoundRobin;
import br.com.simulaedp.logic.impl.SJF;
import br.com.simulaedp.logic.impl.SRT;
import br.com.simulaedp.model.Algoritmo;
import br.com.simulaedp.model.Processo;

@Component
public class EscalonadorExecutor implements Serializable {

	private static final long serialVersionUID = 1799220478831103578L;
	
	private TreeMap<String, Object> resultado;

	public EscalonadorExecutor() {
	}

	public void executar(final Algoritmo algortimo,
			final ArrayList<Processo> processos, final int quantum) {
		Escalonador escalonador = null;
		if (algortimo == Algoritmo.FCFS) {
			escalonador = new FCFS(processos);
		} else if (algortimo == Algoritmo.SJF) {
			escalonador = new SJF(processos);
		} else if (algortimo == Algoritmo.PRIORIDADE) {
			escalonador = new Prioridade(processos);
		} else if (algortimo == Algoritmo.ROUNDROBIN) {
			escalonador = new RoundRobin(processos, quantum);
		} else if (algortimo == Algoritmo.SRT) {
			escalonador = new SRT(processos);
		} else {
			throw new IllegalArgumentException();
		}
		this.resultado = new TreeMap<String, Object>();
		this.resultado.put("resultadoFinal", escalonador.resultadoFinal());
		this.resultado.put("resultadoGrafico", escalonador.resultadoGraficoFinal());
		this.resultado.put("tempoTotal", escalonador.tempoExecucaoTotal());
	}

	public TreeMap<String, Object> getResultado() {
		return resultado;
	}
}