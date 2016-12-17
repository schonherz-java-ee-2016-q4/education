<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tiles:insertDefinition name="defaultLayout">
	<tiles:putAttribute name="header">Login</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<form class="form-horizontal" method="POST"
			action="<c:url value="/login"/>">
			<c:if test="${param.error}">
				<div class="alert alert-danger">Hibás felhasználónév vagy
					jelszó!</div>
			</c:if>

			<div class="form-group">
				<label class="control-label col-sm-5" for="j_username">Login</label>
				<div class="col-sm-7">
					<input type="text" name="j_username">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-5" for="j_password">Password</label>
				<div class="col-sm-7">
					<input type="password" name="j_password">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-5" for="j_password">Remember
					Me: </label>
				<div class="col-sm-7">
					<input type="checkbox" name="remember-me" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Login</button>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>