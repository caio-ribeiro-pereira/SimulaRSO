package com.appspot.simularso.model;

import java.io.Serializable;

public class ProcessoVO implements Comparable<ProcessoVO>, Cloneable, Serializable {

	private static final long serialVersionUID = -5125893820411998437L;

	private int id;
	private int burst;
	private int espera;
	private int resposta;
	private int turnAround;
	private String cor;

	public ProcessoVO(int id, int burst, int espera, int resposta, int turnAround, String cor) {
		this.id = id;
		this.burst = burst;
		this.espera = espera;
		this.resposta = resposta;
		this.turnAround = turnAround;
		this.cor = cor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBurst() {
		return burst;
	}

	public void setBurst(int burst) {
		this.burst = burst;
	}

	public int getEspera() {
		return espera;
	}

	public void setEspera(int espera) {
		this.espera = espera;
	}

	public int getResposta() {
		return resposta;
	}

	public void setResposta(int resposta) {
		this.resposta = resposta;
	}

	public int getTurnAround() {
		return turnAround;
	}

	public void setTurnAround(int turnAround) {
		this.turnAround = turnAround;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProcessoVO))
			return false;
		ProcessoVO other = (ProcessoVO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProcessoVO [id=");
		builder.append(id);
		builder.append(", burst=");
		builder.append(burst);
		builder.append(", espera=");
		builder.append(espera);
		builder.append(", resposta=");
		builder.append(resposta);
		builder.append(", turnAround=");
		builder.append(turnAround);
		builder.append(", cor=");
		builder.append(cor);
		builder.append("]\n");
		return builder.toString();
	}

	@Override
	public int compareTo(ProcessoVO p) {
		if (this.id < p.getId())
			return -1;
		if (this.id > p.getId())
			return 1;
		return 0;
	}

}
