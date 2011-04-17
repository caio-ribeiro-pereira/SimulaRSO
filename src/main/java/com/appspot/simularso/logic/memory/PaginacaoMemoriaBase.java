package com.appspot.simularso.logic.memory;

import java.util.ArrayList;
import java.util.List;

import com.appspot.simularso.exception.FramesInvalidoException;
import com.appspot.simularso.exception.StringReferenciaInvalidaException;
import com.appspot.simularso.model.Pagina;

public abstract class PaginacaoMemoriaBase {

	private static final int MIN_FRAME = 2;

	private List<Integer> stringRef;
	private int frames;
	private ArrayList<Pagina> resultadoGrafico;
	private Pagina pagina;
	private int pageFaults;
	private int index;

	protected PaginacaoMemoriaBase(List<Integer> stringRef, Integer frames) {
		validar(stringRef, frames);
		this.stringRef = stringRef;
		this.frames = frames;
		this.pageFaults = 0;
		this.index = 0;
		this.pagina = new Pagina();
		this.resultadoGrafico = new ArrayList<Pagina>();
	}

	private void validar(List<Integer> stringRef, Integer frames) {
		if (stringRef == null || stringRef.size() <= 0) {
			throw new StringReferenciaInvalidaException();
		}
		if (frames < MIN_FRAME) {
			throw new FramesInvalidoException();
		}
	}

	protected boolean foiAlocado(Integer palavra) {
		if (getPagina().getPalavras().indexOf(palavra) != -1)
			return true;
		return false;
	}

	protected void atualizarPageFault() {
		this.pageFaults++;
	}

	protected int totalDeFrames() {
		return frames;
	}

	protected void atualizarResultadoGrafico() {
		Pagina novaPagina = pagina.clone();
		novaPagina.setPalavras(pagina.getPalavras());
		this.resultadoGrafico.add(novaPagina);
	}

	protected ArrayList<Pagina> getResultadoGrafico() {
		resultadoGrafico.trimToSize();
		return resultadoGrafico;
	}

	protected int getTotalPageFaults() {
		return pageFaults;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	protected Pagina getPagina() {
		return pagina;
	}

	protected void pageFaultPadrao() {
		pagina.iniciarPageFault();
	}

	protected List<Integer> getStringReferencia() {
		return this.stringRef;
	}

	protected int totalStringReferencia() {
		return this.stringRef.size();
	}

	protected Integer totalDePalavrasNaPagina() {
		return pagina.totalPalavras();
	}

	protected void inserirPagina(Integer palavra) {
		this.pagina.incluir(palavra);
	}

	protected void atualizarPagina(Integer index, Integer palavra) {
		this.pagina.atualizar(index, palavra);
	}
}
