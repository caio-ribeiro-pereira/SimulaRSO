package com.appspot.simularso.model;

import java.io.Serializable;

public class Disco implements Comparable<Disco>,Serializable {

	private static final long serialVersionUID = 4045790438519208862L;
	
	private int cilindro;

	public Disco() {
	}

	public Disco(int cilindro) {
		setCilindro(cilindro);
	}

	public void setCilindro(int cilindro) {
		this.cilindro = cilindro;
	}

	public int getCilindro() {
		return cilindro;
	}

	@Override
	public int compareTo(Disco disco) {
		if (this.cilindro > disco.getCilindro())
			return 1;
		if (this.cilindro < disco.getCilindro())
			return -1;
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cilindro;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Disco))
			return false;
		Disco other = (Disco) obj;
		if (cilindro != other.cilindro)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disco [cilindro=" + cilindro + "]\n";
	}
}