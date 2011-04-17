package com.appspot.simularso.logic.memory.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.appspot.simularso.exception.FramesInvalidoException;
import com.appspot.simularso.exception.StringReferenciaInvalidaException;
import com.appspot.simularso.logic.PaginacaoMemoria;
import com.appspot.simularso.logic.memory.LRU;
import com.appspot.simularso.model.Pagina;

public class LRUTest {

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaSimples() {
		final Integer[] STRING_REFERENCIA = { 4, 3, 0, 2, 1, 4, 5, 6, 1, 3, 2 };
		final List<Integer> REFERENCIA = Arrays.asList(STRING_REFERENCIA);
		final Integer FRAME_SIMPLES = 3;
		final int RESULTADO_PAGE_FAULT = 11;

		PaginacaoMemoria lru = new LRU(REFERENCIA, FRAME_SIMPLES);

		List<Pagina> resultadoGrafico = lru.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = lru.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);

		List<Integer> stringRef = lru.stringReferencia();
		Assert.assertEquals(REFERENCIA, stringRef);
		Assert.assertTrue(STRING_REFERENCIA.length == resultadoGrafico.size());
	}

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaMedio() {
		final Integer[] STRING_REFERENCIA = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };
		final List<Integer> REFERENCIA = Arrays.asList(STRING_REFERENCIA);
		final Integer FRAME_SIMPLES = 3;
		final int RESULTADO_PAGE_FAULT = 12;

		PaginacaoMemoria lru = new LRU(REFERENCIA, FRAME_SIMPLES);

		List<Pagina> resultadoGrafico = lru.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = lru.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);

		List<Integer> stringRef = lru.stringReferencia();
		Assert.assertEquals(REFERENCIA, stringRef);
		Assert.assertTrue(STRING_REFERENCIA.length == resultadoGrafico.size());
	}

	@Test(expected = StringReferenciaInvalidaException.class)
	public void naoDeveRealizarSubstituicaoPaginaComStringReferenciaNula() {
		final List<Integer> REFERENCIA_NULA = null;
		final Integer FRAME_VALIDO = 2;
		new LRU(REFERENCIA_NULA, FRAME_VALIDO);
	}

	@Test(expected = FramesInvalidoException.class)
	public void naoDeveRealizarSubstituicaoPaginaComFrameNegativo() {
		final Integer[] REFERENCIA_VALIDA = { 1, 2, 3, 4, 5, 6, 7 };
		final List<Integer> REFERENCIA = Arrays.asList(REFERENCIA_VALIDA);
		final Integer FRAME_NEGATIVO = -1;
		new LRU(REFERENCIA, FRAME_NEGATIVO);
	}
}
