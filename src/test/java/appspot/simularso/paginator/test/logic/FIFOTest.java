package appspot.simularso.paginator.test.logic;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import appspot.simularso.exception.FramesInvalidoException;
import appspot.simularso.exception.StringReferenciaInvalidaException;
import appspot.simularso.model.Pagina;
import appspot.simularso.paginator.logic.Paginador;
import appspot.simularso.paginator.logic.impl.FIFO;

public class FIFOTest {

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaSimples() {
		final Integer[] REFERENCIA_SIMPLES = { 0, 1, 2, 2, 0, 3, 4, 5, 6, 7 };
		final Integer FRAME_SIMPLES = 3;
		final int RESULTADO_PAGE_FAULT = 8;

		Paginador fifo = new FIFO(REFERENCIA_SIMPLES, FRAME_SIMPLES);

		List<Pagina> resultadoGrafico = fifo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = fifo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);
	}

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaSimplesComAnomaliaDeBellady() {
		final Integer[] REFERENCIA_SIMPLES = { 0, 1, 2, 2, 0, 3, 4, 5, 6, 7 };
		final Integer FRAME_SIMPLES = 4;
		final int RESULTADO_PAGE_FAULT = 8;

		Paginador fifo = new FIFO(REFERENCIA_SIMPLES, FRAME_SIMPLES);

		List<Pagina> resultadoGrafico = fifo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = fifo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);
	}

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaMedio() {
		final Integer[] REFERENCIA_SIMPLES = { 0, 1, 2, 2, 0, 3, 4, 5, 6, 7, 2, 3, 0, 1, 4, 5, 6, 5, 3 };
		final Integer FRAME_SIMPLES = 4;
		final int RESULTADO_PAGE_FAULT = 16;

		Paginador fifo = new FIFO(REFERENCIA_SIMPLES, FRAME_SIMPLES);

		List<Pagina> resultadoGrafico = fifo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = fifo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);
	}

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaMedioComAnomaliaDeBellady() {
		final Integer[] REFERENCIA_SIMPLES = { 0, 1, 2, 2, 0, 3, 4, 5, 6, 7, 2, 3, 0, 1, 4, 5, 6, 5, 3 };
		final Integer FRAME_SIMPLES = 5;
		final int RESULTADO_PAGE_FAULT = 15;

		Paginador fifo = new FIFO(REFERENCIA_SIMPLES, FRAME_SIMPLES);

		List<Pagina> resultadoGrafico = fifo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = fifo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);
	}

	@Test(expected = StringReferenciaInvalidaException.class)
	public void naoDeveRealizarSubstituicaoPaginaComStringReferenciaNula() {
		final Integer[] REFERENCIA_NULA = null;
		final Integer FRAME_VALIDO = 2;
		new FIFO(REFERENCIA_NULA, FRAME_VALIDO);
	}

	@Test(expected = FramesInvalidoException.class)
	public void naoDeveRealizarSubstituicaoPaginaComFrameNegativo() {
		final Integer[] REFERENCIA_VALIDO = { 1, 2, 3, 4, 5, 6, 7 };
		final Integer FRAME_NEGATIVO = -1;
		new FIFO(REFERENCIA_VALIDO, FRAME_NEGATIVO);
	}
}
