package com.appspot.simularso.logic.disc.test;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.appspot.simularso.logic.disc.FCFS;
import com.appspot.simularso.logic.test.InitialTestCase;
import com.appspot.simularso.model.Disco;

public class FCFSTest extends InitialTestCase {

	@Test
	public void deveEscalonarCorretamente() {
		final int[] RESULTADO = { 53, 98, 183, 37, 122, 14, 124, 65, 67 };
		final int[] CILINDROS = { 98, 183, 37, 122, 14, 124, 65, 67 };
		final int MOVIMENTO_TOTAL = 640;

		LinkedList<Disco> requisicoes = gerarListaDeRequisicoes(CILINDROS);
		Disco cabeca = new Disco(53);
		FCFS fcfs = new FCFS(requisicoes, cabeca, 1000);

		List<Disco> res = fcfs.resultados();
		Assert.assertNotNull(res);
		for (int i = 0; i < res.size(); i++) {
			Assert.assertTrue(res.get(i).getCilindro() == RESULTADO[i]);
		}

		Integer movimentoCilindros = fcfs.movimentoTotalCilindros();
		Assert.assertTrue(MOVIMENTO_TOTAL == movimentoCilindros);
	}
}
