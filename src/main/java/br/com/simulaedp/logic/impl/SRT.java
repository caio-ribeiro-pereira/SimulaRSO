package br.com.simulaedp.logic.impl;

import java.util.ArrayList;

import br.com.simulaedp.logic.Escalonador;
import br.com.simulaedp.logic.EscalonadorBase;
import br.com.simulaedp.model.Processo;

public class SRT extends EscalonadorBase implements Escalonador {

	private boolean executando;
	private int index;
	private int tempoDeCorte;
	private int totalIDs;

	public SRT(ArrayList<Processo> processos) {
		super();
		validarProcessos(processos);
		definirProcessos(processos);
		ordernarProcessos();
	}

	@Override
	public void executar() {
		iniciar();
		while (estiverEmExecucao()) {
			if (totalDeProcessos() > 0) {
				executarProcesso();
			} else {
				atualizarResultado();
				finalizar();
			}
		}
	}

	private void executarProcesso() {
		definirTempoDeCorte();
		Processo processo = buscarProcesso(index);
		processo.executar();
		processo.setTempoResposta(atualizarTempoResposta(processo));
		processo.setBurstAtual(atualizarBurstAtual(processo));
		processo.setBurstTotal(atualizarBurstTotal(processo));
		atualizarTempoTotal(processo.getBurstAtual());
		processo.setTurnAround(atualizarTurnAround());
		processo.setTempoEspera(atualizarTempoEspera(processo));
		atualizarProcessos(processo);
	}

	private void definirTempoDeCorte() {
		Processo processoAtual = buscarProcesso(index);
		Processo processoFuturo = buscarProcesso(index + 1);
		int burstAtual = processoAtual.getBurstTotal();
		atualizarTempoDeCorte(burstAtual);
		if (processoFuturo != null) {
			int burstFuturo = processoFuturo.getBurstTotal();
			if (burstFuturo < burstAtual) {
				int tempoChegadaAtual = processoAtual.getTempoChegada();
				int tempoChegadaFuturo = processoFuturo.getTempoChegada();
				int diferenca = tempoChegadaFuturo - tempoChegadaAtual;
				if (tempoDeCorte > diferenca) {
					atualizarTempoDeCorte(diferenca);
				}
			}
		}
	}

	private void atualizarProcessos(Processo processo) {
		if (processo.terminou()) {
			processo.finalizar();
		} else {
			processo.esperar();
			atualizarProcesso(processo);
		}
		removerProcesso(index);
		atualizarIndex();
		adicionarResultadoGrafico(processo);
	}

	private int atualizarTurnAround() {
		return tempoTotal();
	}

	private int atualizarBurstAtual(Processo processo) {
		int burstTotal = processo.getBurstTotal();
		return (burstTotal < tempoDeCorte) ? burstTotal : tempoDeCorte;
	}

	private int atualizarBurstTotal(Processo processo) {
		int novoBurst = processo.getBurstTotal() - tempoDeCorte;
		return (novoBurst < 0) ? 0 : novoBurst;
	}

	private int atualizarTempoEspera(Processo processo) {
		int espera = processo.getTurnAround() - processo.getBurst();
		return (espera < 0) ? 0 : espera;
	}

	private int atualizarTempoResposta(Processo processo) {
		boolean firstRun = processo.isFirstRun();
		int tempoResposta = processo.getTempoResposta();
		return firstRun ? tempoTotal() : tempoResposta;
	}

	private void iniciar() {
		this.executando = true;
		this.index = 0;
		this.totalIDs = totalDeProcessos();
	}

	private void finalizar() {
		this.executando = false;
		otimizarFilaDeResultados();
	}

	private void atualizarIndex() {
		index = (index >= totalDeProcessos()) ? (0) : (index);
	}

	private void atualizarTempoDeCorte(int val) {
		tempoDeCorte = val;
	}

	private boolean estiverEmExecucao() {
		return this.executando;
	}

	private void atualizarResultado() {
		for (int i = 1; i <= totalIDs; i++) {
			adicionarResultado(buscarUltimaOcorrencia(i));
		}
	}

	private Processo buscarUltimaOcorrencia(int id) {
		int index = resultadoGrafico().lastIndexOf(new Processo(id));
		return resultadoGrafico().get(index).clone();
	}

	@Override
	public int tempoExecucaoTotal() {
		return tempoTotal();
	}

	@Override
	public ArrayList<Processo> resultadoFinal() {
		return resultado();
	}

	@Override
	public ArrayList<Processo> resultadoGraficoFinal() {
		return resultadoGrafico();
	}

}
