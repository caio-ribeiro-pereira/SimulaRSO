package br.com.simulaedp.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.simulaedp.logic.impl.FCFS;
import br.com.simulaedp.logic.impl.Prioridade;
import br.com.simulaedp.logic.impl.RoundRobin;
import br.com.simulaedp.logic.impl.SJF;
import br.com.simulaedp.logic.impl.SRT;
import br.com.simulaedp.model.Processo;
import br.com.simulaedp.model.enumerator.AlgoritmoProcesso;

@RequestScoped
@Component
public class SchedulerExecutor implements Serializable {

	private static final long serialVersionUID = 1799220478831103578L;
	
	private TreeMap<String, Object> resultado;

	public SchedulerExecutor() {
	}

	public void executar(final AlgoritmoProcesso algortimo,
			final ArrayList<Processo> processos, final int quantum) {
		Escalonador escalonador = null;
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
		this.resultado = new TreeMap<String, Object>();
		this.resultado.put("resultadoFinal", escalonador.resultadoFinal());
		this.resultado.put("resultadoGrafico", escalonador.resultadoGraficoFinal());
		this.resultado.put("tempoTotal", escalonador.tempoExecucaoTotal());
	}

	public TreeMap<String, Object> getResultado() {
		return resultado;
	}
}