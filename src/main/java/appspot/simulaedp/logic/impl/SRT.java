package appspot.simulaedp.logic.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.SortedSet;

import appspot.simulaedp.logic.Escalonador;
import appspot.simulaedp.logic.EscalonadorBase;
import appspot.simulaedp.model.Processo;

public class SRT extends EscalonadorBase implements Escalonador {

	private boolean executando;
	private int index;
	private int tempoDeCorte;
	private int totalIDs;

	public SRT(ArrayList<Processo> processos) {
		super();
		validarProcessos(processos);
		enfileirarProcessos(processos);
		ordernarProcessos();
		executar();
	}

	private void executar() {
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
		processo.setResposta(atualizarTempoResposta(processo));
		processo.setBurstAtual(atualizarBurstAtual(processo));
		processo.setBurstTotal(atualizarBurstTotal(processo));
		atualizarTempoTotal(processo.getBurstAtual());
		processo.setTurnAround(atualizarTurnAround());
		processo.setEspera(atualizarTempoEspera(processo));
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
				int tempoChegadaAtual = processoAtual.getChegada();
				int tempoChegadaFuturo = processoFuturo.getChegada();
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
		int tempoResposta = processo.getResposta();
		return firstRun ? tempoTotal() : tempoResposta;
	}

	private void iniciar() {
		this.executando = true;
		this.index = 0;
		this.totalIDs = totalDeProcessos();
	}

	private void finalizar() {
		this.executando = false;
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
	public SortedSet<Processo> resultadoFinal() {
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
		return AlgoritmoProcesso.SRT.getNome();
	}
}
