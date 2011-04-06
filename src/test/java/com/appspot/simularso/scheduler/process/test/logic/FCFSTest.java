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
import com.appspot.simularso.scheduler.process.logic.EscalonadorProcesso;
import com.appspot.simularso.scheduler.process.logic.impl.FCFS;
import com.appspot.simularso.scheduler.process.test.InitialTestCase;

/**
 * Testes para Escalonamento FCFS.
 * 
 * @author Caio Ribeiro Pereira
 * @since 12/02/2011
 */
public class FCFSTest extends InitialTestCase {

	@Test
	public void deveRetornarResultadoOrdenadoSimples() {
		final Integer[] BURSTS_SIMPLES = { 30, 10, 20, 50, 90 };
		final Integer[] ID_SIMPLES = { 1, 2, 3, 4, 5 };
		final Integer[] TEMPO_ESPERA_PREVISTA_POR_BURST_SIMPLES = { 0, 30, 40, 60, 110 };
		final Integer[] TEMPO_RESPOSTA_PREVISTA_POR_BURST_SIMPLES = { 30, 40, 60, 110, 200 };
		final Integer[] TURN_AROUND_PREVISTA_POR_BURST_SIMPLES = { 30, 40, 60, 110, 200 };

		EscalonadorProcesso fcfs = new FCFS(gerarArrayListDeProcessos(BURSTS_SIMPLES.length, BURSTS_SIMPLES, null, null), 0);
		List<ProcessoVO> resultado = fcfs.resultadoFinal();

		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(BURSTS_SIMPLES.length));

		Iterator<ProcessoVO> resultArrayList = resultado.iterator();
		while (resultArrayList.hasNext()) {
			ProcessoVO proc = resultArrayList.next();
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_BURST_SIMPLES, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_BURST_SIMPLES, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_BURST_SIMPLES, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		List<ProcessoDTO> resultadoGrafico = fcfs.resultadoGraficoFinal();
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertThat(resultadoGrafico.size(), Matchers.is(BURSTS_SIMPLES.length));

		Iterator<ProcessoDTO> resultArrayListGraphic = resultadoGrafico.iterator();
		while (resultArrayListGraphic.hasNext()) {
			ProcessoDTO proc = resultArrayListGraphic.next();
			Assert.assertThat(ID_SIMPLES, Matchers.hasItemInArray(proc.getId()));
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
		final Integer[] BURSTS_MEDIO = { 20, 11, 39, 56, 9, 1, 5, 10 };
		final Integer[] ID_MEDIO = { 1, 2, 3, 4, 5, 6, 7, 8 };
		final Integer[] TEMPO_ESPERA_PREVISTA_POR_BURST_MEDIO = { 0, 20, 31, 70, 126, 135, 136, 141 };
		final Integer[] TEMPO_RESPOSTA_PREVISTA_POR_BURST_MEDIO = { 20, 31, 70, 126, 135, 136, 141, 151 };
		final Integer[] TURN_AROUND_PREVISTA_POR_BURST_MEDIO = { 20, 31, 70, 126, 135, 136, 141, 151 };

		EscalonadorProcesso fcfs = new FCFS(gerarArrayListDeProcessos(BURSTS_MEDIO.length, BURSTS_MEDIO, null, null), 0);

		List<ProcessoVO> resultado = fcfs.resultadoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(BURSTS_MEDIO.length));

		Iterator<ProcessoVO> resultArrayList = resultado.iterator();
		while (resultArrayList.hasNext()) {
			ProcessoVO proc = resultArrayList.next();
			Assert.assertThat(TEMPO_ESPERA_PREVISTA_POR_BURST_MEDIO, Matchers.hasItemInArray(proc.getEspera()));
			Assert.assertThat(TEMPO_RESPOSTA_PREVISTA_POR_BURST_MEDIO, Matchers.hasItemInArray(proc.getResposta()));
			Assert.assertThat(TURN_AROUND_PREVISTA_POR_BURST_MEDIO, Matchers.hasItemInArray(proc.getTurnAround()));
		}

		List<ProcessoDTO> resultadoGrafico = fcfs.resultadoGraficoFinal();
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertThat(resultadoGrafico.size(), Matchers.is(BURSTS_MEDIO.length));

		Iterator<ProcessoDTO> resultArrayListGraphic = resultadoGrafico.iterator();
		while (resultArrayListGraphic.hasNext()) {
			ProcessoDTO proc = resultArrayListGraphic.next();
			Assert.assertThat(ID_MEDIO, Matchers.hasItemInArray(proc.getId()));
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
			EscalonadorProcesso fcfs = new FCFS(gerarListaDeProcessos(i, VALIDO), 0);
			List<ProcessoVO> resultado = fcfs.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
		}
	}

	@Test
	public void deveRetornarResultadoFinalOrdernadoPorProcessoId() {
		final int TOTAL = 10;
		EscalonadorProcesso fcfs = new FCFS(gerarListaDeProcessos(TOTAL, VALIDO), 0);
		List<ProcessoVO> resultado = fcfs.resultadoFinal();
		int id = 1;
		for (ProcessoVO processo : resultado) {
			Assert.assertTrue(id++ == processo.getId());
		}

	}

	@Test(expected = ProcessosConfiguracaoException.class)
	public void naoDeveEscalonarProcessosComBurstNegativo() {
		new FCFS(gerarListaDeProcessos(3, INVALIDO), 0);
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveGerarResultadoSemEscalonarOsProcessosAntes() {
		new FCFS(null, 0);
	}

}
