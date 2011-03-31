package com.appspot.simularso.scheduler.process.logic;

import java.util.List;

import com.appspot.simularso.model.ProcessoDTO;
import com.appspot.simularso.model.ProcessoVO;

public interface EscalonadorProcesso {

	public enum AlgoritmoProcesso {
		FCFS("FC-FS"), SJF("SJF"), SRT("SRT"), ROUNDROBIN("Round-Robin");

		private String nome;

		AlgoritmoProcesso(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return this.nome;
		}
	}

	/**
	 * 
	 * Retorna o total de processos que foram executados.
	 * 
	 * @return total de processos.
	 */
	int totalProcessos();

	/**
	 * 
	 * Retorna o tempo total de execucao de um determinado algoritmo.
	 * 
	 * @return tempo total de execucao.
	 */
	int tempoExecucaoTotal();

	/**
	 * Retorna o tempo de espera media da execucao do algoritmo escalonador.
	 * 
	 * @return tempo de espera media.
	 */
	double tempoEsperaMedia();

	/**
	 * Retorna o tempo de resposta media da execucao do algoritmo escalonador.
	 * 
	 * @return tempo de resposta media.
	 */
	double tempoRespostaMedia();

	/**
	 * Retorna o tempo de turn around medio dos processos executados pelo
	 * algoritmo.
	 * 
	 * @return tempo turn around medio.
	 */
	double tempoTurnAroundMedio();

	/**
	 * 
	 * Retorna um LinkedList com os resultados reais de um escalonamento.
	 * 
	 * @return LinkedList com resultados reais.
	 */
	List<ProcessoVO> resultadoFinal();

	/**
	 * Retorna um LinkedList com os resultados graficos para simular o
	 * comportamento do algoritmo em graficos de gantt ou linha.
	 * 
	 * @return LinkedList com resultados para grafico.
	 */
	List<ProcessoDTO> resultadoGraficoFinal();

	/**
	 * Retorna o nome do Algoritmo de escalonamento que foi executado.
	 * 
	 * @return String nome do algoritmo escalonador.
	 */
	String algoritmoNome();
}
