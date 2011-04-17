package com.appspot.simularso.logic.disc;

import java.util.ArrayList;
import java.util.LinkedList;

import com.appspot.simularso.logic.EscalonadorDisco;
import com.appspot.simularso.model.Disco;

public class SCAN extends EscalonadorDiscoBase implements EscalonadorDisco {

	public SCAN(LinkedList<Disco> requisicoes, Disco cabeca) {
		super(requisicoes, cabeca);
		executar();
	}

	public SCAN(LinkedList<Disco> requisicoes, Disco cabeca, int maxCilindro) {
		super(requisicoes, cabeca, maxCilindro);
		executar();
	}

	private void executar() {
		ordenarRequisicoes();
		int pontoDeCorte = getCilindroRequisicao(getCabeca());
		boolean flag = true;
		while (existeRequisicoes()) {
			adicionarResultado(removerRequisicao(pontoDeCorte));
			if (pontoDeCorte > MIN) {
				pontoDeCorte--;
			} else if (flag) {
				adicionarResultado(new Disco(0));
				flag = false;
			}
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
		return EscalonadorDiscoAlgoritmo.SCAN.getNome();
	}

	@Override
	public Integer getTotalRequisicoes() {
		return getNumeroRequisicoes();
	}
}
