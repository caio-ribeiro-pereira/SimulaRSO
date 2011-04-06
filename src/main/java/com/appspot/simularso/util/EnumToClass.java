package com.appspot.simularso.util;

import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.paginator.memory.logic.impl.PaginacaoMemoriaAlgoritmo;
import com.appspot.simularso.scheduler.process.logic.impl.EscalonadorProcessoAlgoritmo;

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

	private String extractClass(String pack, String algoritmoName) {
		StringBuilder builder = new StringBuilder(pack);
		int index = pack.lastIndexOf(".") + 1;
		int max = pack.length();
		builder.delete(index, max);
		builder.append(algoritmoName);
		return builder.toString();
	}
}
