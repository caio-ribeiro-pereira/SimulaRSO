package com.appspot.simularso.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.logic.EscalonadorDisco;
import com.appspot.simularso.logic.disc.EscalonadorDiscoAlgoritmo;
import com.appspot.simularso.model.Disco;

@ApplicationScoped
@Component
public class DiscoFacade extends AlgoritmoFacade implements Serializable {

	private static final long serialVersionUID = 3077090130580923263L;

	public DiscoFacade() {
		super();
	}

	public ArrayList<HashMap<String, Object>> executar(final List<EscalonadorDiscoAlgoritmo> algs, final LinkedList<Disco> requisicoes,
			final Disco cabeca, final int modo) {

		ArrayList<HashMap<String, Object>> resultados = new ArrayList<HashMap<String, Object>>();

		for (EscalonadorDiscoAlgoritmo algoritmo : algs) {
			LinkedList<Disco> req = (LinkedList<Disco>) requisicoes.clone();

			Class<?> klass = new Mirror().reflectClass(super.extrairAlgoritmoNome(algoritmo));
			EscalonadorDisco escalonador = (EscalonadorDisco) new Mirror().on(klass).invoke().constructor().withArgs(req, cabeca);

			HashMap<String, Object> resultado = new HashMap<String, Object>();
			resultado.put("movimentoTotalCilindros", escalonador.movimentoTotalCilindros());
			resultado.put("resultados", escalonador.resultados());
			resultado.put("algoritmoNome", escalonador.algoritmoNome());
			resultado.put("totalRequisicoes", escalonador.getTotalRequisicoes());
			resultados.add(resultado);
		}

		resultados.trimToSize();
		return resultados;
	}
}