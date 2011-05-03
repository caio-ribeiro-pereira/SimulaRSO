package com.appspot.simularso.infra;

import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.logic.disc.EscalonadorDiscoAlgoritmo;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaAlgoritmo;
import com.appspot.simularso.logic.process.EscalonadorProcessoAlgoritmo;

@Component
public class EnumToClass {

	public EnumToClass() {
	}

	public String extractClassFromEnum(EscalonadorProcessoAlgoritmo algoritmo) {
		String pack = algoritmo.getClass().getName();
		String algoritmoName = algoritmo.name();
		return extractClass(pack, algoritmoName);
	}

	public String extractClassFromEnum(PaginacaoMemoriaAlgoritmo algoritmo) {
		String pack = algoritmo.getClass().getName();
		String algoritmoName = algoritmo.name();
		return extractClass(pack, algoritmoName);
	}
	
	public String extractClassFromEnum(EscalonadorDiscoAlgoritmo algoritmo) {
		String pack = algoritmo.getClass().getName();
		String algoritmoName = algoritmo.name();
		return extractClass(pack, algoritmoName);
	}

	private String extractClass(String pack, String algoritmoName) {
		StringBuilder builder = new StringBuilder(pack);
		int index = pack.lastIndexOf(".") + 1;
		int max = pack.length();
		builder.delete(index, max);
		builder.append(algoritmoName);
		return builder.toString();
	}
}
