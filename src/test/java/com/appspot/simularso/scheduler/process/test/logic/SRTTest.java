package com.appspot.simularso.scheduler.process.test.logic;

import java.util.Iterator;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.appspot.simularso.exception.ProcessosConfiguracaoException;
import com.appspot.simularso.exception.ProcessosNaoCarregadosException;
import com.appspot.simularso.model.ProcessoDTO;
import com.appspot.simularso.model.ProcessoVO;
import com.appspot.simularso.scheduler.process.logic.Escalonador;
import com.appspot.simularso.scheduler.process.logic.impl.SRT;
import com.appspot.simularso.scheduler.process.test.InitialTestCase;

public class SRTTest extends InitialTestCase {

	@Test
	public void deveRealizarUmEscalonamentoSimples() {
		final Integer[] BURSTS_SIMPLES = { 7, 4, 1, 4 };
		final Integer[] CHEGADAS_SIMPLES = { 0, 2, 4, 5 };
		final Integer[] ID_COM_BURSTS_SIMPLES = { 1, 2, 3, 2, 4, 1 };
		final Integer[] TEMPO_ESPERA_COM_BURSTS_SIMPLES = { 9, 3, 4, 7 };
		final Integer[] TEMPO_RESPOSTA_COM_BURSTS_SIMPLES = { 0, 2, 4, 7 };
		final Integer[] TURN_AROUND_COM_BURSTS_SIMPLES = { 16, 7, 5, 11 };

		Escalonador srt = new SRT(gerarArrayListDeProcessos(BURSTS_SIMPLES.length, BURSTS_SIMPLES, CHEGADAS_SIMPLES, null));
		List<ProcessoVO> resultado = srt.resultadoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertTrue(resultado.size() == BURSTS_SIMPLES.length);

		Iterator<ProcessoVO> resultArrayListGraphic = resultado.iterator();
		while (resultArrayListGraphic.hasNext()) {
			ProcessoVO proc = resultArrayListGraphic.next();
			Assert.assertThat(TEMPO_ESPERA_COM_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_COM_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_COM_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		List<ProcessoDTO> resultadoGrafico = srt.resultadoGraficoFinal();
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

		List<ProcessoVO> resultado = srt.resultadoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertTrue(resultado.size() == BURSTS_MEDIO.length);

		Iterator<ProcessoVO> resultArrayListGraphic = resultado.iterator();
		while (resultArrayListGraphic.hasNext()) {
			ProcessoVO proc = resultArrayListGraphic.next();
			Assert.assertThat(TEMPO_ESPERA_COM_BURSTS_MEDIO, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_COM_BURSTS_MEDIO, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_COM_BURSTS_MEDIO, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		List<ProcessoDTO> resultadoGrafico = srt.resultadoGraficoFinal();
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
	public void deveRealizarUmEscalonamentoComplexo() {
		final Integer[] BURSTS_COMPLEXO = { 77, 20, 37, 10, 64, 3, 8, 50, 44, 17, 42, 36, 76, 30, 9, 86, 75, 92, 36, 69 };
		final Integer[] CHEGADAS_COMPLEXO = { 74, 83, 40, 33, 66, 65, 85, 88, 33, 49, 72, 26, 60, 70, 96, 8, 72, 3, 41, 59 };
		final Integer[] PRIORIDADE_COMPLEXO = { 10, 5, 9, 0, 10, 6, 0, 6, 2, 5, 1, 7, 9, 10, 7, 5, 6, 5, 4, 2 };
		final Integer[] ID_PROCESSOS_COMPLEXO = { 4, 7, 11, 9, 20, 19, 18, 16, 10, 2, 2, 6, 17, 8, 12, 15, 3, 13, 5, 14, 16, 20, 17, 5, 13,
				1, 18 };
		final Integer[] TEMPO_ESPERA_COM_BURSTS_COMPLEXO = { 0, 10, 18, 60, 459, 122, 789, 391, 204, 221, 241, 512, 260, 310, 346, 355,
				641, 583, 402, 717 };
		final Integer[] TEMPO_RESPOSTA_COM_BURSTS_COMPLEXO = { 0, 10, 18, 60, 104, 122, 158, 163, 204, 221, 241, 244, 260, 310, 346, 355,
				392, 398, 402, 717 };
		final Integer[] TURN_AROUND_COM_BURSTS_COMPLEXO = { 10, 18, 60, 104, 528, 158, 881, 477, 221, 241, 244, 587, 310, 346, 355, 392,
				717, 647, 432, 794 };

		Escalonador srt = new SRT(
				gerarArrayListDeProcessos(BURSTS_COMPLEXO.length, BURSTS_COMPLEXO, CHEGADAS_COMPLEXO, PRIORIDADE_COMPLEXO));

		List<ProcessoVO> resultado = srt.resultadoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertTrue(resultado.size() == BURSTS_COMPLEXO.length);

		Iterator<ProcessoVO> resultArrayList = resultado.iterator();
		while (resultArrayList.hasNext()) {
			ProcessoVO proc = resultArrayList.next();
			Assert.assertThat(TEMPO_ESPERA_COM_BURSTS_COMPLEXO, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_COM_BURSTS_COMPLEXO, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_COM_BURSTS_COMPLEXO, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		List<ProcessoDTO> resultadoGrafico = srt.resultadoGraficoFinal();
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertTrue(resultadoGrafico.size() == ID_PROCESSOS_COMPLEXO.length);
		for (int i = 0; i < resultadoGrafico.size(); i++) {
			Assert.assertThat(resultadoGrafico.get(i).getId(), Matchers.equalTo(ID_PROCESSOS_COMPLEXO[i]));
		}

		double esperaMedia = srt.tempoEsperaMedia();
		double respostaMedia = srt.tempoRespostaMedia();
		double turnAroundMedio = srt.tempoTurnAroundMedio();
		Assert.assertTrue(turnAroundMedio > 0.0);
		Assert.assertTrue(respostaMedia > 0.0);
		Assert.assertTrue(esperaMedia > 0.0);
		Assert.assertTrue(srt.totalProcessos() == BURSTS_COMPLEXO.length);
	}

	@Test
	public void deveEscalonarComDoisACemProcesso() {
		for (int i = 2; i <= 100; i++) {
			Escalonador srt = new SRT(gerarListaDeProcessos(i, VALIDO));
			List<ProcessoVO> resultado = srt.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
		}
	}

	@Test
	public void deveRetornarResultadoFinalOrdernadoPorProcessoId() {
		final int TOTAL = 10;
		Escalonador srt = new SRT(gerarListaDeProcessos(TOTAL, VALIDO));
		List<ProcessoVO> resultado = srt.resultadoFinal();
		int id = 1;
		for (ProcessoVO processo : resultado) {
			Assert.assertTrue(id++ == processo.getId());
		}
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveEscalonarSemAntesCarregarOsProcessos() {
		new SRT(null);
	}

	@Test(expected = ProcessosConfiguracaoException.class)
	public void naoDeveEscalonarProcessosComBurstNegativo() {
		new SRT(gerarListaDeProcessos(3, INVALIDO));
	}
}
