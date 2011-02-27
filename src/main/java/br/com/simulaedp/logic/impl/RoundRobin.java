package br.com.simulaedp.logic.impl;

import java.util.ArrayList;

import br.com.simulaedp.exception.NegativoQuantumException;
import br.com.simulaedp.logic.Escalonador;
import br.com.simulaedp.logic.EscalonadorBase;
import br.com.simulaedp.model.Estado;
import br.com.simulaedp.model.Processo;

public class RoundRobin extends EscalonadorBase implements Escalonador {

	private int tempoQuantum;
	private int totalIds;
	private int index;
	private boolean executando;

	public RoundRobin(ArrayList<Processo> processos, int tempoQuantum) {
		super();
		definirTempoQuantum(tempoQuantum);
		validarProcessos(processos);
		definirProcessos(processos);
	}

	private void definirTempoQuantum(int tempoQuantum) {
		if (tempoQuantum > 0) {
			this.tempoQuantum = tempoQuantum;
		} else {
			throw new NegativoQuantumException();
		}
	}

	private boolean estiverEmExecucao() {
		return this.executando;
	}

	private void iniciar() {
		this.index = 0;
		this.executando = true;
		this.totalIds = totalDeProcessos();
	}

	private void finalizar() {
		this.executando = false;
		otimizarFilaDeResultados();
	}

	@Override
	public void executar() {
		iniciar();
		while (estiverEmExecucao()) {
			if (totalDeProcessos() > 0) {
				executarProcesso();
			} else {
				finalizar();
				atualizarResultado();
			}
		}
	}

	private void executarProcesso() {
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

	private void atualizarProcessos(Processo processo) {
		if (processo.getBurstTotal() == 0) {
			processo.finalizar();
			removerProcesso(index);
			atualizarIndex();
		} else {
			processo.esperar();
			atualizarProcesso(index, processo);
			avancarIndex();
		}
		adicionarResultadoGrafico(processo);
	}

	private void atualizarResultado() {
		for (int i = 1; i <= totalIds; i++) {
			adicionarResultado(buscarUltimaOcorrencia(i));
		}
	}

	private Processo buscarUltimaOcorrencia(int id) {
		Processo processoFinalizado = new Processo();
		processoFinalizado.setId(id);
		processoFinalizado.setEstado(Estado.FINALIZADO);
		int index = resultadoGrafico().lastIndexOf(processoFinalizado);
		return resultadoGrafico().get(index).clone();
	}

	private void atualizarIndex() {
		index = (index >= totalDeProcessos()) ? (0) : (index);
	}

	private void avancarIndex() {
		index = (index + 1 >= totalDeProcessos()) ? (0) : (index + 1);
	}

	private int atualizarTurnAround() {
		return tempoTotal();
	}

	private int atualizarBurstAtual(Processo processo) {
		return (processo.getBurstTotal() < tempoQuantum) ? processo
				.getBurstTotal() : tempoQuantum;
	}

	private int atualizarBurstTotal(Processo processo) {
		int novoBurst = processo.getBurstTotal() - tempoQuantum;
		return (novoBurst < 0) ? 0 : novoBurst;
	}

	private int atualizarTempoEspera(Processo processo) {
		int espera = processo.getTurnAround() - processo.getBurst();
		return (espera < 0) ? 0 : espera;
	}

	private int atualizarTempoResposta(Processo processo) {
		return (processo.isFirstRun()) ? tempoTotal() : processo
				.getTempoResposta();
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