package appspot.simularso.paginator.logic;

import java.util.ArrayList;
import java.util.List;

import appspot.simularso.exception.FramesInvalidoException;
import appspot.simularso.exception.StringReferenciaInvalidaException;
import appspot.simularso.model.Pagina;

public abstract class PaginadorBase {

	private Integer[] stringRef;
	private int frames;
	private List<Pagina> resultadoGrafico;
	private int pageFaults;
	private Pagina pagina;

	public PaginadorBase(Integer[] stringRef, Integer frames) {
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

	// TODO: CORRIGIR BUG
	protected void atualizarResultadoGrafico() {
		Pagina novaPagina = pagina.clone();
		this.resultadoGrafico.add(novaPagina);
	}

	protected List<Pagina> getResultadoGrafico() {
		return resultadoGrafico;
	}

	protected int getTotalPageFaults() {
		return pageFaults;
	}

	public Pagina getPagina() {
		return pagina;
	}

	public Integer totalPalavras() {
		return pagina.totalPalavras();
	}

	public void inserirPagina(Integer palavra) {
		this.pagina.incluir(palavra);
	}

	public void atualizarPagina(Integer index, Integer palavra) {
		this.pagina.atualizar(index, palavra);
	}
}
