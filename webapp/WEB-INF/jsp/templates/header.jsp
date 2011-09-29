<%@ page contentType="text/html; charset=UTF-8" %>
<header class="row top">
	<h1><fmt:message key="header.titulo" /></h1>
	<c:if test="${not empty warning}">
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