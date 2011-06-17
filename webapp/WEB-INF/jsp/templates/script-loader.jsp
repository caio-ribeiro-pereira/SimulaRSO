<%@ page contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="André de Araújo Rodrigues">
<meta name="author" content="Caio Ribeiro Pereira">
<meta name="description" content="SimulaRSO - Simulador de Recursos de Sistemas Operacionais">
<meta name="keywords" content="Projeto SimulaRSO, Simulador de Recursos de Sistemas Operacionais, Simulator Resources Operating Systems">
<meta name="msvalidate.01" content="8E5B78FB688E236FBD4D6659F8089AD6">
<meta name="y_key" content="3406b834d5e1d184">
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/favicon.ico" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset-grid.min.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dark-hive/jquery-ui.css" />" />
<script type="text/javascript" src="<c:url value="/resources/js/head.min.js" />"></script>
<script type="text/javascript">
	var _gaq = _gaq || [];
		_gaq.push(['_setAccount', 'UA-7076509-2']);
		_gaq.push(['_trackPageview']);
	if(head.browser.ie && head.browser.version !== "9.0"){
		head.js('<c:url value="/resources/js/ie/html5.js" />');
	}
	head.js('<c:url value="/resources/js/jquery-plugins.min.js" />')
		.js('http://www.google-analytics.com/ga.js');
	head.ready(function(){
		$('nav a.left').button({icons: {primary: 'ui-icon-home'}});
		$('nav a.right').button({icons: {primary: 'ui-icon-comment'}});
		$('nav a.middle').button({icons: {primary: 'ui-icon-triangle-1-e'}});
	});
</script>