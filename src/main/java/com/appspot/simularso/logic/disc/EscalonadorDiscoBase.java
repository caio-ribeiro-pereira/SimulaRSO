package com.appspot.simularso.logic.disc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import com.appspot.simularso.exception.CilindroCabecaVaziaException;
import com.appspot.simularso.exception.RequisicaoCilindroException;
import com.appspot.simularso.exception.RequisicoesVaziaException;
import com.appspot.simularso.model.Disco;

public abstract class EscalonadorDiscoBase {

	private static final int PADRAO = 100;
	protected static final int MIN = 0;

	private Integer movimentoTotal;
	private LinkedList<Disco> requisicoes;
	private Disco cabeca;
	private ArrayList<Disco> resultado;
	private int maxCilindro;

	public EscalonadorDiscoBase(LinkedList<Disco> requisicoes, Disco cabeca) {
		this(requisicoes, cabeca, PADRAO);
	}

	public EscalonadorDiscoBase(LinkedList<Disco> requisicoes, Disco cabeca, int maxCilindro) {
		validarParametros(requisicoes, cabeca);
		this.movimentoTotal = MIN;
		this.maxCilindro = maxCilindro;
		this.requisicoes = requisicoes;
		this.cabeca = cabeca;
		this.resultado = new ArrayList<Disco>();
		inserirCilindroCabeca();
	}

	private void inserirCilindroCabeca() {
		this.requisicoes.addFirst(cabeca);
	}

	private void validarParametros(LinkedList<Disco> requisicoes, Disco cabeca) {
		if (cabeca == null || cabeca.getCilindro() < MIN) {
			throw new CilindroCabecaVaziaException();
		}
		if (requisicoes == null || requisicoes.isEmpty()) {
			throw new RequisicoesVaziaException();
		}
		for (Disco disco : requisicoes) {
			if (disco.getCilindro() < MIN) {
				throw new RequisicaoCilindroException();
			}
		}
	}

	protected void ordenarRequisicoes() {
		Collections.sort(requisicoes);
	}

	protected void inverterOrdemDeRequisicoes() {
		Collections.reverse(requisicoes);
	}

	protected boolean existeRequisicoes() {
		return !getRequisicoes().isEmpty();
	}

	protected void calcularResultadoMovimentoTotal() {
		for (int i = 0; i < resultado.size() - 1; i++) {
			this.movimentoTotal += Math.abs(resultado.get(i).getCilindro() - resultado.get(i + 1).getCilindro());
		}
	}

	protected int totalRequisicoes() {
		return requisicoes.size();
	}

	protected int getCilindroRequisicao(Disco disco) {
		return requisicoes.lastIndexOf(disco);
	}

	protected LinkedList<Disco> getRequisicoes() {
		return requisicoes;
	}

	protected Disco getCabeca() {
		return cabeca;
	}

	protected ArrayList<Disco> getResultado() {
		return resultado;
	}

	protected int getMaxCilindro() {
		return maxCilindro;
	}

	protected Integer getMovimentoTotal() {
		return this.movimentoTotal;
	}

	protected void adicionarResultado(Disco disco) {
		this.resultado.add(disco);
	}

	protected Disco removerRequisicao(int index) {
		return requisicoes.remove(index);
	}

	protected int getNumeroRequisicoes() {
		return resultado.size();
	}
}
