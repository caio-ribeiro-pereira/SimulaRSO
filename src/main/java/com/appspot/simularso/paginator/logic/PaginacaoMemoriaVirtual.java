package com.appspot.simularso.paginator.logic;

import java.util.ArrayList;
import java.util.List;

import com.appspot.simularso.model.Pagina;

public interface PaginacaoMemoriaVirtual {

	public enum AlgoritmoPaginacao {
		FIFO("FI-FO"), OPT("OPT"), LRU("LRU");

		private String nome;

		AlgoritmoPaginacao(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return this.nome;
		}
	}

	/**
	 * Retorna o total de falhas de páginas ocorridas durante a paginação do
	 * algoritmo.
	 * 
	 * @return total - pageFaults
	 */
	int totalPageFault();

	/**
	 * Retorna uma lista com as ordens de cada substituição de páginas
	 * realizadas.
	 * 
	 * @return ArrayList com estado de cada substituição realizada.
	 */
	ArrayList<Pagina> resultadoGraficoFinal();

	/**
	 * Retorna a String de Referência de entrada do usuário.
	 * 
	 * @return Array de Integer
	 */
	List<Integer> stringReferencia();

	/**
	 * Retorna o nome do Algoritmo de escalonamento que foi executado.
	 * 
	 * @return String nome do algoritmo escalonador.
	 */
	String algoritmoNome();
}
