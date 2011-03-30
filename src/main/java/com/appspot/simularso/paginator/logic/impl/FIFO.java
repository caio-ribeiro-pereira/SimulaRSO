package com.appspot.simularso.paginator.logic.impl;

import java.util.ArrayList;
import java.util.List;

import com.appspot.simularso.model.Pagina;
import com.appspot.simularso.paginator.logic.Paginador;
import com.appspot.simularso.paginator.logic.PaginadorBase;

public class FIFO extends PaginadorBase implements Paginador {

	public FIFO(List<Integer> stringRef, Integer frames) {
		super(stringRef, frames);
		executar();
	}

	private void executar() {
		for (Integer palavra : getStringReferencia()) {
			pageFaultPadrao();
			if (totalDePalavrasNaPagina() < totalFrames()) {
				if (!foiAlocado(palavra)) {
					inserirPagina(palavra);
					atualizarIndex();
					atualizarPageFault();
				}
			} else {
				if (!foiAlocado(palavra)) {
					atualizarPagina(getIndex(), palavra);
					atualizarIndex();
					atualizarPageFault();
				}
			}
			atualizarResultadoGrafico();
		}
	}

	private void atualizarIndex() {
		setIndex(getIndex() + 1 >= totalFrames() ? 0 : getIndex() + 1);
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