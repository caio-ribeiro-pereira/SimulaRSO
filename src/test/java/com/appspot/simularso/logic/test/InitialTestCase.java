package com.appspot.simularso.logic.test;

import java.util.ArrayList;
import java.util.LinkedList;

import com.appspot.simularso.model.Disco;
import com.appspot.simularso.model.Processo;

public abstract class InitialTestCase {

	protected static final boolean VALIDO = true;
	protected static final boolean INVALIDO = false;

	protected ArrayList<Processo> gerarArrayListDeProcessos(Integer total, Integer[] burstList, Integer[] chegadaList,
			Integer[] prioridadeList) {
		ArrayList<Processo> processos = new ArrayList<Processo>();
		for (int i = 0; i < total; i++) {
			processos.add(gerarProcessoValido((i + 1), ((burstList != null) ? burstList[i] : 100), ((chegadaList != null) ? chegadaList[i]
					: 0), ((prioridadeList != null) ? prioridadeList[i] : 1)));
		}
		return processos;
	}

	protected ArrayList<Processo> gerarArrayListDefault(int total) {
		ArrayList<Processo> processos = new ArrayList<Processo>();
		for (int i = 0; i < total; i++) {
			Processo processo = gerarProcessoValido(i + 1, 100, 0, 0);
			processos.add(processo);
		}
		return processos;
	}

	protected Processo gerarProcessoValido(int i, int burst, int chegada, int prioridade) {
		Processo processo = new Processo();
		processo.setId(i);
		processo.setBurst(burst);
		processo.setChegada(chegada);
		processo.setPrioridade(prioridade);
		return processo;
	}

	protected LinkedList<Disco> gerarListaDeRequisicoes(int[] cilindros) {
		LinkedList<Disco> requisicoes = new LinkedList<Disco>();
		for (int i = 0; i < cilindros.length; i++) {
			requisicoes.add(new Disco(cilindros[i]));
		}
		return requisicoes;
	}
}
