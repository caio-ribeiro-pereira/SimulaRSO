package com.appspot.simularso.logic.disc;

import java.util.ArrayList;
import java.util.LinkedList;

import com.appspot.simularso.logic.EscalonadorDisco;
import com.appspot.simularso.model.Disco;

public class FCFS extends EscalonadorDiscoBase implements EscalonadorDisco {

	public FCFS(LinkedList<Disco> requisicoes, Disco cabeca) {
		super(requisicoes, cabeca);
		executar();
	}

	public FCFS(LinkedList<Disco> requisicoes, Disco cabeca, int maxCilindro) {
		super(requisicoes, cabeca, maxCilindro);
		executar();
	}

	private void executar() {
		while (existeRequisicoes()) {
			adicionarResultado(removerRequisicao(MIN));
		}
		calcularResultadoMovimentoTotal();
	}

	@Override
	public ArrayList<Disco> resultados() {
		return getResultado();
	}

	@Override
	public Integer movimentoTotalCilindros() {
		return getMovimentoTotal();
	}

	@Override
	public String algoritmoNome() {
		return EscalonadorDiscoAlgoritmo.FCFS.getNome();
	}

	@Override
	public Integer getTotalRequisicoes() {
		return getNumeroRequisicoes();
	}
}
