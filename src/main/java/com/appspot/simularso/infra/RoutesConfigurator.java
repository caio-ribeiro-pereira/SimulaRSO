package com.appspot.simularso.infra;

import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.http.route.RoutesConfiguration;
import br.com.caelum.vraptor.http.route.Rules;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.controller.DiscoController;
import com.appspot.simularso.controller.HomeController;
import com.appspot.simularso.controller.PaginacaoController;
import com.appspot.simularso.controller.ProcessoController;
import com.appspot.simularso.controller.SobreController;

@Component
@ApplicationScoped
public class RoutesConfigurator implements RoutesConfiguration {

	@Override
	public void config(Router rota) {
		new Rules(rota) {
			public void routes() {
				routeFor("").is(HomeController.class).home();
				routeFor("/").is(HomeController.class).home();
				routeFor("/escalonamento-processo").is(ProcessoController.class).processoInicio(null, null, 0, 0, 0);
				routeFor("/escalonamento-processo/").is(ProcessoController.class).processoInicio(null, null, 0, 0, 0);
				routeFor("/escalonamento-disco").is(DiscoController.class).discoInicio(null, null, null, 0, 0);
				routeFor("/escalonamento-disco/").is(DiscoController.class).discoInicio(null, null, null, 0, 0);
				routeFor("/paginacao-memoria").is(PaginacaoController.class).paginacaoInicio(null, null, null, 0, 0);
				routeFor("/paginacao-memoria/").is(PaginacaoController.class).paginacaoInicio(null, null, null, 0, 0);
				routeFor("/sobre").is(SobreController.class).sobre();
				routeFor("/sobre/").is(SobreController.class).sobre();
			}
		};
	}

}
