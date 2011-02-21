package br.com.simulaedp.logic.impl;

import java.util.ArrayList;
import java.util.LinkedList;

import br.com.simulaedp.logic.Escalonador;
import br.com.simulaedp.logic.EscalonadorBase;
import br.com.simulaedp.model.Processo;

public class SRT extends EscalonadorBase implements Escalonador {

	private boolean executando;
	private int index;
	private int tempoDeCorte;
	private int totalIDs;
	private LinkedList<Processo> filaDeEspera;

	public SRT(ArrayList<Processo> processos) {
		super();
		validarProcessos(processos);
		definirProcessos(processos);
		ordernarProcessos();
	}

	@Override
	public void executar() {
		iniciar();
		while (estiverEmExecucao()) {
			if (existirProcessoVivo()) {
				executarProcesso();
			} else {
				atualizarResultado();
				finalizar();
			}
		}
	}

	private void executarProcesso() {
		definirTempoDeCorte();
		Processo processo = definirProximoProcesso();
		processo.setTempoResposta(atualizarTempoResposta(processo));
		processo.setBurstAtual(atualizarBurstAtual(processo));
		processo.setBurstTotal(atualizarBurstTotal(processo));
		atualizarTempoTotal(processo.getBurstAtual());
		processo.setTurnAround(atualizarTurnAround());
		processo.setTempoEspera(atualizarTempoEspera(processo));
		atualizarProcessos(processo);
	}
	
	private Processo definirProximoProcesso(){
		Processo processoAtual = buscarProcesso(index);
		if(!filaDeEspera.isEmpty()){
			int burstAtual = processoAtual.getBurstTotal();
			int burstEmEspera = buscarPrimeiroDaFila().getBurstTotal(); 
			if(burstEmEspera <= burstAtual)
				return removerPrimeiroDaFila();
		}
		return processoAtual;
	}
	

	private void definirTempoDeCorte() {
		Processo processoAtual = buscarProcesso(index);
		Processo processoFuturo = buscarProcesso(index + 1);
		int burstAtual = processoAtual.getBurstTotal();
		atualizarTempoDeCorte(burstAtual);
		if (processoFuturo != null) {
			int burstFuturo = processoFuturo.getBurstTotal();
			if(burstFuturo < burstAtual){
				int tempoChegadaAtual = processoAtual.getTempoChegada();
				int tempoChegadaFuturo = processoFuturo.getTempoChegada();
				int diferencaTempoChegada = tempoChegadaFuturo - tempoChegadaAtual;
				if(tempoDeCorte > diferencaTempoChegada){
					atualizarTempoDeCorte(diferencaTempoChegada);
				}
			}
		}else if(validarFilaDeEspera()){
			Processo processoEmEspera = buscarPrimeiroDaFila();
			int burstEmEspera = processoEmEspera.getBurstTotal();
			if(burstEmEspera <= burstAtual){
				atualizarIndex(recuperarIndex(processoEmEspera));
				atualizarTempoDeCorte(processoEmEspera.getBurstTotal());	
			}
		}
	}

	private void atualizarProcessos(Processo processo) {
		adicionarResultadoGrafico(processo);
		if (processo.terminou()) {
			removerProcesso(index);
			otimizarFilaDeProcessos();
			atualizarIndex();
		} else {
			atualizarProcesso(index, processo);
			adicionarPrimeiroDaFila(processo);
			avancarIndex();
		}
	}
	
	private boolean existirProcessoVivo(){
		return (totalDeProcessos() > 0 && this.filaDeEspera != null);
	}

	private int atualizarTurnAround() {
		return tempoTotal();
	}

	private int atualizarBurstAtual(Processo processo) {
		int burstTotal = processo.getBurstTotal();
		return (burstTotal < tempoDeCorte) ? burstTotal : tempoDeCorte;
	}

	private int atualizarBurstTotal(Processo processo) {
		int novoBurst = processo.getBurstTotal() - tempoDeCorte;
		return (novoBurst < 0) ? 0 : novoBurst;
	}

	private int atualizarTempoEspera(Processo processo) {
		int espera = processo.getTurnAround() - processo.getBurst();
		return (espera < 0) ? 0 : espera;
	}

	private int atualizarTempoResposta(Processo processo) {
		boolean firstRun = processo.isFirstRun();
		int tempoResposta = processo.getTempoResposta();
		return firstRun ? tempoTotal() : tempoResposta;
	}
	
	private void iniciar() {
		this.filaDeEspera = new LinkedList<Processo>();
		this.executando = true;
		this.index = 0;
		this.totalIDs = totalDeProcessos();
		otimizarFilaDeProcessos();
	}

	private void finalizar() {
		this.filaDeEspera = null;
		this.executando = false;
		otimizarFilaDeResultados();
	}

	private void atualizarIndex() {
		index = (index >= totalDeProcessos()) ? (0) : (index);
	}
	
	private void atualizarIndex(int val){
		index = val;
	}

	private void avancarIndex() {
		index = (index + 1 >= totalDeProcessos()) ? (0) : (index + 1);
	}
	
	private void atualizarTempoDeCorte(int val){
		tempoDeCorte = val;
	}

	private boolean estiverEmExecucao() {
		return this.executando;
	}

	private Processo buscarPrimeiroDaFila(){
		return filaDeEspera.getFirst().clone();
	}
	
	private Processo removerPrimeiroDaFila(){
		return filaDeEspera.removeFirst().clone();
	}
	
	private void adicionarPrimeiroDaFila(Processo processo){
		filaDeEspera.addFirst(processo);
	}
	
	private boolean validarFilaDeEspera(){
		return (filaDeEspera != null && !filaDeEspera.isEmpty());
	}
	
	private void atualizarResultado() {
		for (int i = 1; i <= totalIDs; i++) {
			adicionarResultado(buscarUltimaOcorrencia(i));
		}
	}

	private Processo buscarUltimaOcorrencia(int id) {
		int index = resultadoGrafico().lastIndexOf(new Processo(id));
		return resultadoGrafico().get(index).clone();
	}

	@Override
	public int tempoExecucaoTotal() {
		return tempoTotal();
	}

	@Override
	public ArrayList<Processo> resultadoFinal() {
		return resultado();
	}

	@Override
	public ArrayList<Processo> resultadoGraficoFinal() {
		return resultadoGrafico();
	}

}
