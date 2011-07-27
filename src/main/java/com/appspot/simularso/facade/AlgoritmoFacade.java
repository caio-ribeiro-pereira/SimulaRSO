package com.appspot.simularso.facade;

import com.appspot.simularso.logic.disc.EscalonadorDiscoAlgoritmo;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaAlgoritmo;
import com.appspot.simularso.logic.process.EscalonadorProcessoAlgoritmo;

public abstract class AlgoritmoFacade {

	public AlgoritmoFacade() {
	}

	public String extrairAlgoritmoNome(EscalonadorProcessoAlgoritmo algoritmo) {
		String pack = algoritmo.getClass().getName();
		String algoritmoName = algoritmo.name();
		return classExtractor(pack, algoritmoName);
	}

	public String extrairAlgoritmoNome(PaginacaoMemoriaAlgoritmo algoritmo) {
		String pack = algoritmo.getClass().getName();
		String algoritmoName = algoritmo.name();
		return classExtractor(pack, algoritmoName);
	}

	public String extrairAlgoritmoNome(EscalonadorDiscoAlgoritmo algoritmo) {
		String pack = algoritmo.getClass().getName();
		String algoritmoName = algoritmo.name();
		return classExtractor(pack, algoritmoName);
	}

	private String classExtractor(String pack, String algoritmoName) {
		StringBuilder builder = new StringBuilder(pack);
		int index = pack.lastIndexOf(".") + 1;
		int max = pack.length();
		builder.delete(index, max);
		builder.append(algoritmoName);
		return builder.toString();
	}
}
