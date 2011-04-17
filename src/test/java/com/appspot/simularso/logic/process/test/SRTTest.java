package com.appspot.simularso.logic.process.test;

import java.util.Iterator;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.appspot.simularso.exception.ProcessosConfiguracaoException;
import com.appspot.simularso.exception.ProcessosNaoCarregadosException;
import com.appspot.simularso.logic.EscalonadorProcesso;
import com.appspot.simularso.logic.process.SRT;
import com.appspot.simularso.logic.test.InitialTestCase;
import com.appspot.simularso.model.ProcessoDTO;
import com.appspot.simularso.model.ProcessoVO;

public class SRTTest extends InitialTestCase {

	@Test
	public void deveRealizarUmEscalonamentoSimples() {
		final Integer[] BURSTS_SIMPLES = { 7, 4, 1, 4 };
		final Integer[] CHEGADAS_SIMPLES = { 0, 2, 4, 5 };
		final Integer[] ID_COM_BURSTS_SIMPLES = { 1, 2, 3, 2, 4, 1 };
		final Integer[] TEMPO_ESPERA_COM_BURSTS_SIMPLES = { 9, 3, 4, 7 };
		final Integer[] TEMPO_RESPOSTA_COM_BURSTS_SIMPLES = { 0, 2, 4, 7 };
		final Integer[] TURN_AROUND_COM_BURSTS_SIMPLES = { 16, 7, 5, 11 };

		EscalonadorProcesso srt = new SRT(gerarArrayListDeProcessos(BURSTS_SIMPLES.length, BURSTS_SIMPLES, CHEGADAS_SIMPLES, null), 0);
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

		EscalonadorProcesso srt = new SRT(gerarArrayListDeProcessos(BURSTS_MEDIO.length, BURSTS_MEDIO, CHEGADAS_MEDIO, null), 0);

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
	public void deveEscalonarComDoisACemProcesso() {
		for (int i = 2; i <= 100; i++) {
			EscalonadorProcesso srt = new SRT(gerarListaDeProcessos(i, VALIDO), 0);
			List<ProcessoVO> resultado = srt.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
		}
	}

	@Test
	public void deveRetornarResultadoFinalOrdernadoPorProcessoId() {
		final int TOTAL = 10;
		EscalonadorProcesso srt = new SRT(gerarListaDeProcessos(TOTAL, VALIDO), 0);
		List<ProcessoVO> resultado = srt.resultadoFinal();
		int id = 1;
		for (ProcessoVO processo : resultado) {
			Assert.assertTrue(id++ == processo.getId());
		}
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveEscalonarSemAntesCarregarOsProcessos() {
		new SRT(null, 0);
	}

	@Test(expected = ProcessosConfiguracaoException.class)
	public void naoDeveEscalonarProcessosComBurstNegativo() {
		new SRT(gerarListaDeProcessos(3, INVALIDO), 0);
	}
}
