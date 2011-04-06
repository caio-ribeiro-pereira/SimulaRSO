package com.appspot.simularso.scheduler.process.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.appspot.simularso.exception.ProcessosConfiguracaoException;
import com.appspot.simularso.exception.ProcessosNaoCarregadosException;
import com.appspot.simularso.model.Processo;
import com.appspot.simularso.model.ProcessoDTO;
import com.appspot.simularso.model.ProcessoVO;

public abstract class EscalonadorProcessoBase {

	protected static final int MIN_QUANTUM = 1;
	protected static final int MAX_QUANTUM = 100;

	private List<ProcessoVO> resultado;
	private List<ProcessoDTO> resultadoGrafico;
	private int tempoTotal;
	private int totalProcessos;
	private int tempoQuantum;
	private ArrayList<Processo> processos;

	public EscalonadorProcessoBase() {
		this.resultado = new LinkedList<ProcessoVO>();
		this.resultadoGrafico = new LinkedList<ProcessoDTO>();
	}

	public void adcionarTempoQuantum(int tempoQuantum) {
		this.tempoQuantum = tempoQuantum;
	}

	public int tempoQuantum() {
		return tempoQuantum;
	}

	protected int tempoTotal() {
		return tempoTotal;
	}

	protected void atualizarTempoTotal(int burst) {
		this.tempoTotal += burst;
	}

	protected double calcularEsperaMedia() {
		int acumulado = 0;
		for (ProcessoVO proc : resultado) {
			acumulado += proc.getEspera();
		}
		return (acumulado / resultado.size());
	}

	protected double calcularRespostaMedia() {
		int acumulado = 0;
		for (ProcessoVO proc : resultado) {
			acumulado += proc.getResposta();
		}
		return (acumulado / resultado.size());
	}

	protected double calcularTurnAroundMedio() {
		int acumulado = 0;
		for (ProcessoVO proc : resultado) {
			acumulado += proc.getTurnAround();
		}
		return (acumulado / resultado.size());
	}

	protected int totalProcessosExecutados() {
		return totalProcessos;
	}

	protected int totalDeProcessos() {
		return processos.size();
	}

	protected void ordernarProcessos() {
		Collections.sort(processos);
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

	protected void atualizarProcesso(Processo processo) {
		if (processo.isFirstRun()) {
			processo.setFirstRun(false);
		}
		int index = 0;
		for (int i = 0; i < processos.size(); i++) {
			if (processo.getBurstTotal() > processos.get(i).getBurstTotal()) {
				index = i;
			}
		}
		processos.add(++index, processo);
	}

	protected void removerProcesso(int index) {
		processos.remove(index);
	}

	protected void enfileirarProcessos(ArrayList<Processo> processos) {
		this.processos = processos;
		this.processos.trimToSize();
		this.totalProcessos = processos.size();
	}

	protected int recuperarIndex(Processo processo) {
		return processos.indexOf(processo);
	}

	protected void adicionarResultadoFinal(Processo processo) {
		resultado.add(extrairParaProcessoVO(processo));
	}

	protected void adicionarResultadoGrafico(Processo processo) {
		resultadoGrafico.add(extrairParaProcessoDTO(processo));
	}

	protected List<ProcessoVO> resultado() {
		Collections.sort(resultado);
		return resultado;
	}

	protected List<ProcessoDTO> resultadoGrafico() {
		return resultadoGrafico;
	}

	private ProcessoDTO extrairParaProcessoDTO(Processo processo) {
		int id = processo.getId();
		int x = tempoTotal;
		int y = id;
		int w = processo.getBurstAtual();
		int h = 1;
		String cor = processo.getCor();
		return new ProcessoDTO(id, x, y, w, h, cor);
	}

	private ProcessoVO extrairParaProcessoVO(Processo processo) {
		int id = processo.getId();
		int burst = processo.getBurst();
		int espera = processo.getEspera();
		int resposta = processo.getResposta();
		int turnAround = processo.getTurnAround();
		String cor = processo.getCor();
		return new ProcessoVO(id, burst, espera, resposta, turnAround, cor);
	}

	protected void validarProcessos(ArrayList<Processo> processos) {
		if (processos != null && !processos.isEmpty()) {
			for (Processo processo : processos) {
				if (processo.getBurstTotal() <= 0 || processo.getChegada() < 0 || processo.getPrioridade() < 0) {
					throw new ProcessosConfiguracaoException();
				}
			}
		} else {
			throw new ProcessosNaoCarregadosException();
		}
	}
}