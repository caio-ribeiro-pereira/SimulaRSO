package com.appspot.simularso.logic.process;

import java.util.ArrayList;
import java.util.List;

import com.appspot.simularso.exception.TempoQuantumException;
import com.appspot.simularso.logic.EscalonadorProcesso;
import com.appspot.simularso.model.Processo;
import com.appspot.simularso.model.ProcessoDTO;
import com.appspot.simularso.model.ProcessoVO;

public class RR extends EscalonadorProcessoBase implements EscalonadorProcesso {

	private int index;
	private boolean executando;

	public RR(ArrayList<Processo> processos, int tempoQuantum) {
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
			adcionarTempoQuantum(tempoQuantum);
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

		adicionarResultadoGrafico(processo);
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
		return (processo.getBurstTotal() < tempoQuantum()) ? processo.getBurstTotal() : tempoQuantum();
	}

	private int atualizarBurstTotal(Processo processo) {
		int novoBurst = processo.getBurstTotal() - tempoQuantum();
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
	public List<ProcessoVO> resultadoFinal() {
		return resultado();
	}

	@Override
	public List<ProcessoDTO> resultadoGraficoFinal() {
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
		return EscalonadorProcessoAlgoritmo.RR.getNome();
	}
}