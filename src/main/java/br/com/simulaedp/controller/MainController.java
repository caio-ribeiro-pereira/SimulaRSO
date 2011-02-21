package br.com.simulaedp.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.simulaedp.model.Processo;

@Resource
public class MainController {

	@Path("/")
	@Get
	public void index(){
		
	}
	
	@Path("/executar")
	@Post
	public void executar(List<Processo> processos){
		
	}
	
}
