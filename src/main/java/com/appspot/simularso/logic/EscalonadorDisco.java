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
	 * @return Lista de Integer de resultados
	 */
	ArrayList<Disco> resultados();

	Integer movimentoTotalCilindros();
	
	String algoritmoNome();
	
	Integer getTotalRequisicoes();
		
		
	
}
