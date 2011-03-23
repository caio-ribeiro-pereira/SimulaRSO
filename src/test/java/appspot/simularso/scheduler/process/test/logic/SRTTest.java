package appspot.simularso.scheduler.process.test.logic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import appspot.simularso.exception.ProcessosNaoCarregadosException;
import appspot.simularso.model.Processo;
import appspot.simularso.scheduler.process.logic.Escalonador;
import appspot.simularso.scheduler.process.logic.impl.SRT;
import appspot.simularso.scheduler.process.test.InitialCase;

public class SRTTest extends InitialCase {

	@Test
	public void deveRealizarUmEscalonamentoSimples() {
		final Integer[] BURSTS_SIMPLES = { 7, 4, 1, 4 };
		final Integer[] CHEGADAS_SIMPLES = { 0, 2, 4, 5 };
		final Integer[] ID_COM_BURSTS_SIMPLES = { 1, 2, 3, 2, 4, 1 };
		final Integer[] TEMPO_ESPERA_COM_BURSTS_SIMPLES = { 9, 3, 4, 7 };
		final Integer[] TEMPO_RESPOSTA_COM_BURSTS_SIMPLES = { 0, 2, 4, 7 };
		final Integer[] TURN_AROUND_COM_BURSTS_SIMPLES = { 16, 7, 5, 11 };

		Escalonador srt = new SRT(gerarArrayListDeProcessos(BURSTS_SIMPLES.length, BURSTS_SIMPLES, CHEGADAS_SIMPLES, null));
		Set<Processo> resultado = srt.resultadoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertTrue(resultado.size() == BURSTS_SIMPLES.length);

		Iterator<Processo> resultSetGraphic = resultado.iterator();
		while (resultSetGraphic.hasNext()) {
			Processo proc = resultSetGraphic.next();
			Assert.assertThat(TEMPO_ESPERA_COM_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_COM_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_COM_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		LinkedList<Processo> resultadoGrafico = srt.resultadoGraficoFinal();
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertTrue(resultadoGrafico.size() == ID_COM_BURSTS_SIMPLES.length);

		for (int i = 0; i < resultadoGrafico.size(); i++) {
			Assert.assertThat(resultadoGrafico.get(i).getId(), Matchers.equalTo(ID_COM_BURSTS_SIMPLES[i]));
		}

		double esperaMedia = srt.tempoEsperaMedia();
		double respostaMedia = srt.tempoRespostaMedia();
		double turnAroundMedio = srt.tempoTurnAroundMedio();
		Assert.assertTrue(turnAroundMedio > 0.0);
		Assert.assertTrue(respostaMedia > 0.0);
		Assert.assertTrue(esperaMedia > 0.0);
		Assert.assertTrue(srt.totalProcessos() == BURSTS_SIMPLES.length);
	}

	@Test
	public void deveRealizarUmEscalonamentoMedio() {
		final Integer[] BURSTS_MEDIO = { 100, 30, 70, 95, 85 };
		final Integer[] CHEGADAS_MEDIO = { 0, 100, 120, 140, 140 };
		final Integer[] ID_COM_BURSTS_MEDIO = { 1, 2, 3, 5, 4 };
		final Integer[] TEMPO_ESPERA_COM_BURSTS_MEDIO = { 0, 100, 130, 285, 200 };
		final Integer[] TEMPO_RESPOSTA_COM_BURSTS_MEDIO = { 0, 100, 130, 285, 200 };
		final Integer[] TURN_AROUND_COM_BURSTS_MEDIO = { 100, 130, 200, 380, 285 };

		Escalonador srt = new SRT(gerarArrayListDeProcessos(BURSTS_MEDIO.length, BURSTS_MEDIO, CHEGADAS_MEDIO, null));

		Set<Processo> resultado = srt.resultadoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertTrue(resultado.size() == BURSTS_MEDIO.length);

		Iterator<Processo> resultSetGraphic = resultado.iterator();
		while (resultSetGraphic.hasNext()) {
			Processo proc = resultSetGraphic.next();
			Assert.assertThat(TEMPO_ESPERA_COM_BURSTS_MEDIO, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_COM_BURSTS_MEDIO, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_COM_BURSTS_MEDIO, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		LinkedList<Processo> resultadoGrafico = srt.resultadoGraficoFinal();
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertTrue(resultadoGrafico.size() == ID_COM_BURSTS_MEDIO.length);
		for (int i = 0; i < resultadoGrafico.size(); i++) {
			Assert.assertThat(resultadoGrafico.get(i).getId(), Matchers.equalTo(ID_COM_BURSTS_MEDIO[i]));
		}

		double esperaMedia = srt.tempoEsperaMedia();
		double respostaMedia = srt.tempoRespostaMedia();
		double turnAroundMedio = srt.tempoTurnAroundMedio();
		Assert.assertTrue(turnAroundMedio > 0.0);
		Assert.assertTrue(respostaMedia > 0.0);
		Assert.assertTrue(esperaMedia > 0.0);
		Assert.assertTrue(srt.totalProcessos() == BURSTS_MEDIO.length);
	}

	@Test
	public void deveEscalonarComDoisACemProcesso() {
		for (int i = 2; i <= 100; i++) {
			Escalonador srt = new SRT(gerarListaDeProcessos(i, VALIDO));
			Set<Processo> resultado = srt.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
		}
	}

	@Test
	public void deveRetornarResultadoFinalOrdernadoPorProcessoId() {
		final int TOTAL = 10;
		Escalonador srt = new SRT(gerarListaDeProcessos(TOTAL, VALIDO));
		Set<Processo> resultado = srt.resultadoFinal();
		Iterator<Processo> result = resultado.iterator();
		int id = 1;
		while (result.hasNext()) {
			Integer procId = result.next().getId();
			Assert.assertTrue(id == procId);
			id++;
		}
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveEscalonarSemAntesCarregarOsProcessos() {
		new SRT(null);
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveEscalonarProcessosComBurstNegativo() {
		new SRT(gerarListaDeProcessos(3, INVALIDO));
	}
}
