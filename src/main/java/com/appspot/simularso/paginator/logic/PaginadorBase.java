package com.appspot.simularso.paginator.logic;

import java.util.ArrayList;

import com.appspot.simularso.exception.FramesInvalidoException;
import com.appspot.simularso.exception.StringReferenciaInvalidaException;
import com.appspot.simularso.model.Pagina;

public abstract class PaginadorBase {

	private Integer[] stringRef;
	private int frames;
	private ArrayList<Pagina> resultadoGrafico;
	private int pageFaults;
	private Pagina pagina;

	protected PaginadorBase(Integer[] stringRef, Integer frames) {
		validar(stringRef, frames);
		this.stringRef = stringRef;
		this.frames = frames;
		this.pageFaults = 0;
		this.pagina = new Pagina();
		this.resultadoGrafico = new ArrayList<Pagina>();
	}

	private void validar(Integer[] stringRef, Integer frames) {
		if (stringRef == null || stringRef.length <= 0) {
			throw new StringReferenciaInvalidaException();
		}
		if (frames < 2) {
			throw new FramesInvalidoException();
		}
	}

	protected void atualizarPageFault() {
		this.pageFaults++;
	}

	protected Integer[] stringReferencia() {
		return stringRef;
	}

	protected int totalFrames() {
		return frames;
	}

	protected void atualizarResultadoGrafico() {
		Pagina novaPagina = pagina.clone();
		novaPagina.setPalavras(pagina.getPalavras());
		this.resultadoGrafico.add(novaPagina);
	}

	protected ArrayList<Pagina> getResultadoGrafico() {
		return resultadoGrafico;
	}

	protected int getTotalPageFaults() {
		return pageFaults;
	}

	protected Pagina getPagina() {
		return pagina;
	}

	protected void pageFaultPadrao() {
		pagina.iniciarPageFault();
	}

	protected Integer totalPalavras() {
		return pagina.totalPalavras();
	}

	protected void inserirPagina(Integer palavra) {
		this.pagina.incluir(palavra);
	}

	protected void atualizarPagina(Integer index, Integer palavra) {
		this.pagina.atualizar(index, palavra);
	}
}
