package com.appspot.simularso.paginator.logic.impl;

import java.util.ArrayList;

import com.appspot.simularso.model.Pagina;
import com.appspot.simularso.paginator.logic.Paginador;
import com.appspot.simularso.paginator.logic.PaginadorBase;

public class FIFO extends PaginadorBase implements Paginador {

	private static final boolean CADASTRAR = true;
	private static final boolean ATUALIZAR = false;
	private int index;

	public FIFO(Integer[] stringRef, Integer frames) {
		super(stringRef, frames);
		executar();
	}

	private void executar() {
		for (Integer palavra : stringReferencia()) {
			pageFaultPadrao();
			if (totalPalavras() < totalFrames()) {
				if (!foiAlocado(palavra)) {
					atualizarPaginacao(palavra, CADASTRAR);
				}
			} else {
				if (!foiAlocado(palavra)) {
					atualizarPaginacao(palavra, ATUALIZAR);
				}
			}
			atualizarResultadoGrafico();
		}
	}

	private void atualizarPaginacao(Integer palavra, boolean cadastrar) {
		if (cadastrar) {
			inserirPagina(palavra);
		} else {
			atualizarPagina(index, palavra);
		}
		atualizarPageFault();
		atualizarIndex();
	}

	private void atualizarIndex() {
		index = index + 1 >= totalFrames() ? 0 : index + 1;
	}

	private boolean foiAlocado(Integer palavra) {
		if (getPagina().getPalavras().indexOf(palavra) != -1)
			return true;
		return false;
	}

	@Override
	public int totalPageFault() {
		return getTotalPageFaults();
	}

	@Override
	public ArrayList<Pagina> resultadoGraficoFinal() {
		return getResultadoGrafico();
	}

}