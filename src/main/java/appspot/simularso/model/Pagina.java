package appspot.simularso.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pagina implements Serializable, Cloneable {

	private static final long serialVersionUID = 6894337553437317181L;

	private List<Integer> palavras;

	public Pagina() {
		this.palavras = new ArrayList<Integer>();
	}

	public void incluir(Integer palavra) {
		this.palavras.add(palavra);
	}

	public void atualizar(Integer index, Integer palavra) {
		this.palavras.set(index, palavra);
	}

	public List<Integer> getPalavras() {
		return this.palavras;
	}

	public Integer totalPalavras() {
		return this.palavras.size();
	}

	@Override
	public Pagina clone() {
		try {
			return (Pagina) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pagina [palavras=");
		builder.append(palavras);
		builder.append("]\n");
		return builder.toString();
	}
}
