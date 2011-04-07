package com.appspot.simularso.paginator.memory.logic;

import java.util.ArrayList;
import java.util.List;

import com.appspot.simularso.model.Pagina;

public interface PaginacaoMemoria {

	/**
	 * Retorna o total de falhas de páginas ocorridas durante a paginação do
	 * algoritmo.
	 * 
	 * @author Caio R. Pereira
	 * @since 07/04/2011
	 * 
	 * @return total - pageFaults
	 */
	int totalPageFault();

	/**
	 * Retorna uma lista com as ordens de cada substituição de páginas
	 * realizadas.
	 * 
	 * @author Caio R. Pereira
	 * @since 07/04/2011
	 * 
	 * @return ArrayList com estado de cada substituição realizada.
	 */
	ArrayList<Pagina> resultadoGraficoFinal();

	/**
	 * Retorna a String de Referência de entrada do usuário.
	 * 
	 * @author Caio R. Pereira
	 * @since 07/04/2011
	 * 
	 * @return Array de Integer
	 */
	List<Integer> stringReferencia();

	/**
	 * Retorna o nome do Algoritmo de escalonamento que foi executado.
	 * 
	 * @author Caio R. Pereira
	 * @since 07/04/2011
	 * 
	 * @return String nome do algoritmo escalonador.
	 */
	String algoritmoNome();

	/**
	 * Retorna o total de frames determinado para a execução do algoritmo.
	 * 
	 * @author Caio R. Pereira
	 * @since 07/04/2011
	 * 
	 * @return total - frames
	 */
	Integer totalFrames();
}
