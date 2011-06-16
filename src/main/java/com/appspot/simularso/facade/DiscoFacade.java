package com.appspot.simularso.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.infra.EnumToClass;
import com.appspot.simularso.logic.EscalonadorDisco;
import com.appspot.simularso.logic.disc.EscalonadorDiscoAlgoritmo;
import com.appspot.simularso.model.Disco;
import com.appspot.simularso.validator.DiscoValidator;

@ApplicationScoped
@Component
public class DiscoFacade implements Serializable {

	private static final long serialVersionUID = 8239656513624501995L;
	private final EnumToClass enumToClass;
	private final DiscoValidator validator;

	public DiscoFacade(EnumToClass enumToClass, DiscoValidator validator) {
		this.enumToClass = enumToClass;
		this.validator = validator;
	}

	public ArrayList<HashMap<String, Object>> executar(final List<EscalonadorDiscoAlgoritmo> algs, final LinkedList<Disco> requisicoes,
			final Disco cabeca, final int modo) {

		validator.validarEntrada(algs, requisicoes, cabeca, modo);

		ArrayList<HashMap<String, Object>> resultados = new ArrayList<HashMap<String, Object>>();
		for (EscalonadorDiscoAlgoritmo alg : algs) {
			LinkedList<Disco> req = (LinkedList<Disco>) requisicoes.clone();
			HashMap<String, Object> resultado = gerarResultado(alg, req, cabeca);
			resultados.add(resultado);
		}

		resultados.trimToSize();
		return resultados;
	}

	private HashMap<String, Object> gerarResultado(EscalonadorDiscoAlgoritmo algoritmo, LinkedList<Disco> requisicoes, Disco cabeca) {
		Class<?> klass = new Mirror().reflectClass(enumToClass.extractClassFromEnum(algoritmo));
		EscalonadorDisco disco = (EscalonadorDisco) new Mirror().on(klass).invoke().constructor().withArgs(requisicoes, cabeca);
		return mapResultado(disco);
	}

	private HashMap<String, Object> mapResultado(EscalonadorDisco escalonador) {
		HashMap<String, Object> mapearResultados = new HashMap<String, Object>();
		mapearResultados.put("movimentoTotalCilindros", escalonador.movimentoTotalCilindros());
		mapearResultados.put("resultados", escalonador.resultados());
		mapearResultados.put("algoritmoNome", escalonador.algoritmoNome());
		mapearResultados.put("totalRequisicoes", escalonador.getTotalRequisicoes());
		return mapearResultados;
	}

}
