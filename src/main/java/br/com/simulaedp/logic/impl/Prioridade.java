package br.com.simulaedp.logic.impl;

import java.util.ArrayList;

import br.com.simulaedp.logic.Escalonador;
import br.com.simulaedp.logic.EscalonadorBase;
import br.com.simulaedp.model.Processo;

public class Prioridade extends EscalonadorBase implements Escalonador {

	public Prioridade(ArrayList<Processo> processos) {
		super();
		validarProcessos(processos);
		definirProcessos(processos);
		ordernarProcessos();
	}

	@Override
	public void executar() {
		otimizarFilaDeProcessos();
		for (int i = 0; i < totalDeProcessos(); i++) {
			Processo processo = buscarProcesso(i);
			processo.executar();
			processo.setTempoEspera(tempoTotal());
			processo.setBurstAtual(processo.getBurstTotal());
			atualizarTempoTotal(processo.getBurstTotal());
			processo.setTempoResposta(tempoTotal());
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
}