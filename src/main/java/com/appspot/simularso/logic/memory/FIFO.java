package com.appspot.simularso.logic.memory;

import java.util.ArrayList;
import java.util.List;

import com.appspot.simularso.logic.PaginacaoMemoria;
import com.appspot.simularso.model.Pagina;

public class FIFO extends PaginacaoMemoriaBase implements PaginacaoMemoria {

	public FIFO(List<Integer> stringRef, Integer frames) {
		super(stringRef, frames);
		executar();
	}

	private void executar() {
		for (Integer palavra : getStringReferencia()) {
			pageFaultPadrao();
			if (totalDePalavrasNaPagina() < totalDeFrames()) {
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
		setIndex(getIndex() + 1 >= totalDeFrames() ? 0 : getIndex() + 1);
	}

	@Override
	public Integer totalFrames() {
		return totalDeFrames();
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

	@Override
	public String algoritmoNome() {
		return PaginacaoMemoriaAlgoritmo.FIFO.getNome();
	}
}