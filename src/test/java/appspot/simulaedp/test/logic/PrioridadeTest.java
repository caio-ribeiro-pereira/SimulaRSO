package appspot.simulaedp.test.logic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import appspot.simulaedp.exception.ProcessosNaoCarregadosException;
import appspot.simulaedp.logic.Escalonador;
import appspot.simulaedp.logic.impl.Prioridade;
import appspot.simulaedp.model.Processo;
import appspot.simulaedp.test.InitialCase;

public class PrioridadeTest extends InitialCase {

	@Test
	public void deveRetornarResultadoOrdenadoPorPrioridadeSimples() {
		final Integer[] BURSTS_SIMPLES = { 100, 20, 50, 10, 40 };
		final Integer[] PRIORIDADES_SIMPLES = { 1, 5, 3, 4, 2 };
		final Integer[] ID_PREVISTO_POR_PRIORIDADE_SIMPLES = { 1, 5, 3, 4, 2 };
		final Integer[] TEMPO_ESPERA_PREVISTA_POR_BURSTS_SIMPLES = { 0, 100, 140, 190, 200 };
		final Integer[] TEMPO_RESPOSTA_PREVISTA_POR_BURSTS_SIMPLES = { 100, 140, 190, 200, 220 };
		final Integer[] TURN_AROUND_PREVISTA_POR_BURSTS_SIMPLES = { 100, 140, 190, 200, 220 };

		Escalonador prioridade = new Prioridade(gerarArrayListDeProcessos(BURSTS_SIMPLES.length, BURSTS_SIMPLES, null, PRIORIDADES_SIMPLES));
		SortedSet<Processo> resultado = prioridade.resultadoFinal();

		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(BURSTS_SIMPLES.length));

		Iterator<Processo> resultSet = resultado.iterator();
		while (resultSet.hasNext()) {
			Processo proc = resultSet.next();
			Assert.assertThat(ID_PREVISTO_POR_PRIORIDADE_SIMPLES, Matchers.hasItemInArray(proc.getId()));
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		LinkedList<Processo> resultadoGrafico = prioridade.resultadoGraficoFinal();

		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertTrue(resultadoGrafico.size() == BURSTS_SIMPLES.length);

		Iterator<Processo> resultSetGraphic = resultadoGrafico.iterator();
		while (resultSetGraphic.hasNext()) {
			Processo proc = resultSetGraphic.next();
			Assert.assertThat(ID_PREVISTO_POR_PRIORIDADE_SIMPLES, Matchers.hasItemInArray(proc.getId()));
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		double esperaMedia = prioridade.tempoEsperaMedia();
		double respostaMedia = prioridade.tempoRespostaMedia();
		double turnAroundMedio = prioridade.tempoTurnAroundMedio();
		Assert.assertTrue(turnAroundMedio > 0.0);
		Assert.assertTrue(respostaMedia > 0.0);
		Assert.assertTrue(esperaMedia > 0.0);
		Assert.assertTrue(prioridade.totalProcessos() == BURSTS_SIMPLES.length);
	}

	@Test
	public void deveRetornarResultadoOrdenadoPorPrioridadeMedio() {
		final Integer[] BURSTS_SIMPLES = { 20, 30, 22, 13, 45, 50 };
		final Integer[] PRIORIDADES_SIMPLES = { 1, 2, 2, 3, 4, 5 };
		final Integer[] ID_PREVISTO_POR_PRIORIDADE_SIMPLES = { 1, 3, 2, 4, 5, 6 };
		final Integer[] TEMPO_ESPERA_PREVISTA_POR_BURSTS_SIMPLES = { 0, 20, 42, 72, 85, 130 };
		final Integer[] TEMPO_RESPOSTA_PREVISTA_POR_BURSTS_SIMPLES = { 20, 42, 72, 85, 130, 180 };
		final Integer[] TURN_AROUND_PREVISTA_POR_BURSTS_SIMPLES = { 20, 42, 72, 85, 130, 180 };

		Escalonador prioridade = new Prioridade(gerarArrayListDeProcessos(BURSTS_SIMPLES.length, BURSTS_SIMPLES, null, PRIORIDADES_SIMPLES));
		SortedSet<Processo> resultado = prioridade.resultadoFinal();

		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(BURSTS_SIMPLES.length));

		Iterator<Processo> resultSet = resultado.iterator();
		while (resultSet.hasNext()) {
			Processo proc = resultSet.next();
			Assert.assertThat(ID_PREVISTO_POR_PRIORIDADE_SIMPLES, Matchers.hasItemInArray(proc.getId()));
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		LinkedList<Processo> resultadoGrafico = prioridade.resultadoGraficoFinal();

		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertTrue(resultadoGrafico.size() == BURSTS_SIMPLES.length);

		Iterator<Processo> resultSetGraphic = resultadoGrafico.iterator();
		while (resultSetGraphic.hasNext()) {
			Processo proc = resultSetGraphic.next();
			Assert.assertThat(ID_PREVISTO_POR_PRIORIDADE_SIMPLES, Matchers.hasItemInArray(proc.getId()));
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		double esperaMedia = prioridade.tempoEsperaMedia();
		double respostaMedia = prioridade.tempoRespostaMedia();
		double turnAroundMedio = prioridade.tempoTurnAroundMedio();
		Assert.assertTrue(turnAroundMedio > 0.0);
		Assert.assertTrue(respostaMedia > 0.0);
		Assert.assertTrue(esperaMedia > 0.0);
		Assert.assertTrue(prioridade.totalProcessos() == BURSTS_SIMPLES.length);
	}

	@Test
	public void deveEscalonarComDoisACemProcessos() {
		for (int i = 2; i <= 100; i++) {
			Escalonador prioridade = new Prioridade(gerarListaDeProcessos(i, VALIDO));
			SortedSet<Processo> resultado = prioridade.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
		}
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveEscalonarProcessosComBurstNegativo() {
		new Prioridade(gerarListaDeProcessos(3, INVALIDO));
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveGerarResultadoSemEscalonarOsProcessosAntes() {
		new Prioridade(null);
	}
}