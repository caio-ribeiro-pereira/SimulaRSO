package com.appspot.simularso.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pagina implements Serializable, Cloneable {

	private static final long serialVersionUID = -7399563107260088190L;

	private ArrayList<Integer> palavras;
	private boolean pageFault;

	public Pagina() {
		this.palavras = new ArrayList<Integer>();
	}

	public Pagina(ArrayList<Integer> palavras) {
		this.palavras = palavras;
	}

	public void incluir(Integer palavra) {
		this.palavras.add(palavra);
		this.pageFault = true;
	}

	public void atualizar(Integer index, Integer palavra) {
		this.palavras.set(index, palavra);
		this.pageFault = true;
	}

	public void iniciarPageFault() {
		this.pageFault = false;
	}

	public void setPalavras(ArrayList<Integer> palavras) {
		this.palavras = palavras;
	}

	public ArrayList<Integer> getPalavras() {
		return (ArrayList<Integer>) this.palavras.clone();
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
		builder.append(", pageFault=");
		builder.append(pageFault);
		builder.append("]\n");
		return builder.toString();
	}
}
