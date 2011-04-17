package com.appspot.simularso.logic.memory;

import java.util.ArrayList;
import java.util.List;

import com.appspot.simularso.logic.PaginacaoMemoria;
import com.appspot.simularso.model.Pagina;

public class LRU extends PaginacaoMemoriaBase implements PaginacaoMemoria {

	public LRU(List<Integer> stringRef, Integer frames) {
		super(stringRef, frames);
		executar();
	}

	private void executar() {
		for (int i = 0; i < getStringReferencia().size(); i++) {
			pageFaultPadrao();
			int palavra = getStringReferencia().get(i);
			if (totalDePalavrasNaPagina() < totalDeFrames()) {
				if (!foiAlocado(palavra)) {
					inserirPagina(palavra);
					atualizarPageFault();
				}
			} else {
				if (!foiAlocado(palavra)) {
					atualizarIndex(i - 1);
					atualizarPagina(getIndex(), palavra);
					atualizarPageFault();
				}
			}
			atualizarResultadoGrafico();
		}
	}

	private void atualizarIndex(int pointCutIndex) {
		int palavraEncontrada = buscarPaginaMaisAntiga(pointCutIndex);
		setIndex(getPagina().getPalavras().indexOf(palavraEncontrada));
	}

	private int buscarPaginaMaisAntiga(int pointCutIndex) {
		int maxPageIndex = pointCutIndex;

		for (int pageIndex = 0; pageIndex < getPagina().getPalavras().size(); pageIndex++) {
			boolean paginaEncontrada = false;
			int palavraDaPagina = getPagina().getPalavras().get(pageIndex);

			for (int stringRefIndex = pointCutIndex; stringRefIndex >= 0; stringRefIndex--) {
				int palavraDaStringReferencia = getStringReferencia().get(stringRefIndex);

				if (palavraDaPagina == palavraDaStringReferencia) {
					if (stringRefIndex <= maxPageIndex) {
						maxPageIndex = stringRefIndex;
					}
					paginaEncontrada = true;
					break;
				}
			}
			if (!paginaEncontrada) {
				return getPagina().getPalavras().get(pageIndex);
			}
		}
		return getStringReferencia().get(maxPageIndex);
	}

	@Override
	public int totalPageFault() {
		return getTotalPageFaults();
	}

	@Override
	public Integer totalFrames() {
		return totalDeFrames();
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
		return PaginacaoMemoriaAlgoritmo.LRU.getNome();
	}
}