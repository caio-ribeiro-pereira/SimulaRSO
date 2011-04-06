package com.appspot.simularso.scheduler.process.logic;

import java.util.List;

import com.appspot.simularso.model.ProcessoDTO;
import com.appspot.simularso.model.ProcessoVO;

public interface EscalonadorProcesso {

	/**
	 * @author Caio R. Pereira
	 * @since 02/02/2011
	 * 
	 *        Retorna o total de processos que foram executados.
	 * 
	 * @return total de processos.
	 */
	int totalProcessos();

	/**
	 * @author Caio R. Pereira
	 * @since 02/02/2011
	 * 
	 *        Retorna o tempo total de execucao de um determinado algoritmo.
	 * 
	 * @return tempo total de execucao.
	 */
	int tempoExecucaoTotal();

	/**
	 * @author Caio R. Pereira
	 * @since 02/02/2011
	 * 
	 *        Retorna o tempo de espera media da execucao do algoritmo
	 *        escalonador.
	 * 
	 * @return tempo de espera media.
	 */
	double tempoEsperaMedia();

	/**
	 * @author Caio R. Pereira
	 * @since 02/02/2011
	 * 
	 *        Retorna o tempo de resposta media da execucao do algoritmo
	 *        escalonador.
	 * 
	 * @return tempo de resposta media.
	 */
	double tempoRespostaMedia();

	/**
	 * @author Caio R. Pereira
	 * @since 02/02/2011
	 * 
	 *        Retorna o tempo de turn around medio dos processos executados pelo
	 *        algoritmo.
	 * 
	 * @return tempo turn around medio.
	 */
	double tempoTurnAroundMedio();

	/**
	 * @author Caio R. Pereira
	 * @since 02/02/2011
	 * 
	 *        Retorna um LinkedList com os resultados reais de um escalonamento.
	 * 
	 * @return LinkedList com resultados reais.
	 */
	List<ProcessoVO> resultadoFinal();

	/**
	 * @author Caio R. Pereira
	 * @since 02/02/2011
	 * 
	 *        Retorna um LinkedList com os resultados graficos para simular o
	 *        comportamento do algoritmo em graficos de gantt ou linha.
	 * 
	 * @return LinkedList com resultados para grafico.
	 */
	List<ProcessoDTO> resultadoGraficoFinal();

	/**
	 * @author Caio R. Pereira
	 * @since 02/02/2011
	 * 
	 *        Retorna o nome do Algoritmo de escalonamento que foi executado.
	 * 
	 * @return String nome do algoritmo escalonador.
	 */
	String algoritmoNome();
}
