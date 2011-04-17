package com.appspot.simularso.logic.disc.test;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import com.appspot.simularso.exception.CilindroCabecaVaziaException;
import com.appspot.simularso.exception.RequisicaoCilindroException;
import com.appspot.simularso.exception.RequisicoesVaziaException;
import com.appspot.simularso.logic.disc.SSTF;
import com.appspot.simularso.logic.test.InitialTestCase;
import com.appspot.simularso.model.Disco;

public class SSTFTest extends InitialTestCase {

	@Test
	public void deveEscalonarCorretamente() {
		final int[] RESULTADO = { 53, 65, 67, 37, 14, 98, 122, 124, 183 };
		final int[] CILINDROS = { 98, 183, 37, 122, 14, 124, 65, 67 };
		final int MOVIMENTO_TOTAL = 177;

		LinkedList<Disco> requisicoes = gerarListaDeRequisicoes(CILINDROS);
		Disco cabeca = new Disco(53);
		SSTF sstf = new SSTF(requisicoes, cabeca, 1000);

		ArrayList<Disco> res = sstf.resultados();
		Assert.assertNotNull(res);
		for (int i = 0; i < res.size(); i++) {
			Assert.assertTrue(res.get(i).getCilindro() == RESULTADO[i]);
		}

		int movimentoCilindros = sstf.movimentoTotalCilindros();
		Assert.assertTrue(MOVIMENTO_TOTAL == movimentoCilindros);
	}

	@Test(expected = CilindroCabecaVaziaException.class)
	public void naoDeveEscalonarSemUmCilindroCabeca() {
		final int[] cilindros = { 98, 183, 37, 122, 14, 124, 65, 67 };
		new SSTF(gerarListaDeRequisicoes(cilindros), null);
	}

	@Test(expected = RequisicoesVaziaException.class)
	public void naoDeveEscalonarComAFilaDeRequisicoesVazia() {
		new SSTF(null, new Disco(10));
	}

	@Test(expected = RequisicaoCilindroException.class)
	public void naoDeveEscalonarComValoresDeCilindrosDaFilaNegativo() {
		final int[] cilindros = { 98, 183, -37, -122, 14, 124, 65, -67 };
		new SSTF(gerarListaDeRequisicoes(cilindros), new Disco(100));
	}
}
