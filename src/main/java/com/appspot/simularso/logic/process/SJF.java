package com.appspot.simularso.logic.process;

import java.util.ArrayList;
import java.util.List;

import com.appspot.simularso.logic.EscalonadorProcesso;
import com.appspot.simularso.model.Processo;
import com.appspot.simularso.model.ProcessoDTO;
import com.appspot.simularso.model.ProcessoVO;

public class SJF extends EscalonadorProcessoBase implements EscalonadorProcesso {

	public SJF(ArrayList<Processo> processos, int tempoQuantum, int tempoContexto) {
		super(processos, tempoQuantum = 0, tempoContexto = 0);
		utilizarBurstOrder(true);
		ordernarProcessos();
		executar();
	}

	private void executar() {
		for (int i = 0; i < totalDeProcessos(); i++) {
			Processo processo = clonarProcesso(i);
			processo.executar();
			processo.setEspera(tempoTotal());
			processo.setBurstAtual(processo.getBurstTotal());

			adicionarResultadoGrafico(processo);
			if (i < totalDeProcessos() - 1) {
				atualizarTempoTotal(processo.getBurstTotal() + tempoContexto());
				processo.setResposta(tempoTotal() - tempoContexto());
				processo.setTurnAround(tempoTotal() - tempoContexto());
			} else {
				atualizarTempoTotal(processo.getBurstTotal());
				processo.setResposta(tempoTotal());
				processo.setTurnAround(tempoTotal());
			}
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
		return EscalonadorProcessoAlgoritmo.SJF.getNome();
	}
}
