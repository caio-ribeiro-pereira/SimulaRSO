package appspot.simularso.paginator.logic.impl;

import java.util.List;

import appspot.simularso.model.Pagina;
import appspot.simularso.paginator.logic.Paginador;
import appspot.simularso.paginator.logic.PaginadorBase;

public class FIFO extends PaginadorBase implements Paginador {

	private int index;

	public FIFO(Integer[] stringRef, Integer frames) {
		super(stringRef, frames);
		executar();
	}

	private void executar() {
		this.index = 0;
		for (Integer palavra : stringReferencia()) {
			if (totalPalavras() >= totalFrames()) {
				if (!foiAlocado(palavra)) {
					atualizarPaginacao(palavra, false);
				}
			} else {
				if (!foiAlocado(palavra)) {
					atualizarPaginacao(palavra, true);
				}
			}
			atualizarResultadoGrafico();
		}
	}

	private void atualizarPaginacao(Integer palavra, boolean novo) {
		if (novo) {
			inserirPagina(palavra);
		} else {
			atualizarPagina(index, palavra);
		}
		atualizarPageFault();
		atualizarIndex();
	}

	private void atualizarIndex() {
		index = (index + 1 >= totalFrames()) ? 0 : index + 1;
	}

	private boolean foiAlocado(Integer palavra) {
		for (Integer val : getPagina().getPalavras()) {
			if (val == palavra) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int totalPageFault() {
		return getTotalPageFaults();
	}

	@Override
	public List<Pagina> resultadoGraficoFinal() {
		return getResultadoGrafico();
	}

}
