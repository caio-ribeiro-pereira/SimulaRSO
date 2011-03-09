package appspot.simulaedp.test.logic;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import appspot.simulaedp.exception.ProcessosNaoCarregadosException;
import appspot.simulaedp.logic.Escalonador;
import appspot.simulaedp.logic.impl.FCFS;
import appspot.simulaedp.model.Processo;
import appspot.simulaedp.test.InitialCase;

/**
 * Testes para Escalonamento FCFS.
 * 
 * @author Caio Ribeiro Pereira
 * @since 12/02/2011
 */
public class FCFSTest extends InitialCase {

	private static final int[] BURSTS = { 30, 10, 20, 50, 90 };
	private static final int[] TEMPO_ESPERA_PREVISTA_POR_BURST = { 0, 30, 40, 60, 110 };
	private static final int[] TEMPO_RESPOSTA_PREVISTA_POR_BURST = { 30, 40, 60, 110, 200 };
	private static final int[] TURN_AROUND_PREVISTA_POR_BURST = { 30, 40, 60, 110, 200 };

	@Test
	public void deveRetornarResultadoOrdenado() {
		Escalonador fcfs = new FCFS(gerarArrayListDeProcessos(BURSTS.length, BURSTS, null, null));
		fcfs.executar();
		ArrayList<Processo> resultado = fcfs.resultadoFinal();
		ArrayList<Processo> resultadoGrafico = fcfs.resultadoGraficoFinal();
		double esperaMedia = fcfs.tempoEsperaMedia();
		double respostaMedia = fcfs.tempoRespostaMedia();
		double turnAroundMedio = fcfs.tempoTurnAroundMedio();
		checarResultadoMedio(turnAroundMedio);
		checarResultadoMedio(respostaMedia);
		checarResultadoMedio(esperaMedia);
		checarResultado(resultado);
		checarResultado(resultadoGrafico);
		Assert.assertTrue(fcfs.totalProcessos() == BURSTS.length);
	}

	private void checarResultado(List<Processo> resultado) {
		Assert.assertThat(resultado, Matchers.notNullValue());
		Assert.assertThat(resultado.size(), Matchers.is(BURSTS.length));
		for (int i = 0; i < resultado.size(); i++) {
			Assert.assertThat(resultado.get(i).getEspera(), Matchers.equalTo(TEMPO_ESPERA_PREVISTA_POR_BURST[i]));
			Assert.assertThat(resultado.get(i).getResposta(), Matchers.equalTo(TEMPO_RESPOSTA_PREVISTA_POR_BURST[i]));
			Assert.assertThat(resultado.get(i).getTurnAround(), Matchers.equalTo(TURN_AROUND_PREVISTA_POR_BURST[i]));
		}
	}

	private void checarResultadoMedio(double val) {
		Assert.assertTrue(val > 0.0);
	}

	@Test
	public void deveEscalonarComUmAMilProcesso() {
		for (int i = 1; i <= 1000; i++) {
			Escalonador fcfs = new FCFS(gerarListaDeProcessos(i, VALIDO));
			fcfs.executar();
			ArrayList<Processo> resultado = fcfs.resultadoFinal();
			Assert.assertThat(resultado, Matchers.notNullValue());
			Assert.assertThat(resultado.size(), Matchers.is(i));
		}
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveEscalonarProcessosComBurstNegativo() {
		new FCFS(gerarListaDeProcessos(3, INVALIDO));
	}

	@Test(expected = ProcessosNaoCarregadosException.class)
	public void naoDeveGerarResultadoSemEscalonarOsProcessosAntes() {
		new FCFS(null);
	}
}
