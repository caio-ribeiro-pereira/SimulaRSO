package com.appspot.simularso.logic.process.test;

import java.util.Iterator;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.appspot.simularso.logic.EscalonadorProcesso;
import com.appspot.simularso.logic.process.RR;
import com.appspot.simularso.logic.test.InitialTestCase;
import com.appspot.simularso.model.ProcessoDTO;
import com.appspot.simularso.model.ProcessoVO;

public class RRTest extends InitialTestCase {

	@Test
	public void deveRetornarResultadoSimples() {
		final Integer[] BURSTS_SIMPLES = { 60, 20, 40, 30 };
		final Integer[] ID_COM_BURSTS_SIMPLES = { 1, 2, 3, 4, 1, 3, 4, 1 };
		final Integer[] TEMPO_RESPOSTA_COM_BURSTS_SIMPLES = { 0, 20, 40, 60 };
		final Integer[] TEMPO_ESPERA_COM_BURSTS_SIMPLES = { 90, 20, 80, 100 };
		final Integer[] TURN_AROUND_COM_BURSTS_SIMPLES = { 150, 40, 120, 130 };
		final int QUANTUM = 20;

		EscalonadorProcesso roundRobin = new RR(gerarArrayListDeProcessos(BURSTS_SIMPLES.length, BURSTS_SIMPLES, null, null), QUANTUM, 0);

		List<ProcessoDTO> resultadoGrafico = roundRobin.resultadoGraficoFinal();
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertTrue(resultadoGrafico.size() == ID_COM_BURSTS_SIMPLES.length);
		for (int i = 0; i < resultadoGrafico.size(); i++) {
			Assert.assertThat(resultadoGrafico.get(i).getId(), Matchers.equalTo(ID_COM_BURSTS_SIMPLES[i]));
		}

		List<ProcessoVO> resultado = roundRobin.resultadoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertTrue(resultado.size() == BURSTS_SIMPLES.length);
		Iterator<ProcessoVO> resultArrayList = resultado.iterator();
		while (resultArrayList.hasNext()) {
			ProcessoVO proc = resultArrayList.next();
			Assert.assertThat(ID_COM_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getId()));
			Assert.assertThat(TEMPO_ESPERA_COM_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_COM_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_COM_BURSTS_SIMPLES, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		double esperaMedia = roundRobin.tempoEsperaMedia();
		double respostaMedia = roundRobin.tempoRespostaMedia();
		double turnAroundMedio = roundRobin.tempoTurnAroundMedio();
		Assert.assertTrue(turnAroundMedio > 0.0);
		Assert.assertTrue(respostaMedia > 0.0);
		Assert.assertTrue(esperaMedia > 0.0);
		Assert.assertTrue(roundRobin.totalProcessos() == BURSTS_SIMPLES.length);
	}

	@Test
	public void deveRetornarResultadoMedio() {
		final Integer[] BURSTS_MEDIO = { 53, 17, 68, 24 };
		final Integer[] ID_COM_BURSTS_MEDIO = { 1, 2, 3, 4, 1, 3, 4, 1, 3, 3 };
		final Integer[] TEMPO_RESPOSTA_COM_BURSTS_MEDIO = { 0, 20, 37, 57 };
		final Integer[] TEMPO_ESPERA_COM_BURSTS_MEDIO = { 81, 20, 94, 97 };
		final Integer[] TURN_AROUND_COM_BURSTS_MEDIO = { 134, 37, 162, 121 };
		final int QUANTUM = 20;

		EscalonadorProcesso roundRobin = new RR(gerarArrayListDeProcessos(BURSTS_MEDIO.length, BURSTS_MEDIO, null, null), QUANTUM, 0);

		List<ProcessoDTO> resultadoGrafico = roundRobin.resultadoGraficoFinal();
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertThat(resultadoGrafico.size(), Matchers.is(ID_COM_BURSTS_MEDIO.length));
		for (int i = 0; i < resultadoGrafico.size(); i++) {
			Assert.assertThat(resultadoGrafico.get(i).getId(), Matchers.equalTo(ID_COM_BURSTS_MEDIO[i]));
		}

		List<ProcessoVO> resultado = roundRobin.resultadoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(BURSTS_MEDIO.length));
		Iterator<ProcessoVO> resultArrayList = resultado.iterator();
		while (resultArrayList.hasNext()) {
			ProcessoVO proc = resultArrayList.next();
			Assert.assertThat(ID_COM_BURSTS_MEDIO, Matchers.hasItemInArray(proc.getId()));
			Assert.assertThat(TEMPO_ESPERA_COM_BURSTS_MEDIO, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_COM_BURSTS_MEDIO, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_COM_BURSTS_MEDIO, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		double esperaMedia = roundRobin.tempoEsperaMedia();
		double respostaMedia = roundRobin.tempoRespostaMedia();
		double turnAroundMedio = roundRobin.tempoTurnAroundMedio();
		Assert.assertTrue(turnAroundMedio > 0.0);
		Assert.assertTrue(respostaMedia > 0.0);
		Assert.assertTrue(esperaMedia > 0.0);
		Assert.assertTrue(roundRobin.totalProcessos() == BURSTS_MEDIO.length);
	}

	@Test
	public void deveEscalonarComDoisACemProcessos() {
		final int QUANTUM_VALIDO = 20;
		for (int i = 2; i <= 100; i++) {
			EscalonadorProcesso roundRobin = new RR(gerarArrayListDefault(i), QUANTUM_VALIDO, 0);
			List<ProcessoVO> resultado = roundRobin.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
		}
	}

	@Test
	public void deveRetornarResultadoFinalOrdernadoPorProcessoId() {
		final int TOTAL = 10;
		final int QUANTUM_VALIDO = 50;
		EscalonadorProcesso roundRobin = new RR(gerarArrayListDefault(TOTAL), QUANTUM_VALIDO, 0);
		List<ProcessoVO> resultado = roundRobin.resultadoFinal();
		int id = 1;
		for (ProcessoVO processo : resultado) {
			Assert.assertTrue(id++ == processo.getId());
		}
	}

}