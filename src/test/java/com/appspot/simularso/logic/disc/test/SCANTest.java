package com.appspot.simularso.logic.disc.test;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import com.appspot.simularso.exception.CilindroCabecaVaziaException;
import com.appspot.simularso.exception.RequisicaoCilindroException;
import com.appspot.simularso.exception.RequisicoesVaziaException;
import com.appspot.simularso.logic.disc.SCAN;
import com.appspot.simularso.logic.test.InitialTestCase;
import com.appspot.simularso.model.Disco;

public class SCANTest extends InitialTestCase {
	@Test
	public void deveEscalonarCorretamente() {
		final int[] RESULTADO = { 53, 37, 14, 0, 65, 67, 98, 122, 124, 183 };
		final int[] CILINDROS = { 98, 183, 37, 122, 14, 124, 65, 67 };
		final int MOVIMENTO_TOTAL = 236;

		LinkedList<Disco> requisicoes = gerarListaDeRequisicoes(CILINDROS);
		Disco cabeca = new Disco(53);
		SCAN scan = new SCAN(requisicoes, cabeca,1000);

		ArrayList<Disco> res = scan.resultados();
		Assert.assertNotNull(res);
		for (int i = 0; i < res.size(); i++) {
			Assert.assertTrue(res.get(i).getCilindro() == RESULTADO[i]);
		}

		int movimentoCilindros = scan.movimentoTotalCilindros();
		Assert.assertTrue(MOVIMENTO_TOTAL == movimentoCilindros);
	}

	@Test(expected = CilindroCabecaVaziaException.class)
	public void naoDeveEscalonarSemUmCilindroCabeca() {
		final int[] cilindros = { 98, 183, 37, 122, 14, 124, 65, 67 };
		new SCAN(gerarListaDeRequisicoes(cilindros), null);
	}

	@Test(expected = RequisicoesVaziaException.class)
	public void naoDeveEscalonarComAFilaDeRequisicoesVazia() {
		new SCAN(null, new Disco(10));
	}

	@Test(expected = RequisicaoCilindroException.class)
	public void naoDeveEscalonarComValoresDeCilindrosDaFilaNegativo() {
		final int[] cilindros = { 98, 183, -37, -122, 14, 124, 65, -67 };
		new SCAN(gerarListaDeRequisicoes(cilindros), new Disco(100));
	}
}
