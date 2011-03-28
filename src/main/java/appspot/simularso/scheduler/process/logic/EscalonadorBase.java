package appspot.simularso.scheduler.process.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import appspot.simularso.exception.ProcessosConfiguracaoException;
import appspot.simularso.exception.ProcessosNaoCarregadosException;
import appspot.simularso.model.Processo;
import appspot.simularso.model.dto.ProcessoDTO;

public abstract class EscalonadorBase {

	private Set<Processo> resultado;
	private LinkedList<ProcessoDTO> resultadoGrafico;
	private int tempoTotal;
	private int totalProcessos;
	private ArrayList<Processo> processos;

	public EscalonadorBase() {
		this.resultado = new TreeSet<Processo>();
		this.resultadoGrafico = new LinkedList<ProcessoDTO>();
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

	protected void adicionarResultadoFinal(Processo processo) {
		resultado.add(processo);
	}

	protected void adicionarResultadoGrafico(Processo processo) {
		resultadoGrafico.add(extrairParaProcessoDTO(processo));
	}

	protected Set<Processo> resultado() {
		return resultado;
	}

	protected LinkedList<ProcessoDTO> resultadoGrafico() {
		return resultadoGrafico;
	}

	private ProcessoDTO extrairParaProcessoDTO(Processo processo) {
		int id = processo.getId();
		int x = tempoTotal * 20;
		int y = id * 20;
		int w = processo.getBurstAtual() * 20;
		int h = 20;
		String cor = processo.getCor();
		return new ProcessoDTO(id, x, y, w, h, cor);
	}

	protected void validarProcessos(ArrayList<Processo> processos) {
		if (processos != null && !processos.isEmpty()) {
			for (Processo processo : processos) {
				if (processo.getBurstTotal() <= 0 || processo.getChegada() < 0 || processo.getPrioridade() < 0) {
					throw new ProcessosConfiguracaoException();
				}
			}
		} else {
			throw new ProcessosNaoCarregadosException();
		}
	}
}