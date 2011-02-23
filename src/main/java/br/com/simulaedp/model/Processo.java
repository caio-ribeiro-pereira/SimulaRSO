package br.com.simulaedp.model;

public class Processo implements Comparable<Processo>, Cloneable {
	
	private int id;
	private int burst;
	private int burstAtual;
	private int burstTotal;
	private int tempoEspera;
	private int tempoResposta;
	private int tempoChegada;
	private int prioridade;
	private int turnAround;
	private boolean firstRun;
	private Estado estado;
	
	public Processo(){
		this.firstRun = true;
		this.estado = Estado.EM_ESPERA;
	}
	
	public Processo(int id){
		this();
		this.id = id;
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
	public void setBurstTotal(int burstTotal){
		this.burstTotal = burstTotal;
	}
	public int getBurstTotal(){
		return this.burstTotal;
	}
	public void setTempoEspera(int tempoEspera) {
		this.tempoEspera = tempoEspera;
	}
	public int getTempoEspera() {
		return tempoEspera;
	}
	public void setTempoResposta(int tempoResposta) {
		this.tempoResposta = tempoResposta;
	}
	public int getTempoResposta() {
		return tempoResposta;
	}
	public void setTempoChegada(int tempoChegada) {
		this.tempoChegada = tempoChegada;
	}
	public int getTempoChegada() {
		return tempoChegada;
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
	
	public void setEstado(Estado estado){
		this.estado = estado;
	}
	
	public Estado getEstado(){
		return this.estado;
	}

	@Override
	public Processo clone() {
		try{
			return (Processo) super.clone();	
		}catch(CloneNotSupportedException e){
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
		sb.append(", tempoEspera=").append(tempoEspera);
		sb.append(", turnAround=").append(turnAround);
		sb.append(", tempoResposta=").append(tempoResposta);
		sb.append(", tempoChegada=").append(tempoChegada);
		sb.append(", estado=").append(estado);
		sb.append(", burst=").append(burst);
		sb.append(", prioridade=").append(prioridade);
		sb.append(", firstRun=").append(firstRun);
		sb.append("]\n");
		sb.trimToSize();
		return sb.toString();
	}

	public int compareTo(Processo processo) {
		if(this.getPrioridade() < processo.getPrioridade())
			return -1;
		if(this.getPrioridade() > processo.getPrioridade())
			return 1;
		if(this.getTempoChegada() < processo.getTempoChegada())
			return -1;
		if(this.getTempoChegada() > processo.getTempoChegada())
			return 1;
		if(this.getBurst() < processo.getBurst())
			return -1;
		if(this.getBurst() > processo.getBurst())
			return 1;
		return 0;
	}
	
	public boolean terminou(){
		return (this.burstTotal == 0);
	}
	
	public void executar(){
		this.estado = Estado.EXECUTANDO;
	}
	
	public void finalizar(){
		this.estado = Estado.FINALIZADO;
	}
	
	public void esperar(){
		this.estado = Estado.EM_ESPERA;
	}
}