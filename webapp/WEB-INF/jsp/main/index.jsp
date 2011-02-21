<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="pt-BR"/>
<html lang="pt-BR">
<head>
    <title>Canvas tutorial</title>
  </head>
  <body>
  	<h2>SimulaEDP - Simulador de Escalonamento de Disco e Processos</h2>
    <canvas id="canvas" width="1080" height="500"></canvas>

	<p>Total de processos: 
		<input type="text" id="process_total" maxlength="2" size="4">
		<button id="setup">Setup</button>
	</p>
	<p id="process_setup"></p>
	<p>
		<button id="run">Run</button>
	</p>
	<script type="text/javascript" src="<c:url value="/js/jquery-1.5.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/html5.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/canvas-gantt.js" />"></script>
  </body>
</html>