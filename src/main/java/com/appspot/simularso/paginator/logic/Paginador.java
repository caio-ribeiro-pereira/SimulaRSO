package com.appspot.simularso.paginator.logic;

import java.util.ArrayList;

import com.appspot.simularso.model.Pagina;

public interface Paginador {

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

}
