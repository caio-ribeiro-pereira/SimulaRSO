package br.com.simulaedp.logic;

import java.util.ArrayList;

import br.com.simulaedp.model.Processo;

public interface Escalonador {

	/**
	 * Executa um algoritmo de escalonamento.
	 * 
	 */
	void executar();

	/**
	 * 
	 * Retorna o tempo total de execucao de um determinado algoritmo.
	 * 
	 * @return tempo total de execucao.
	 */
	int tempoExecucaoTotal();

	/**
	 * 
	 * Retorna um ArrayList com os resultados reais de um escalonamento.
	 * 
	 * @return ArrayList com resultados reais.
	 */
	ArrayList<Processo> resultadoFinal();

	/**
	 * Retorna um ArrayList com os resultados graficos para simular o comportamento
	 * do algoritmo em graficos de gantt ou linha.
	 * 
	 * @return ArrayList com resultados para grafico.
	 */
	ArrayList<Processo> resultadoGraficoFinal();
}
