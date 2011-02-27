package br.com.simulaedp.model;

import java.util.ArrayList;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ModelGenerator {

	public ModelGenerator() {
	}

	public ArrayList<Processo> gerarListaDeProcessos(int total, int[] bursts,
			int[] chegadas, int[] prioridades) {
		ArrayList<Processo> processos = new ArrayList<Processo>();
		for (int i = 0; i < total; i++) {
			Processo processo = new Processo((i + 1), bursts[i], chegadas[i],
					prioridades[i]);
			processos.add(processo);
		}
		processos.trimToSize();
		return processos;
	}
}
