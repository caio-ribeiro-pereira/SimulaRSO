package com.appspot.simularso.logic.disc;

import java.util.ArrayList;
import java.util.LinkedList;

import com.appspot.simularso.logic.EscalonadorDisco;
import com.appspot.simularso.model.Disco;

public class SSTF extends EscalonadorDiscoBase implements EscalonadorDisco {

	public SSTF(LinkedList<Disco> requisicoes, Disco cabeca) {
		super(requisicoes, cabeca);
		executar();
	}

	public SSTF(LinkedList<Disco> requisicoes, Disco cabeca, int maxCilindro) {
		super(requisicoes, cabeca, maxCilindro);
		executar();
	}

	private void executar() {
		while (totalRequisicoes() > 1) {
			Disco primeiroDisco = removerRequisicao(MIN);
			adicionarResultado(primeiroDisco);
			int menorIntervalo = getMaxCilindro();
			Disco menorDisco = null;

			for (Disco disco : getRequisicoes()) {
				int intervalo = Math.abs(disco.getCilindro()
						- primeiroDisco.getCilindro());

				if (menorIntervalo >= intervalo) {
					menorIntervalo = intervalo;
					menorDisco = disco;
				}
			}

			getRequisicoes().remove(menorDisco);
			getRequisicoes().addFirst(menorDisco);

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
		return EscalonadorDiscoAlgoritmo.SSTF.getNome();
	}

	@Override
	public Integer getTotalRequisicoes() {
		return getNumeroRequisicoes();
	}
}
