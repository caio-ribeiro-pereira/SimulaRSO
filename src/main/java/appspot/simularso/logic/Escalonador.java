package appspot.simularso.logic;

import java.util.LinkedList;
import java.util.Set;

import appspot.simularso.model.Processo;

public interface Escalonador {

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
	 * Retorna um Set com os resultados reais de um escalonamento.
	 * 
	 * @return Set com resultados reais.
	 */
	Set<Processo> resultadoFinal();

	/**
	 * Retorna um LinkedList com os resultados graficos para simular o
	 * comportamento do algoritmo em graficos de gantt ou linha.
	 * 
	 * @return LinkedList com resultados para grafico.
	 */
	LinkedList<Processo> resultadoGraficoFinal();

	/**
	 * Retorna o nome do Algoritmo de escalonamento que foi executado.
	 * 
	 * @return String nome do algoritmo escalonador.
	 */
	String algoritmoNome();
}
