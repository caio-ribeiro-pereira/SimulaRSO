package appspot.simularso.virtualmemory.logic;

import java.util.ArrayList;

import appspot.simularso.model.Paginacao;

public interface MemoriaVirtual {

	public enum AlgoritmoPaginacao {
		FIFO("FI-FO"), OTIMO("Ótimo"), LRU("LRU");

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
	ArrayList<Paginacao> resultadoFinal();

}
