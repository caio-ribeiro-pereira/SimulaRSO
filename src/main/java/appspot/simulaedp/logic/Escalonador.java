package appspot.simulaedp.logic;

import java.util.ArrayList;

import appspot.simulaedp.model.Processo;

public interface Escalonador {

	/**
	 * Executa um algoritmo de escalonamento.
	 * 
	 */
	void executar();

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
	 * Retorna um ArrayList com os resultados reais de um escalonamento.
	 * 
	 * @return ArrayList com resultados reais.
	 */
	ArrayList<Processo> resultadoFinal();

	/**
	 * Retorna um ArrayList com os resultados graficos para simular o
	 * comportamento do algoritmo em graficos de gantt ou linha.
	 * 
	 * @return ArrayList com resultados para grafico.
	 */
	ArrayList<Processo> resultadoGraficoFinal();
}
