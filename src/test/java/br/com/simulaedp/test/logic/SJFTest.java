package br.com.simulaedp.test.logic;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import br.com.simulaedp.exception.NegativoBurstException;
import br.com.simulaedp.exception.ProcessosNaoCarregadosException;
import br.com.simulaedp.logic.Escalonador;
import br.com.simulaedp.logic.impl.SJF;
import br.com.simulaedp.model.Processo;
import br.com.simulaedp.test.InitialCase;

public class SJFTest extends InitialCase {

	private static final int[] TEMPOS_CHEGADA = { 10, 20, 5, 3, 1 };
	private static final int[] ID_PREVISTO_POR_CHEGADA = { 5, 4, 3, 1, 2 };
	private static final int[] TEMPO_ESPERA_PREVISTA_POR_CHEGADA = { 0, 100,
			200, 300, 400 };
	private static final int[] TEMPO_RESPOSTA_PREVISTA_POR_CHEGADA = { 100,
			200, 300, 400, 500 };
	private static final int[] TURN_AROUND_PREVISTA_POR_CHEGADA = { 100, 200,
			300, 400, 500 };
	private static final int[] BURSTS = { 100, 20, 50, 10, 40 };
	private static final int[] ID_PREVISTO_POR_BURST = { 4, 2, 5, 3, 1 };
	private static final int[] TEMPO_ESPERA_PREVISTA_POR_BURST = { 0, 10, 30,
			70, 120 };
	private static final int[] TEMPO_RESPOSTA_PREVISTA_POR_BURST = { 10, 30,
			70, 120, 220 };
	private static final int[] TURN_AROUND_PREVISTA_POR_BURST = { 10, 30, 70,
			120, 220 };

	@Test
	public void deveRetornarResultadoOrdenadoPorChegada() {
		Escalonador sjf = new SJF(
				gerarArrayListDeProcessos(TEMPOS_CHEGADA.length, null, TEMPOS_CHEGADA, null));
		sjf.executar();
		List<Processo> resultado = sjf.resultadoFinal();
		List<Processo> resultadoGrafico = sjf.resultadoGraficoFinal();
		checarResultadoOrdenadoPorChegada(resultado);
		checarResultadoOrdenadoPorChegada(resultadoGrafico);
	}

	private void checarResultadoOrdenadoPorChegada(List<Processo> resultado) {
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(TEMPOS_CHEGADA.length));
		for (int i = 0; i < resultado.size(); i++) {
			Assert.assertThat(resultado.get(i).getId(),
					Matchers.equalTo(ID_PREVISTO_POR_CHEGADA[i]));
			Assert.assertThat(resultado.get(i).getTempoEspera(),
					Matchers.equalTo(TEMPO_ESPERA_PREVISTA_POR_CHEGADA[i]));
			Assert.assertThat(resultado.get(i).getTempoResposta(),
					Matchers.equalTo(TEMPO_RESPOSTA_PREVISTA_POR_CHEGADA[i]));
			Assert.assertThat(resultado.get(i).getTurnAround(),
					Matchers.equalTo(TURN_AROUND_PREVISTA_POR_CHEGADA[i]));
		}
	}

	@Test
	public void deveRetornarResultadoOrdenadoPorBursts() {
		Escalonador sjf = new SJF(gerarArrayListDeProcessos(BURSTS.length, BURSTS, null, null));
		sjf.executar();
		List<Processo> resultado = sjf.resultadoFinal();
		List<Processo> resultadoGrafico = sjf.resultadoGraficoFinal();
		checarResultadoOrdenadoPorBurst(resultado);
		checarResultadoOrdenadoPorBurst(resultadoGrafico);
	}

	private void checarResultadoOrdenadoPorBurst(List<Processo> resultado) {
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(BURSTS.length));
		for (int i = 0; i < resultado.size(); i++) {
			Assert.assertThat(resultado.get(i).getId(),
					Matchers.equalTo(ID_PREVISTO_POR_BURST[i]));
			Assert.assertThat(resultado.get(i).getTempoEspera(),
					Matchers.equalTo(TEMPO_ESPERA_PREVISTA_POR_BURST[i]));
			Assert.assertThat(resultado.get(i).getTempoResposta(),
					Matchers.equalTo(TEMPO_RESPOSTA_PREVISTA_POR_BURST[i]));
			Assert.assertThat(resultado.get(i).getTurnAround(),
					Matchers.equalTo(TURN_AROUND_PREVISTA_POR_BURST[i]));
		}
	}

	@Test
	public void deveEscalonarComUmAMilProcesso() {
		for (int i = 1; i <= 1000; i++) {
			Escalonador sjf = new SJF(gerarListaDeProcessos(i, VALIDO));
			sjf.executar();
			List<Processo> resultado = sjf.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
			Assert.assertThat(resultado.size(), Matchers.is(i));
		}
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveEscalonarSemAntesCarregarOsProcessos() {
		new SJF(null);
	}

	@Test(expected = NegativoBurstException.class)
	public void naoDeveEscalonarProcessosComBurstNegativo() {
		new SJF(gerarListaDeProcessos(3, INVALIDO));
	}

}