<%@ page contentType="text/html; charset=UTF-8" %>
<footer>
	<p><a href="http://www.unisantos.br" target="_blank" title="Universidade Católica de Santos" rel="no-follow"><strong>Universidade Católica de Santos</strong></a></p>
 	<p><strong><fmt:message key="footer.orientador" />:</strong> André Luiz Vizine Pereira - <a href="mailto:vizine@unisantos.br" title="vizine@unisantos.br">vizine@unisantos.br</a></p>
 	<p><strong><fmt:message key="footer.aluno" />:</strong> André de Araújo Rodrigues - <a href="mailto:and.arodrigues@gmail.com" title="and.arodrigues@gmail.com">and.arodrigues@gmail.com</a></p>
 	<p><strong><fmt:message key="footer.aluno" />:</strong> Caio Ribeiro Pereira - <a href="mailto:caio.ribeiro.pereira@gmail.com" title="caio.ribeiro.pereira@gmail.com">caio.ribeiro.pereira@gmail.com</a></p>
 	<p><strong><fmt:message key="footer.curso" />:</strong> <fmt:message key="footer.curso.titulo" /></p>
 	<div class="row center"><a class="appengine" href="http://code.google.com/appengine/" title="<fmt:message key="footer.appengine" />" rel="no-follow"></a></div>
</footer>
<div id="notification-dialog" class="modal fade hide justify" title="<fmt:message key="main.painel.aviso" />">
	<div class="modal-header">
		<a href="#" class="close">x</a>
		<h3><fmt:message key="main.painel.aviso" /></h3>
	</div>
	<div class="modal-body justify">
		<p><fmt:message key="main.painel.msg1" /></p>
		<p><fmt:message key="main.painel.msg2" /></p>
		<p><fmt:message key="main.painel.msg4" />&nbsp;<a href="https://github.com/caio-ribeiro-pereira/SimulaRSO/issues" target="_blank" title="Github.com"><fmt:message key="main.painel.report.bug" /></a></p>
		<p><fmt:message key="main.painel.msg3" /></p>
		<div class="row center"><%@ include file="../templates/browsers.jsp"%></div>
	</div>
	<div class="modal-footer right">
		<button id="close-dialog" class="btn primary">ok</button>
	</div>
</div>
<script type="text/javascript">
	var _gaq = [];
	_gaq.push(['_setAccount', 'UA-7076509-2']);
	_gaq.push(['_trackPageview']);
	
	head.js('<c:url value="/resources/js/core.js" />');
	if(head.browser.ie && head.browser.version < parseFloat("9.0")){
		head.js('<c:url value="/resources/js/html5.min.js" />');
		$('#notification-dialog').modal('show');
	}
	$('#notification-dialog').modal({
		closeOnEscape: true
	});
	$('#close-dialog').click(function(){
		$('#notification-dialog').modal('hide');
	});
</script>