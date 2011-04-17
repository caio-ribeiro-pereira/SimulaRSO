package com.appspot.simularso.logic.disc;

import java.util.ArrayList;
import java.util.LinkedList;

import com.appspot.simularso.logic.EscalonadorDisco;
import com.appspot.simularso.model.Disco;

public class LOOK extends EscalonadorDiscoBase implements EscalonadorDisco {

	public LOOK(LinkedList<Disco> requisicoes, Disco cabeca) {
		super(requisicoes, cabeca);
		executar();
	}

	public LOOK(LinkedList<Disco> requisicoes, Disco cabeca, int maxCilindro) {
		super(requisicoes, cabeca, maxCilindro);
		executar();
	}

	private void executar() {
		ordenarRequisicoes();
		inverterOrdemDeRequisicoes();
		int pontoDeCorte = getCilindroRequisicao(getCabeca());
		boolean flag = true;
		while (existeRequisicoes()) {
			if (pontoDeCorte >= MIN) {
				adicionarResultado(removerRequisicao(pontoDeCorte--));
			} else if (pontoDeCorte <= MIN && flag) {
				inverterOrdemDeRequisicoes();
				flag = false;
			} else {
				adicionarResultado(removerRequisicao(MIN));
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
		return EscalonadorDiscoAlgoritmo.LOOK.getNome();
	}

	@Override
	public Integer getTotalRequisicoes() {
		return getNumeroRequisicoes();
	}
}
