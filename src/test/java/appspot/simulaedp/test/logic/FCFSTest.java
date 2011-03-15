package appspot.simulaedp.test.logic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import appspot.simulaedp.exception.ProcessosNaoCarregadosException;
import appspot.simulaedp.logic.Escalonador;
import appspot.simulaedp.logic.impl.FCFS;
import appspot.simulaedp.model.Processo;
import appspot.simulaedp.test.InitialCase;

/**
 * Testes para Escalonamento FCFS.
 * 
 * @author Caio Ribeiro Pereira
 * @since 12/02/2011
 */
public class FCFSTest extends InitialCase {

	@Test
	public void deveRetornarResultadoOrdenadoSimples() {
		final Integer[] BURSTS_SIMPLES = { 30, 10, 20, 50, 90 };
		final Integer[] TEMPO_ESPERA_PREVISTA_POR_BURST_SIMPLES = { 0, 30, 40, 60, 110 };
		final Integer[] TEMPO_RESPOSTA_PREVISTA_POR_BURST_SIMPLES = { 30, 40, 60, 110, 200 };
		final Integer[] TURN_AROUND_PREVISTA_POR_BURST_SIMPLES = { 30, 40, 60, 110, 200 };

		Escalonador fcfs = new FCFS(gerarArrayListDeProcessos(BURSTS_SIMPLES.length, BURSTS_SIMPLES, null, null));
		SortedSet<Processo> resultado = fcfs.resultadoFinal();
		LinkedList<Processo> resultadoGrafico = fcfs.resultadoGraficoFinal();

		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(BURSTS_SIMPLES.length));

		Iterator<Processo> resultSet = resultado.iterator();
		while (resultSet.hasNext()) {
			Processo proc = resultSet.next();
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_BURST_SIMPLES, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_BURST_SIMPLES, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_BURST_SIMPLES, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertThat(resultadoGrafico.size(), Matchers.is(BURSTS_SIMPLES.length));

		Iterator<Processo> resultSetGraphic = resultadoGrafico.iterator();
		while (resultSetGraphic.hasNext()) {
			Processo proc = resultSetGraphic.next();
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_BURST_SIMPLES, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_BURST_SIMPLES, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_BURST_SIMPLES, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		double esperaMedia = fcfs.tempoEsperaMedia();
		double respostaMedia = fcfs.tempoRespostaMedia();
		double turnAroundMedio = fcfs.tempoTurnAroundMedio();
		Assert.assertTrue(turnAroundMedio > 0.0);
		Assert.assertTrue(respostaMedia > 0.0);
		Assert.assertTrue(esperaMedia > 0.0);
		Assert.assertTrue(fcfs.totalProcessos() == BURSTS_SIMPLES.length);
	}

	@Test
	public void deveRetornarResultadoOrdenadoMedio() {
		final Integer[] BURSTS_MEDIO = { 20, 11, 39, 56, 9 };
		final Integer[] TEMPO_ESPERA_PREVISTA_POR_BURST_MEDIO = { 0, 20, 31, 70, 126 };
		final Integer[] TEMPO_RESPOSTA_PREVISTA_POR_BURST_MEDIO = { 20, 31, 70, 126, 135 };
		final Integer[] TURN_AROUND_PREVISTA_POR_BURST_MEDIO = { 20, 31, 70, 126, 135 };

		Escalonador fcfs = new FCFS(gerarArrayListDeProcessos(BURSTS_MEDIO.length, BURSTS_MEDIO, null, null));
		SortedSet<Processo> resultado = fcfs.resultadoFinal();
		LinkedList<Processo> resultadoGrafico = fcfs.resultadoGraficoFinal();

		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(BURSTS_MEDIO.length));

		Iterator<Processo> resultSet = resultado.iterator();
		while (resultSet.hasNext()) {
			Processo proc = resultSet.next();
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_BURST_MEDIO, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_BURST_MEDIO, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_BURST_MEDIO, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertThat(resultadoGrafico.size(), Matchers.is(BURSTS_MEDIO.length));

		Iterator<Processo> resultSetGraphic = resultadoGrafico.iterator();
		while (resultSetGraphic.hasNext()) {
			Processo proc = resultSetGraphic.next();
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_BURST_MEDIO, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_BURST_MEDIO, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_BURST_MEDIO, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		double esperaMedia = fcfs.tempoEsperaMedia();
		double respostaMedia = fcfs.tempoRespostaMedia();
		double turnAroundMedio = fcfs.tempoTurnAroundMedio();
		Assert.assertTrue(turnAroundMedio > 0.0);
		Assert.assertTrue(respostaMedia > 0.0);
		Assert.assertTrue(esperaMedia > 0.0);
		Assert.assertTrue(fcfs.totalProcessos() == BURSTS_MEDIO.length);
	}

	@Test
	public void deveEscalonarComDoisACemProcessos() {
		for (int i = 2; i <= 100; i++) {
			Escalonador fcfs = new FCFS(gerarListaDeProcessos(i, VALIDO));
			SortedSet<Processo> resultado = fcfs.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
		}
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveEscalonarProcessosComBurstNegativo() {
		new FCFS(gerarListaDeProcessos(3, INVALIDO));
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveGerarResultadoSemEscalonarOsProcessosAntes() {
		new FCFS(null);
	}

}
