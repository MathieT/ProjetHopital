<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<base href="${pageContext.request.contextPath}/" />
<title>formation</title>
</head>
<body>
	<div class="container">
		<h1>edition competence</h1>
		<form:form action="competence" method="post"
			modelAttribute="competence">
			<form:hidden path="version" />
			<div class="form-group">
				<form:label path="id">id:</form:label>
				<form:input path="id" class="form-control" readonly="true"
					placeholder="generation auto" />
			</div>

			<div class="form-group">
				<form:label path="libelle">libelle:</form:label>
				<form:input path="libelle" class="form-control" />
				<form:errors path="libelle" element="div"
					cssClass="alert alert-danger"></form:errors>
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-outline-primary">enregistrer</button>
				<a href="formateur" class="btn btn-outline-warning">annuler</a>
			</div>
		</form:form>
	</div>
</body>
</html>