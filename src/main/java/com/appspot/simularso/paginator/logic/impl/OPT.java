package com.appspot.simularso.paginator.logic.impl;

import java.util.ArrayList;
import java.util.List;

import com.appspot.simularso.model.Pagina;
import com.appspot.simularso.paginator.logic.Paginador;
import com.appspot.simularso.paginator.logic.PaginadorBase;

public class OPT extends PaginadorBase implements Paginador {

	public OPT(List<Integer> stringRef, Integer frames) {
		super(stringRef, frames);
		executar();
	}

	private void executar() {
		for (int i = 0; i < getStringReferencia().size(); i++) {
			pageFaultPadrao();
			Integer palavra = getStringReferencia().get(i);
			if (totalDePalavrasNaPagina() < totalFrames()) {
				if (!foiAlocado(palavra)) {
					inserirPagina(palavra);
					atualizarPageFault();
				}
			} else {
				if (!foiAlocado(palavra)) {
					atualizarIndex(i + 1);
					atualizarPagina(getIndex(), palavra);
					atualizarPageFault();
				}
			}
			atualizarResultadoGrafico();
		}
	}

	private void atualizarIndex(int pointCutIndex) {
		setIndex(buscarPaginaMaisDistante(pointCutIndex));
	}

	// TODO ; CORRIGIR BUG
	private int buscarPaginaMaisDistante(int pointCutIndex) {
		int maxIndex = 0;
		boolean paginaEncontrada;

		for (int pageIndex = 0; pageIndex < getPagina().getPalavras().size(); pageIndex++) {
			paginaEncontrada = false;

			for (int stringRefIndex = pointCutIndex; stringRefIndex < getStringReferencia().size(); stringRefIndex++) {
				int palavraDaPagina = getPagina().getPalavras().get(pageIndex);
				int palavraDaStringReferencia = getStringReferencia().get(stringRefIndex);

				if (palavraDaPagina == palavraDaStringReferencia) {

					if (pageIndex >= maxIndex) {
						maxIndex = pageIndex;
						paginaEncontrada = true;
						break;
					}

				}
			}
			if (!paginaEncontrada) {
				return maxIndex;
			}
		}
		return maxIndex;
	}

	@Override
	public int totalPageFault() {
		return getTotalPageFaults();
	}

	@Override
	public ArrayList<Pagina> resultadoGraficoFinal() {
		return getResultadoGrafico();
	}

	@Override
	public List<Integer> stringReferencia() {
		return getStringReferencia();
	}
}
