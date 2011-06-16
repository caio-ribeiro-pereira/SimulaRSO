package com.appspot.simularso.logic.memory.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.appspot.simularso.logic.PaginacaoMemoria;
import com.appspot.simularso.logic.memory.FIFO;
import com.appspot.simularso.model.Pagina;

public class FIFOTest {

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaSimples() {
		final Integer[] STRING_REFERENCIA = { 0, 1, 2, 2, 0, 3, 4, 5, 6, 7 };
		final List<Integer> REFERENCIA = Arrays.asList(STRING_REFERENCIA);
		final Integer FRAME = 3;
		final int RESULTADO_PAGE_FAULT = 8;

		PaginacaoMemoria fifo = new FIFO(REFERENCIA, FRAME);

		List<Pagina> resultadoGrafico = fifo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = fifo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);

		List<Integer> stringRef = fifo.stringReferencia();
		Assert.assertEquals(REFERENCIA, stringRef);
		Assert.assertTrue(STRING_REFERENCIA.length == resultadoGrafico.size());
	}

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaSimplesComAnomaliaDeBellady() {
		final Integer[] STRING_REFERENCIA = { 0, 1, 2, 2, 0, 3, 4, 5, 6, 7 };
		final List<Integer> REFERENCIA = Arrays.asList(STRING_REFERENCIA);
		final Integer FRAME = 4;
		final int RESULTADO_PAGE_FAULT = 8;

		PaginacaoMemoria fifo = new FIFO(REFERENCIA, FRAME);

		List<Pagina> resultadoGrafico = fifo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = fifo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);

		List<Integer> stringRef = fifo.stringReferencia();
		Assert.assertEquals(REFERENCIA, stringRef);
		Assert.assertTrue(STRING_REFERENCIA.length == resultadoGrafico.size());
	}

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaMedio() {
		final Integer[] STRING_REFERENCIA = { 0, 1, 2, 2, 0, 3, 4, 5, 6, 7, 2, 3, 0, 1, 4, 5, 6, 5, 3 };
		final List<Integer> REFERENCIA = Arrays.asList(STRING_REFERENCIA);
		final Integer FRAME = 4;
		final int RESULTADO_PAGE_FAULT = 16;

		PaginacaoMemoria fifo = new FIFO(REFERENCIA, FRAME);

		List<Pagina> resultadoGrafico = fifo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = fifo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);

		List<Integer> stringRef = fifo.stringReferencia();
		Assert.assertEquals(REFERENCIA, stringRef);
		Assert.assertTrue(STRING_REFERENCIA.length == resultadoGrafico.size());
	}

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaMedioComAnomaliaDeBellady() {
		final Integer[] STRING_REFERENCIA = { 0, 1, 2, 2, 0, 3, 4, 5, 6, 7, 2, 3, 0, 1, 4, 5, 6, 5, 3 };
		final List<Integer> REFERENCIA = Arrays.asList(STRING_REFERENCIA);
		final Integer FRAME = 5;
		final int RESULTADO_PAGE_FAULT = 16;

		PaginacaoMemoria fifo = new FIFO(REFERENCIA, FRAME);

		List<Pagina> resultadoGrafico = fifo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = fifo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);

		List<Integer> stringRef = fifo.stringReferencia();
		Assert.assertEquals(REFERENCIA, stringRef);
		Assert.assertTrue(STRING_REFERENCIA.length == resultadoGrafico.size());
	}

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferencia() {
		final Integer[] STRING_REFERENCIA = { 0, 1, 2, 3, 0, 1, 4, 0, 1, 2, 3, 4 };
		final List<Integer> REFERENCIA = Arrays.asList(STRING_REFERENCIA);
		final Integer FRAME = 3;
		final int RESULTADO_PAGE_FAULT = 9;

		PaginacaoMemoria fifo = new FIFO(REFERENCIA, FRAME);

		List<Pagina> resultadoGrafico = fifo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = fifo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);

		List<Integer> stringRef = fifo.stringReferencia();
		Assert.assertEquals(REFERENCIA, stringRef);
		Assert.assertTrue(STRING_REFERENCIA.length == resultadoGrafico.size());
	}

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaComAnomaliaDeBellady() {
		final Integer[] STRING_REFERENCIA = { 0, 1, 2, 3, 0, 1, 4, 0, 1, 2, 3, 4 };
		final List<Integer> REFERENCIA = Arrays.asList(STRING_REFERENCIA);
		final Integer FRAME = 4;
		final int RESULTADO_PAGE_FAULT = 10;

		PaginacaoMemoria fifo = new FIFO(REFERENCIA, FRAME);

		List<Pagina> resultadoGrafico = fifo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = fifo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);

		List<Integer> stringRef = fifo.stringReferencia();
		Assert.assertEquals(REFERENCIA, stringRef);
		Assert.assertTrue(STRING_REFERENCIA.length == resultadoGrafico.size());
	}
}
