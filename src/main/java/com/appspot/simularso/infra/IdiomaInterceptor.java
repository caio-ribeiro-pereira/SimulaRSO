package com.appspot.simularso.infra;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.appspot.simularso.controller.DiscoController;
import com.appspot.simularso.controller.PaginacaoController;
import com.appspot.simularso.controller.ProcessoController;

@Intercepts
@RequestScoped
public class IdiomaInterceptor implements Interceptor {

	private static final String HOME = "/";
	private final HttpServletRequest request;
	private Idioma idioma;

	public IdiomaInterceptor(HttpServletRequest request, Idioma idioma) {
		this.request = request;
		this.idioma = idioma;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return !Arrays.asList(ProcessoController.class, DiscoController.class, PaginacaoController.class).contains(
				method.getMethod().getDeclaringClass());
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object obj) throws InterceptionException {
		if (request.getServletPath().equals(HOME)) {
			String locale = request.getParameter("locale");
			idioma.setIdioma(locale);
		}
		stack.next(method, obj);
	}
}