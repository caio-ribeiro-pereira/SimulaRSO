package com.appspot.simularso.validator;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.exception.CilindroCabecaVaziaException;
import com.appspot.simularso.exception.RequisicaoCilindroException;
import com.appspot.simularso.exception.RequisicoesVaziaException;
import com.appspot.simularso.logic.disc.EscalonadorDiscoAlgoritmo;
import com.appspot.simularso.logic.disc.EscalonadorDiscoBase;
import com.appspot.simularso.model.Disco;

@Component
@ApplicationScoped
public class DiscoValidator implements Serializable {

	private static final long serialVersionUID = 3877522856341539889L;

	public DiscoValidator() {

	}

	public void validarEntrada(final List<EscalonadorDiscoAlgoritmo> algs, final LinkedList<Disco> requisicoes, final Disco cabeca,
			final int modo) {

		if (algs == null || algs.isEmpty() || algs.size() != modo) {
			throw new IllegalArgumentException();
		}
		if (algs.size() > 1 && algs.get(0).equals(algs.get(1))) {
			throw new IllegalStateException();
		}
		if (requisicoes == null || requisicoes.isEmpty()) {
			throw new RequisicoesVaziaException();
		}
		if (cabeca == null || cabeca.getCilindro() < EscalonadorDiscoBase.MIN) {
			throw new CilindroCabecaVaziaException();
		}
		for (Disco disco : requisicoes) {
			if (disco.getCilindro() < EscalonadorDiscoBase.MIN) {
				throw new RequisicaoCilindroException();
			}
		}
	}
}
