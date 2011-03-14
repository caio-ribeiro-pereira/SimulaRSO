package appspot.simulaedp.test.logic;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import appspot.simulaedp.exception.ProcessosNaoCarregadosException;
import appspot.simulaedp.logic.Escalonador;
import appspot.simulaedp.logic.impl.SRT;
import appspot.simulaedp.model.Processo;
import appspot.simulaedp.test.InitialCase;

public class SRTTest extends InitialCase {

	private static final int[] BURSTS_SIMPLES = { 7, 4, 1, 4 };
	private static final int[] CHEGADAS_SIMPLES = { 0, 2, 4, 5 };
	private static final int[] ID_COM_BURSTS_SIMPLES = { 1, 2, 3, 2, 4, 1 };
	private static final int[] TEMPO_ESPERA_COM_BURSTS_SIMPLES = { 9, 3, 4, 7 };
	private static final int[] TEMPO_RESPOSTA_COM_BURSTS_SIMPLES = { 0, 2, 4, 7 };
	private static final int[] TURN_AROUND_COM_BURSTS_SIMPLES = { 16, 7, 5, 11 };

	@Test
	public void deveRealizarUmEscalonamentoSimples() {
		Escalonador srt = new SRT(gerarArrayListDeProcessos(BURSTS_SIMPLES.length, BURSTS_SIMPLES, CHEGADAS_SIMPLES, null));
		srt.executar();
		ArrayList<Processo> resultado = srt.resultadoFinal();
		ArrayList<Processo> resultadoGrafico = srt.resultadoGraficoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.equalTo(BURSTS_SIMPLES.length));
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertThat(resultadoGrafico.size(), Matchers.equalTo(ID_COM_BURSTS_SIMPLES.length));
		for (int i = 0; i < resultadoGrafico.size(); i++) {
			Assert.assertThat(resultadoGrafico.get(i).getId(), Matchers.equalTo(ID_COM_BURSTS_SIMPLES[i]));
		}
		for (int i = 0; i < resultado.size(); i++) {
			Assert.assertThat(resultado.get(i).getEspera(), Matchers.equalTo(TEMPO_ESPERA_COM_BURSTS_SIMPLES[i]));
			Assert.assertThat(resultado.get(i).getResposta(), Matchers.equalTo(TEMPO_RESPOSTA_COM_BURSTS_SIMPLES[i]));
			Assert.assertThat(resultado.get(i).getTurnAround(), Matchers.equalTo(TURN_AROUND_COM_BURSTS_SIMPLES[i]));
		}
		double esperaMedia = srt.tempoEsperaMedia();
		double respostaMedia = srt.tempoRespostaMedia();
		double turnAroundMedio = srt.tempoTurnAroundMedio();
		checarResultadoMedio(turnAroundMedio);
		checarResultadoMedio(respostaMedia);
		checarResultadoMedio(esperaMedia);
		Assert.assertTrue(srt.totalProcessos() == BURSTS_SIMPLES.length);
	}

	private static final int[] BURSTS_MEDIO = { 100, 30, 70, 95, 85 };
	private static final int[] CHEGADAS_MEDIO = { 0, 100, 120, 140, 140 };
	private static final int[] ID_COM_BURSTS_MEDIO = { 1, 2, 3, 5, 4 };
	private static final int[] TEMPO_ESPERA_COM_BURSTS_MEDIO = { 0, 100, 130, 285, 200 };
	private static final int[] TEMPO_RESPOSTA_COM_BURSTS_MEDIO = { 0, 100, 130, 285, 200 };
	private static final int[] TURN_AROUND_COM_BURSTS_MEDIO = { 100, 130, 200, 380, 285 };

	@Test
	public void deveRealizarUmEscalonamentoMedio() {
		Escalonador srt = new SRT(gerarArrayListDeProcessos(BURSTS_MEDIO.length, BURSTS_MEDIO, CHEGADAS_MEDIO, null));
		srt.executar();
		ArrayList<Processo> resultado = srt.resultadoFinal();
		ArrayList<Processo> resultadoGrafico = srt.resultadoGraficoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.equalTo(BURSTS_MEDIO.length));
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertThat(resultadoGrafico.size(), Matchers.equalTo(ID_COM_BURSTS_MEDIO.length));
		for (int i = 0; i < resultadoGrafico.size(); i++) {
			Assert.assertThat(resultadoGrafico.get(i).getId(), Matchers.equalTo(ID_COM_BURSTS_MEDIO[i]));
		}
		for (int i = 0; i < resultado.size(); i++) {
			Assert.assertThat(resultado.get(i).getEspera(), Matchers.equalTo(TEMPO_ESPERA_COM_BURSTS_MEDIO[i]));
			Assert.assertThat(resultado.get(i).getResposta(), Matchers.equalTo(TEMPO_RESPOSTA_COM_BURSTS_MEDIO[i]));
			Assert.assertThat(resultado.get(i).getTurnAround(), Matchers.equalTo(TURN_AROUND_COM_BURSTS_MEDIO[i]));
		}
		double esperaMedia = srt.tempoEsperaMedia();
		double respostaMedia = srt.tempoRespostaMedia();
		double turnAroundMedio = srt.tempoTurnAroundMedio();
		checarResultadoMedio(turnAroundMedio);
		checarResultadoMedio(respostaMedia);
		checarResultadoMedio(esperaMedia);
		Assert.assertTrue(srt.totalProcessos() == BURSTS_MEDIO.length);
	}

	private void checarResultadoMedio(double val) {
		Assert.assertTrue(val > 0.0);
	}

	@Test
	public void deveEscalonarComDoisAMilProcesso() {
		for (int i = 2; i <= 1000; i++) {
			Escalonador srt = new SRT(gerarListaDeProcessos(i, VALIDO));
			srt.executar();
			ArrayList<Processo> resultado = srt.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
			Assert.assertThat(resultado.size(), Matchers.equalTo(i));
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
