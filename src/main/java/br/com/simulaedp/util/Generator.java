package br.com.simulaedp.util;

import java.util.ArrayList;

import br.com.caelum.vraptor.ioc.Component;
import br.com.simulaedp.model.Processo;

@Component
public class Generator {

	public Generator() {
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
