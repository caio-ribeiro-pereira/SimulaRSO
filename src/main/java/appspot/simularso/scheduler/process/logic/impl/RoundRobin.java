package appspot.simularso.scheduler.process.logic.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import appspot.simularso.exception.TempoQuantumException;
import appspot.simularso.model.Processo;
import appspot.simularso.scheduler.process.logic.Escalonador;
import appspot.simularso.scheduler.process.logic.EscalonadorBase;

public class RoundRobin extends EscalonadorBase implements Escalonador {

	private static final int MIN_QUANTUM = 1;
	private static final int MAX_QUANTUM = 100;
	private int tempoQuantum;
	private int index;
	private boolean executando;

	public RoundRobin(ArrayList<Processo> processos, int tempoQuantum) {
		super();
		definirTempoQuantum(tempoQuantum);
		validarProcessos(processos);
		enfileirarProcessos(processos);
		executar();
	}

	private void executar() {
		iniciar();
		while (estiverEmExecucao()) {
			if (totalDeProcessos() > 0) {
				executarProcesso();
			} else {
				finalizar();
			}
		}
	}

	private void definirTempoQuantum(int tempoQuantum) {
		if (tempoQuantum >= MIN_QUANTUM && tempoQuantum <= MAX_QUANTUM) {
			this.tempoQuantum = tempoQuantum;
		} else {
			throw new TempoQuantumException();
		}
	}

	private boolean estiverEmExecucao() {
		return this.executando;
	}

	private void iniciar() {
		this.index = 0;
		this.executando = true;
	}

	private void finalizar() {
		this.executando = false;
	}

	private void executarProcesso() {
		Processo processo = buscarProcesso(index);
		processo.executar();
		processo.setResposta(atualizarTempoResposta(processo));
		processo.setBurstAtual(atualizarBurstAtual(processo));
		processo.setBurstTotal(atualizarBurstTotal(processo));
		atualizarTempoTotal(processo.getBurstAtual());
		processo.setTurnAround(atualizarTurnAround());
		processo.setEspera(atualizarTempoEspera(processo));
		atualizarProcessos(processo);
	}

	private void atualizarProcessos(Processo processo) {
		if (processo.terminou()) {
			processo.finalizar();
			adicionarResultadoFinal(processo);
			removerProcesso(index);
			atualizarIndex();
		} else {
			processo.esperar();
			atualizarProcesso(index, processo);
			avancarIndex();
		}
		adicionarResultadoGrafico(processo);
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
		return (processo.getBurstTotal() < tempoQuantum) ? processo.getBurstTotal() : tempoQuantum;
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
		return (processo.isFirstRun()) ? tempoTotal() : processo.getResposta();
	}

	@Override
	public int tempoExecucaoTotal() {
		return tempoTotal();
	}

	@Override
	public Set<Processo> resultadoFinal() {
		return resultado();
	}

	@Override
	public LinkedList<Processo> resultadoGraficoFinal() {
		return resultadoGrafico();
	}

	@Override
	public double tempoEsperaMedia() {
		return calcularEsperaMedia();
	}

	@Override
	public double tempoRespostaMedia() {
		return calcularRespostaMedia();
	}

	@Override
	public double tempoTurnAroundMedio() {
		return calcularTurnAroundMedio();
	}

	@Override
	public int totalProcessos() {
		return totalProcessosExecutados();
	}

	@Override
	public String algoritmoNome() {
		return AlgoritmoProcesso.ROUNDROBIN.getNome();
	}
}