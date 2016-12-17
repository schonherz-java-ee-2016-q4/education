
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="alert alert-info">
	<strong>getAuthType:</strong> ${pageContext.request.authType}
</div>

<div class="alert alert-info">
	<strong>isUserInRole("admin")</strong>
	${pageContext.request.isUserInRole('ADMIN')}
</div>

<div class="alert alert-info">
	<strong>getProtocol()</strong> ${pageContext.request.protocol}
</div>

<div class="alert alert-info">
	<strong>isSecure() </strong>${pageContext.request.secure}
</div>

<div class="alert alert-info">
	<strong>getUserPrincipal() </strong>${pageContext.request.userPrincipal}
</div>

<div class="alert alert-info">
	<strong>getUserPrincipal().getName(); </strong>
	<c:if test="${pageContext.request.userPrincipal!=null}">
		${pageContext.request.userPrincipal.name}
	</c:if>

</div>

<div class="alert alert-info">
	<strong>isRequestedSessionIdValid() </strong>${pageContext.request.requestedSessionIdValid}
</div>


<div class="alert alert-info">
	<strong>sec:authorize access="hasRole('USER') </strong>
	<sec:authorize access="hasRole('USER')">

		<h2>Hello user!</h2>

	</sec:authorize>
</div>
<div class="alert alert-info">
	<strong>authorize access="hasRole('MANAGER')"</strong>
	<sec:authorize access="hasRole('MANAGER')">

		<h2>Hello manger!</h2>

	</sec:authorize>
</div>

<div class="alert alert-info">
	<strong>authorize access="hasRole('ADMIN')</strong>
	<sec:authorize access="hasRole('ADMIN')">

		<h2>Hello admin!</h2>

	</sec:authorize>
</div>
<div class="alert alert-info">
	<strong> User name:</strong>
	<c:if test="${pageContext.request.userPrincipal!=null}">
		<sec:authentication property="principal.username" />
	</c:if>

</div>
<div class="alert alert-info">
	<strong> Password:</strong>
	<sec:authentication property="credentials" />
</div>
<div class="alert alert-info">
	<strong> Roles:</strong>
	<sec:authentication property="authorities" />
</div>

<div class="alert alert-info">
	<strong>authorize access="isRememberMe()"</strong>
	<sec:authorize access="isRememberMe()">
		<h1>isRememberMe()</h1>
	</sec:authorize>
</div>

<div class="alert alert-info">
	<strong>authorize access="!isRememberMe()"</strong>
	<sec:authorize access="!isRememberMe()">
		<h1>no isRememberMe()</h1>
	</sec:authorize>
</div>

