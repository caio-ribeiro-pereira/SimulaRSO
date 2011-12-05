<%@ page contentType="text/html; charset=UTF-8" %>
<header class="row top">
	<div style="float:left;position:absolute;margin: 25px 0 0 25px;">
		<img src="<c:url value="/resources/img/simularso-logo.png" />" width="54" height="48" border="0" alt="SimulaRSO" />
	</div>
	<h1 class="center">SimulaRSO</h1>
	<h1 class="center"><fmt:message key="header.titulo" /></h1>
	<c:if test="${not empty warning}">
		<br>
		<div class="alert-message warning">
			<a href="#" class="close">x</a>
			<p><strong>${warning}</strong></p>
		</div>
		<script type="text/javascript">
			head.ready(function(){
				$('.alert-message').alert();
			});
		</script>
	</c:if>
</header>