package com.appspot.simularso.validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.exception.ProcessosConfiguracaoException;
import com.appspot.simularso.exception.ProcessosNaoCarregadosException;
import com.appspot.simularso.logic.process.EscalonadorProcessoAlgoritmo;
import com.appspot.simularso.model.Processo;

@Component
@ApplicationScoped
public class ProcessoValidator implements Serializable {

	private static final long serialVersionUID = -8838123282462520145L;

	public ProcessoValidator() {
	}

	public void validarEntrada(final List<EscalonadorProcessoAlgoritmo> algs, final ArrayList<Processo> pr, final int qt, final int modo) {
		if (algs == null || algs.isEmpty() || algs.size() != modo) {
			throw new IllegalArgumentException();
		}
		if (algs.size() > 1 && algs.get(0).equals(algs.get(1))) {
			throw new IllegalStateException();
		}
		if (pr == null || pr.isEmpty()) {
			throw new ProcessosNaoCarregadosException();
		}
		for (Processo processo : pr) {
			if (processo.getBurstTotal() <= 0 || processo.getChegada() < 0 || processo.getPrioridade() < 0) {
				throw new ProcessosConfiguracaoException();
			}
		}
	}
}
