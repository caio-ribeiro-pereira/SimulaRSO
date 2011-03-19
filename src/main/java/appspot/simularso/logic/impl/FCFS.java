package appspot.simularso.logic.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import appspot.simularso.logic.Escalonador;
import appspot.simularso.logic.EscalonadorBase;
import appspot.simularso.model.Processo;

public class FCFS extends EscalonadorBase implements Escalonador {

	public FCFS(ArrayList<Processo> processos) {
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

			atualizarTempoTotal(processo.getBurstTotal());

			processo.setResposta(tempoTotal());
			processo.setTurnAround(tempoTotal());
			processo.finalizar();

			adicionarResultadoFinal(processo);
			adicionarResultadoGrafico(processo);
		}
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
		return AlgoritmoProcesso.FCFS.getNome();
	}
}