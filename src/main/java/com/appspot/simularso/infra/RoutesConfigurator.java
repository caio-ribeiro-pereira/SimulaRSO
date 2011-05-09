package com.appspot.simularso.infra;

import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.http.route.RoutesConfiguration;
import br.com.caelum.vraptor.http.route.Rules;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import com.appspot.simularso.controller.HomeController;

@Component
@ApplicationScoped
public class RoutesConfigurator implements RoutesConfiguration{

	@Override
	public void config(Router rota) {
		new Rules(rota){
			public void routes(){
				routeFor("").is(HomeController.class).home();
				routeFor("/").is(HomeController.class).home();
			}
		};
	}

}
