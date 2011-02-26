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
	
	@Path("/sobre")
	@Get
	public void sobre(){
		
	}
	
	@Path("/escalonamento-processo")
	@Get
	public void escalonamentoProcesso(){
		
	}
	
	@Path("/escalonamento-disco")
	@Get
	public void escalonamentoDisco(){
		
	}
	
	@Path("/executar-escalonamento-processo")
	@Post
	public void executarEscalonamentoProcesso(List<Processo> processos){
		
	}

	@Path("/executar-escalonamento-disco")
	@Post
	public void executarEscalonamentoDisco(List<Processo> processos){
		
	}
	
}
