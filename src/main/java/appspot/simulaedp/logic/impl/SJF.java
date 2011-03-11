package appspot.simulaedp.logic.impl;

import java.util.ArrayList;

import appspot.simulaedp.logic.Escalonador;
import appspot.simulaedp.logic.EscalonadorBase;
import appspot.simulaedp.model.Processo;

public class SJF extends EscalonadorBase implements Escalonador {

	public SJF(ArrayList<Processo> processos) {
		super();
		validarProcessos(processos);
		enfileirarProcessos(processos);
		ordernarProcessos();
	}

	@Override
	public void executar() {
		for (int i = 0; i < totalDeProcessos(); i++) {
			Processo processo = buscarProcesso(i);
			processo.executar();
			processo.setEspera(tempoTotal());
			processo.setBurstAtual(processo.getBurstTotal());
			atualizarTempoTotal(processo.getBurstTotal());
			processo.setResposta(tempoTotal());
			processo.setTurnAround(tempoTotal());
			processo.finalizar();
			adicionarResultado(processo);
			adicionarResultadoGrafico(processo);
		}
		otimizarFilaDeResultados();
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
		return AlgoritmoProcesso.SJF.getNome();
	}
}
