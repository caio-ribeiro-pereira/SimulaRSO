package br.com.simulaedp.logic;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import br.com.simulaedp.exception.NegativoBurstException;
import br.com.simulaedp.exception.ProcessosNaoCarregadosException;
import br.com.simulaedp.logic.impl.SRT;
import br.com.simulaedp.model.Processo;
import br.com.simulaedp.test.InitialCase;

public class SRTTest extends InitialCase {
	
	private static final int[] BURSTS_SIMPLES = { 7, 4, 1, 4 };
	private static final int[] CHEGADAS_SIMPLES = {0,2,4,5};
	private static final int[] ID_COM_BURSTS_SIMPLES = { 1, 2, 3, 2, 4, 1};
	private static final int[] TEMPO_ESPERA_COM_BURSTS_SIMPLES = { 9, 3 , 4, 7};
	private static final int[] TEMPO_RESPOSTA_COM_BURSTS_SIMPLES = { 0, 2, 4, 7};
	private static final int[] TURN_AROUND_COM_BURSTS_SIMPLES = {16,7,5,11};
	
	@Test
	public void deveRealizarUmEscalonamentoSimples(){
		Escalonador srt = new SRT(gerarArrayListDeProcessos(BURSTS_SIMPLES.length, BURSTS_SIMPLES, CHEGADAS_SIMPLES, null));
		srt.executar();
		ArrayList<Processo> resultado = srt.resultadoFinal();
		ArrayList<Processo> resultadoGrafico = srt.resultadoGraficoFinal();
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(BURSTS_SIMPLES.length));
		Assert.assertThat(resultadoGrafico, Matchers.notNullValue());
		Assert.assertThat(resultadoGrafico.size(), Matchers.is(ID_COM_BURSTS_SIMPLES.length));
		for(int i = 0 ; i < resultadoGrafico.size(); i++){
			Assert.assertThat(resultadoGrafico.get(i).getId(), Matchers.is(ID_COM_BURSTS_SIMPLES[i]));
		}
		for(int i = 0 ; i < resultado.size(); i++){
			Assert.assertThat(resultado.get(i).getTempoEspera(), Matchers.is(TEMPO_ESPERA_COM_BURSTS_SIMPLES[i]));
			Assert.assertThat(resultado.get(i).getTempoResposta(), Matchers.is(TEMPO_RESPOSTA_COM_BURSTS_SIMPLES[i]));
			Assert.assertThat(resultado.get(i).getTurnAround(), Matchers.is(TURN_AROUND_COM_BURSTS_SIMPLES[i]));
		}
	}
	
	@Test
	public void deveEscalonarComDoisAMilProcesso() {
		for (int i = 2; i <= 1000; i++) {
			Escalonador srt = new SRT(gerarListaDeProcessos(i, VALIDO));
			srt.executar();
			ArrayList<Processo> resultado = srt.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
			Assert.assertThat(resultado.size(), Matchers.is(i));
		}
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveEscalonarSemAntesCarregarOsProcessos() {
		new SRT(null);
	}

	@Test(expected = NegativoBurstException.class)
	public void naoDeveEscalonarProcessosComBurstNegativo() {
		new SRT(gerarListaDeProcessos(3, INVALIDO));
	}
}
