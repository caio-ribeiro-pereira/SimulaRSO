package com.appspot.simularso.infra;

import com.appspot.simularso.controller.MainController;

import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.http.route.RoutesConfiguration;
import br.com.caelum.vraptor.http.route.Rules;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class RoutesConfigurator implements RoutesConfiguration{

	@Override
	public void config(Router rota) {
		new Rules(rota){
			public void routes(){
				routeFor("").is(MainController.class).home();
				routeFor("/").is(MainController.class).home();
			}
		};
	}

}
