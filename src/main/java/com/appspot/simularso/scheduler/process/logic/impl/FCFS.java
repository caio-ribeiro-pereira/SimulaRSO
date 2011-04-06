package com.appspot.simularso.scheduler.process.logic.impl;

import java.util.ArrayList;
import java.util.List;

import com.appspot.simularso.model.Processo;
import com.appspot.simularso.model.ProcessoDTO;
import com.appspot.simularso.model.ProcessoVO;
import com.appspot.simularso.scheduler.process.logic.EscalonadorProcesso;

public class FCFS extends EscalonadorProcessoBase implements EscalonadorProcesso {

	public FCFS(ArrayList<Processo> processos, int tempoQuantum) {
		super();
		validarProcessos(processos);
		enfileirarProcessos(processos);
		executar();
	}

	private void executar() {
		for (int i = 0; i < totalDeProcessos(); i++) {
			Processo processo = buscarProcesso(i);

			processo.executar();
			processo.setEspera(tempoTotal());
			processo.setBurstAtual(processo.getBurstTotal());

			adicionarResultadoGrafico(processo);

			atualizarTempoTotal(processo.getBurstTotal());

			processo.setResposta(tempoTotal());
			processo.setTurnAround(tempoTotal());
			processo.finalizar();

			adicionarResultadoFinal(processo);

		}
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
		return EscalonadorProcessoAlgoritmo.FCFS.getNome();
	}
}