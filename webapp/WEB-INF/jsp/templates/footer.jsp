<%@ page contentType="text/html; charset=UTF-8" %>
<hr>
<footer class="row">
	<p><a href="http://www.unisantos.br" target="_blank" title="Universidade Católica de Santos" rel="no-follow"><strong>Universidade Católica de Santos</strong></a></p>
 	<p><strong><fmt:message key="footer.orientador" />:</strong> André Luiz Vizine Pereira - <a href="mailto:vizine@unisantos.br" title="vizine@unisantos.br">vizine@unisantos.br</a></p>
 	<p><strong><fmt:message key="footer.aluno" />:</strong> André de Araújo Rodrigues - <a href="mailto:and.arodrigues@gmail.com" title="and.arodrigues@gmail.com">and.arodrigues@gmail.com</a></p>
 	<p><strong><fmt:message key="footer.aluno" />:</strong> Caio Ribeiro Pereira - <a href="mailto:caio.ribeiro.pereira@gmail.com" title="caio.ribeiro.pereira@gmail.com">caio.ribeiro.pereira@gmail.com</a></p>
 	<p><strong><fmt:message key="footer.curso" />:</strong> <fmt:message key="footer.curso.titulo" /></p>
</footer>
<script type="text/javascript">
	var _gaq = [];
	_gaq.push(['_setAccount', 'UA-7076509-2']);
	_gaq.push(['_trackPageview']);
	
	head.js('<c:url value="/resources/js/jquery.min.js" />')
		.js('<c:url value="/resources/js/jquery-plugins.min.js" />')
		.js('<c:url value="/resources/js/bootstrap.min.js" />')
		.js('<c:url value="/resources/js/canvas/charts.js" />')
		.js('<c:url value="/resources/js/ga.min.js" />');
</script>