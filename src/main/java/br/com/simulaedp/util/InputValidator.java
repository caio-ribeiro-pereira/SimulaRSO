package br.com.simulaedp.util;

import br.com.caelum.vraptor.ioc.Component;
import br.com.simulaedp.model.Algoritmo;

@Component
public class InputValidator {

	public InputValidator() {
	}

	public void validar(int total, Algoritmo algoritmo, int[] bursts,
			int[] chegadas, int[] prioridades, int quantum) {
		if (!validarTotal(total))
			throw new IllegalArgumentException();
		if (!validarQuantum(quantum))
			throw new IllegalArgumentException();
		if (!validarAlgoritmo(algoritmo))
			throw new IllegalArgumentException();
		if (!validarArrays(bursts))
			throw new IllegalArgumentException();
		if (!validarArrays(chegadas))
			throw new IllegalArgumentException();
		if (!validarArrays(prioridades))
			throw new IllegalArgumentException();
	}

	private boolean validarTotal(int total) {
		return (total >= 2 && total <= 20);
	}

	private boolean validarQuantum(int quantum) {
		return (quantum >= 10 || quantum <= 100);
	}

	private boolean validarAlgoritmo(Algoritmo algoritmo) {
		if (algoritmo == null) {
			return false;
		}
		for (Algoritmo algoritmos : Algoritmo.values()) {
			if (algoritmo == algoritmos) {
				return true;
			}
		}
		return false;
	}

	private boolean validarArrays(int[] array) {
		return (array != null);
	}
}
