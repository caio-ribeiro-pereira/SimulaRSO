package br.com.simulaedp.logic;

import java.util.ArrayList;
import java.util.Collections;

import br.com.simulaedp.exception.NegativoBurstException;
import br.com.simulaedp.exception.ProcessosNaoCarregadosException;
import br.com.simulaedp.model.Processo;

public abstract class EscalonadorBase {

	private ArrayList<Processo> resultado;
	private ArrayList<Processo> resultadoGrafico;
	private int tempoTotal;
	private ArrayList<Processo> processos;

	public EscalonadorBase() {
		this.resultado = new ArrayList<Processo>();
		this.resultadoGrafico = new ArrayList<Processo>();
	}

	protected int tempoTotal() {
		return tempoTotal;
	}

	protected void atualizarTempoTotal(int burst) {
		this.tempoTotal += burst;
	}

	protected int totalDeProcessos() {
		return processos.size();
	}

	protected void ordernarProcessos() {
		Collections.sort(processos);
	}

	protected void otimizarFilaDeProcessos() {
		processos.trimToSize();
	}

	protected void otimizarFilaDeResultados() {
		resultadoGrafico.trimToSize();
	}

	protected Processo buscarProcesso(int index) {
		if (index >= processos.size()) {
			return null;
		}
		return processos.get(index).clone();
	}

	protected void atualizarProcesso(int index, Processo processo) {
		if (processo.isFirstRun()) {
			processo.setFirstRun(false);
		}		
		processos.set(index, processo);
	}
	
	protected void atualizarProcesso(Processo processo){
		if (processo.isFirstRun()) {
			processo.setFirstRun(false);
		}
		int index = 0;
		for(int i = 0; i < processos.size();i++){
			if(processo.getBurstTotal() > processos.get(i).getBurstTotal()){
				index = i;
			}
		}
		processos.add(++index, processo);
	}

	protected void removerProcesso(int index) {
		processos.remove(index);
	}

	protected void definirProcessos(ArrayList<Processo> processos) {
		this.processos = processos;
	}
	
	protected int recuperarIndex(Processo processo){
		return processos.indexOf(processo);
	}

	protected void adicionarResultado(Processo processo) {
		resultado.add(processo);
	}

	protected void adicionarResultadoGrafico(Processo processo) {
		resultadoGrafico.add(processo);
	}

	protected ArrayList<Processo> resultado() {
		return resultado;
	}

	protected ArrayList<Processo> resultadoGrafico() {
		return resultadoGrafico;
	}

	protected void validarProcessos(ArrayList<Processo> processos) {
		if (null != processos && !processos.isEmpty()) {
			for (Processo processo : processos) {
				if (processo.getBurstTotal() <= 0) {
					throw new NegativoBurstException();
				}
			}
		} else {
			throw new ProcessosNaoCarregadosException();
		}
	}
}