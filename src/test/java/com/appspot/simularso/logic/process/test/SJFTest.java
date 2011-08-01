package com.appspot.simularso.logic.process.test;

import java.util.Iterator;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.appspot.simularso.logic.EscalonadorProcesso;
import com.appspot.simularso.logic.process.SJF;
import com.appspot.simularso.logic.test.InitialTestCase;
import com.appspot.simularso.model.ProcessoDTO;
import com.appspot.simularso.model.ProcessoVO;

public class SJFTest extends InitialTestCase {

	@Test
	public void deveRetornarResultadoOrdenadoPorChegada() {
		final Integer[] TEMPOS_CHEGADA = { 10, 20, 5, 3, 1 };
		final Integer[] ID_PREVISTO_POR_CHEGADA = { 5, 4, 3, 1, 2 };
		final Integer[] TEMPO_ESPERA_PREVISTA_POR_CHEGADA = { 0, 100, 200, 300, 400 };
		final Integer[] TEMPO_RESPOSTA_PREVISTA_POR_CHEGADA = { 100, 200, 300, 400, 500 };
		final Integer[] TURN_AROUND_PREVISTA_POR_CHEGADA = { 100, 200, 300, 400, 500 };

		EscalonadorProcesso sjf = new SJF(gerarArrayListDeProcessos(TEMPOS_CHEGADA.length, null, TEMPOS_CHEGADA, null), 0, 0);

		List<ProcessoVO> resultado = sjf.resultadoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(TEMPOS_CHEGADA.length));

		Iterator<ProcessoVO> resultArrayList = resultado.iterator();
		while (resultArrayList.hasNext()) {
			ProcessoVO proc = resultArrayList.next();
			Assert.assertThat(ID_PREVISTO_POR_CHEGADA, Matchers.hasItemInArray(proc.getId()));
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_CHEGADA, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_CHEGADA, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_CHEGADA, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		List<ProcessoDTO> resultadoGrafico = sjf.resultadoGraficoFinal();
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertThat(resultadoGrafico.size(), Matchers.is(TEMPOS_CHEGADA.length));

		Iterator<ProcessoDTO> resultArrayListGraphic = resultadoGrafico.iterator();
		while (resultArrayListGraphic.hasNext()) {
			ProcessoDTO proc = resultArrayListGraphic.next();
			Assert.assertThat(ID_PREVISTO_POR_CHEGADA, Matchers.hasItemInArray(proc.getId()));
		}

		double esperaMedia = sjf.tempoEsperaMedia();
		double respostaMedia = sjf.tempoRespostaMedia();
		double turnAroundMedio = sjf.tempoTurnAroundMedio();
		Assert.assertTrue(turnAroundMedio > 0.0);
		Assert.assertTrue(respostaMedia > 0.0);
		Assert.assertTrue(esperaMedia > 0.0);
		Assert.assertTrue(sjf.totalProcessos() == TEMPOS_CHEGADA.length);
	}

	@Test
	public void deveRetornarResultadoOrdenadoPorBursts() {
		final Integer[] BURSTS = { 100, 20, 50, 10, 40 };
		final Integer[] ID_PREVISTO_POR_BURST = { 4, 2, 5, 3, 1 };
		final Integer[] TEMPO_ESPERA_PREVISTA_POR_BURST = { 0, 10, 30, 70, 120 };
		final Integer[] TEMPO_RESPOSTA_PREVISTA_POR_BURST = { 10, 30, 70, 120, 220 };
		final Integer[] TURN_AROUND_PREVISTA_POR_BURST = { 10, 30, 70, 120, 220 };

		EscalonadorProcesso sjf = new SJF(gerarArrayListDeProcessos(BURSTS.length, BURSTS, null, null), 0, 0);

		List<ProcessoVO> resultado = sjf.resultadoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(BURSTS.length));

		Iterator<ProcessoVO> resultArrayList = resultado.iterator();
		while (resultArrayList.hasNext()) {
			ProcessoVO proc = resultArrayList.next();
			Assert.assertThat(ID_PREVISTO_POR_BURST, Matchers.hasItemInArray(proc.getId()));
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_BURST, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_BURST, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_BURST, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		List<ProcessoDTO> resultadoGrafico = sjf.resultadoGraficoFinal();
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertThat(resultadoGrafico.size(), Matchers.is(BURSTS.length));

		Iterator<ProcessoDTO> resultArrayListGraphic = resultadoGrafico.iterator();
		while (resultArrayListGraphic.hasNext()) {
			ProcessoDTO proc = resultArrayListGraphic.next();
			Assert.assertThat(ID_PREVISTO_POR_BURST, Matchers.hasItemInArray(proc.getId()));
		}

		double esperaMedia = sjf.tempoEsperaMedia();
		double respostaMedia = sjf.tempoRespostaMedia();
		double turnAroundMedio = sjf.tempoTurnAroundMedio();
		Assert.assertTrue(turnAroundMedio > 0.0);
		Assert.assertTrue(respostaMedia > 0.0);
		Assert.assertTrue(esperaMedia > 0.0);
		Assert.assertTrue(sjf.totalProcessos() == BURSTS.length);

	}

	@Test
	public void deveEscalonarComDoisACemProcessos() {
		for (int i = 2; i <= 100; i++) {
			EscalonadorProcesso sjf = new SJF(gerarArrayListDefault(i), 0, 0);
			List<ProcessoVO> resultado = sjf.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
		}
	}

	@Test
	public void deveRetornarResultadoFinalOrdernadoPorProcessoId() {
		final int TOTAL = 10;
		EscalonadorProcesso sjf = new SJF(gerarArrayListDefault(TOTAL), 0, 0);
		List<ProcessoVO> resultado = sjf.resultadoFinal();
		int id = 1;
		for (ProcessoVO processo : resultado) {
			Assert.assertTrue(id++ == processo.getId());
		}
	}

}