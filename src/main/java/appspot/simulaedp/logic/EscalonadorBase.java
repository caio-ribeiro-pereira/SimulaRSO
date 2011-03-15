package appspot.simulaedp.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

import appspot.simulaedp.exception.ProcessosNaoCarregadosException;
import appspot.simulaedp.model.Processo;

public abstract class EscalonadorBase {

	private SortedSet<Processo> resultado;
	private LinkedList<Processo> resultadoGrafico;
	private int tempoTotal;
	private int totalProcessos;
	private ArrayList<Processo> processos;

	public EscalonadorBase() {
		this.resultado = new TreeSet<Processo>();
		this.resultadoGrafico = new LinkedList<Processo>();
	}

	protected int tempoTotal() {
		return tempoTotal;
	}

	protected void atualizarTempoTotal(int burst) {
		this.tempoTotal += burst;
	}

	protected double calcularEsperaMedia() {
		int acumulado = 0;
		for (Processo proc : resultado) {
			acumulado += proc.getEspera();
		}
		return (acumulado / resultado.size());
	}

	protected double calcularRespostaMedia() {
		int acumulado = 0;
		for (Processo proc : resultado) {
			acumulado += proc.getResposta();
		}
		return (acumulado / resultado.size());
	}

	protected double calcularTurnAroundMedio() {
		int acumulado = 0;
		for (Processo proc : resultado) {
			acumulado += proc.getTurnAround();
		}
		return (acumulado / resultado.size());
	}

	protected int totalProcessosExecutados() {
		return totalProcessos;
	}

	protected int totalDeProcessos() {
		return processos.size();
	}

	protected void ordernarProcessos() {
		Collections.sort(processos);
	}

	protected Processo buscarProcesso(int index) {
		if (index >= processos.size()) {
			return null;
		}
		return processos.get(index).clone();
	}

	protected void atualizarProcesso(int index, Processo processo) {
		if (processo.isFirstRun()) {
			processo.setFirstRun(false);
		}
		processos.set(index, processo);
	}

	protected void atualizarProcesso(Processo processo) {
		if (processo.isFirstRun()) {
			processo.setFirstRun(false);
		}
		int index = 0;
		for (int i = 0; i < processos.size(); i++) {
			if (processo.getBurstTotal() > processos.get(i).getBurstTotal()) {
				index = i;
			}
		}
		processos.add(++index, processo);
	}

	protected void removerProcesso(int index) {
		processos.remove(index);
	}

	protected void enfileirarProcessos(ArrayList<Processo> processos) {
		this.processos = processos;
		this.processos.trimToSize();
		this.totalProcessos = processos.size();
	}

	protected int recuperarIndex(Processo processo) {
		return processos.indexOf(processo);
	}

	protected void adicionarResultado(Processo processo) {
		resultado.add(processo);
	}

	protected void adicionarResultadoGrafico(Processo processo) {
		resultadoGrafico.add(processo);
	}

	protected SortedSet<Processo> resultado() {
		return resultado;
	}

	protected LinkedList<Processo> resultadoGrafico() {
		return resultadoGrafico;
	}

	protected void validarProcessos(ArrayList<Processo> processos) {
		if (processos != null && !processos.isEmpty()) {
			for (Processo processo : processos) {
				if (processo.getBurstTotal() <= 0) {
					throw new ProcessosNaoCarregadosException();
				}
			}
		} else {
			throw new ProcessosNaoCarregadosException();
		}
	}
}