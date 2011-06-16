package com.appspot.simularso.validator;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.exception.FramesInvalidoException;
import com.appspot.simularso.exception.StringReferenciaInvalidaException;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaAlgoritmo;
import com.appspot.simularso.logic.memory.PaginacaoMemoriaBase;

@Component
@ApplicationScoped
public class PaginacaoValidator implements Serializable {

	private static final long serialVersionUID = 2709933030246028178L;

	public PaginacaoValidator() {

	}

	public void validarEntrada(final List<PaginacaoMemoriaAlgoritmo> algs, final List<Integer> stringRef, final Integer frames,
			final int modo) {

		if (algs == null || algs.isEmpty() || algs.size() != modo) {
			throw new IllegalArgumentException();
		}
		if (algs.size() > 1 && algs.get(0).equals(algs.get(1))) {
			throw new IllegalStateException();
		}
		if (stringRef == null || stringRef.size() <= 0) {
			throw new StringReferenciaInvalidaException();
		}
		if (frames < PaginacaoMemoriaBase.MIN_FRAME) {
			throw new FramesInvalidoException();
		}
	}
}
