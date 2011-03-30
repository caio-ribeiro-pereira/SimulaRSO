package com.appspot.simularso.paginator.test.logic;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.appspot.simularso.model.Pagina;
import com.appspot.simularso.paginator.logic.Paginador;
import com.appspot.simularso.paginator.logic.impl.OPT;

public class OPTTest {

	// @Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaSimples() {
		final Integer[] STRING_REFERENCIA = { 0, 1, 2, 2, 0, 3, 4, 5, 6, 7 };
		final List<Integer> REFERENCIA = Arrays.asList(STRING_REFERENCIA);
		final Integer FRAME_SIMPLES = 3;
		final int RESULTADO_PAGE_FAULT = 8;

		Paginador otimo = new OPT(REFERENCIA, FRAME_SIMPLES);

		List<Pagina> resultadoGrafico = otimo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = otimo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);

		List<Integer> stringRef = otimo.stringReferencia();
		Assert.assertEquals(REFERENCIA, stringRef);
	}

	@Test
	public void deveRealizarSubstituicaoPaginaComStringReferenciaMedio() {
		final Integer[] STRING_REFERENCIA = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };
		final List<Integer> REFERENCIA = Arrays.asList(STRING_REFERENCIA);
		final Integer FRAME_SIMPLES = 3;
		final int RESULTADO_PAGE_FAULT = 9;

		Paginador otimo = new OPT(REFERENCIA, FRAME_SIMPLES);

		List<Pagina> resultadoGrafico = otimo.resultadoGraficoFinal();
		Assert.assertNotNull(resultadoGrafico);

		int pageFaults = otimo.totalPageFault();
		Assert.assertTrue(RESULTADO_PAGE_FAULT == pageFaults);

		List<Integer> stringRef = otimo.stringReferencia();
		Assert.assertEquals(REFERENCIA, stringRef);
	}
}
