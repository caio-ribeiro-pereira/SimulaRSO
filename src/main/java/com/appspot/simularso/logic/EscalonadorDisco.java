package com.appspot.simularso.logic;

import java.util.ArrayList;

import com.appspot.simularso.model.Disco;

public interface EscalonadorDisco {

	/**
	 * 
	 * Retornar uma lista com as posicoes de cilindros que serao ilustradas na
	 * view.
	 * 
	 * 
	 * @author Andre de Araujo Rodrigues
	 * @since 16/04/2011
	 * @return Lista de resultados
	 */
	ArrayList<Disco> resultados();

	/**
	 * 
	 * Retornar o numero de movimentos realizados pelo escalonador de disco.
	 * 
	 * 
	 * @author Andre de Araujo Rodrigues
	 * @since 16/04/2011
	 * @return Total de movimentos
	 */
	Integer movimentoTotalCilindros();

	/**
	 * 
	 * Retornar o nome do algoritmo que foi simulado.
	 * 
	 * 
	 * @author Andre de Araujo Rodrigues
	 * @since 16/04/2011
	 * @return Nome do algoritmo
	 */
	String algoritmoNome();

	/**
	 * 
	 * Retornar o total de requisicoes que foram incluidas na simulacao.
	 * 
	 * @author Andre de Araujo Rodrigues
	 * @since 16/04/2011
	 * @return total de requisicoes.
	 */
	Integer getTotalRequisicoes();
}