package com.appspot.simularso.model;

import java.io.Serializable;

public class Processo implements Comparable<Processo>, Cloneable, Serializable {

	private static final long serialVersionUID = 4135991502330565347L;

	public enum Estado {
		EM_ESPERA, EXECUTANDO, FINALIZADO
	}

	private int id;
	private int burst;
	private int burstAtual;
	private int burstTotal;
	private int espera;
	private int resposta;
	private int chegada;
	private int prioridade;
	private int turnAround;
	private boolean firstRun;
	private String cor;
	private Estado estado;

	public Processo() {
		this(1, 0, 0, 0);
	}

	public Processo(int id) {
		this(id, 0, 0, 0);
	}

	public Processo(int id, int burst) {
		this(id, burst, 0, 0);
	}

	public Processo(int id, int burst, int tempoChegada) {
		this(id, burst, tempoChegada, 0);
	}

	public Processo(int id, int burst, int tempoChegada, int prioridade) {
		this.firstRun = true;
		this.estado = Estado.EM_ESPERA;
		this.id = id;
		this.burst = burst;
		this.chegada = tempoChegada;
		this.prioridade = prioridade;
		// TODO - APAGAR DEPOIS
		this.burstTotal = burst;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setBurst(int burst) {
		this.burst = burst;
		setBurstTotal(burst);
	}

	public int getBurst() {
		return burst;
	}

	public void setBurstAtual(int burstAtual) {
		this.burstAtual = burstAtual;
	}

	public int getBurstAtual() {
		return burstAtual;
	}

	public void setBurstTotal(int burstTotal) {
		this.burstTotal = burstTotal;
	}

	public int getBurstTotal() {
		return this.burstTotal;
	}

	public void setEspera(int espera) {
		this.espera = espera;
	}

	public int getEspera() {
		return espera;
	}

	public void setResposta(int resposta) {
		this.resposta = resposta;
	}

	public int getResposta() {
		return resposta;
	}

	public void setChegada(int chegada) {
		this.chegada = chegada;
	}

	public int getChegada() {
		return chegada;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setTurnAround(int turnAround) {
		this.turnAround = turnAround;
	}

	public int getTurnAround() {
		return turnAround;
	}

	public void setFirstRun(boolean firstRun) {
		this.firstRun = firstRun;
	}

	public boolean isFirstRun() {
		return firstRun;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getCor() {
		return cor;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public boolean terminou() {
		return (this.burstTotal == 0);
	}

	public void executar() {
		this.estado = Estado.EXECUTANDO;
	}

	public void finalizar() {
		this.estado = Estado.FINALIZADO;
	}

	public void esperar() {
		this.estado = Estado.EM_ESPERA;
	}

	@Override
	public Processo clone() {
		try {
			return (Processo) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
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
		if (!(obj instanceof Processo))
			return false;
		Processo other = (Processo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Processo");
		sb.append("[id=").append(id);
		sb.append(", burstAtual=").append(burstAtual);
		sb.append(", burstTotal=").append(burstTotal);
		sb.append(", burst=").append(burst);
		sb.append(", turnAround=").append(turnAround);
		sb.append(", espera=").append(espera);
		sb.append(", resposta=").append(resposta);
		sb.append(", chegada=").append(chegada);
		sb.append(", estado=").append(estado);
		sb.append(", prioridade=").append(prioridade);
		sb.append(", firstRun=").append(firstRun);
		sb.append(", cor=").append(cor);
		sb.append("]\n");
		sb.trimToSize();
		return sb.toString();
	}

	public int compareTo(Processo processo) {
		if (this.getPrioridade() < processo.getPrioridade())
			return -1;
		if (this.getPrioridade() > processo.getPrioridade())
			return 1;
		if (this.getChegada() < processo.getChegada())
			return -1;
		if (this.getChegada() > processo.getChegada())
			return 1;
		if (this.getBurst() < processo.getBurst())
			return -1;
		if (this.getBurst() > processo.getBurst())
			return 1;
		if (this.getId() < processo.getId())
			return -1;
		if (this.getId() > processo.getId())
			return 1;
		return 0;
	}

}