package br.com.simulaedp.logic;

import java.util.ArrayList;

import br.com.simulaedp.model.Processo;

public interface Escalonador {

	void executar();

	int tempoExecucaoTotal();

	ArrayList<Processo> resultadoFinal();

	ArrayList<Processo> resultadoGraficoFinal();
}
