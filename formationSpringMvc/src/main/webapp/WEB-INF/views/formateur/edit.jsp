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
		<h1>edition formateur</h1>
		<form:form action="formateur" method="post" modelAttribute="formateur">
			<form:hidden path="version" />
			<div class="form-group">
				<form:label path="id">id:</form:label>
				<form:input path="id" class="form-control" readonly="true"
					placeholder="generation auto" />
			</div>

			<div class="form-group">
				<form:label path="nom">nom:</form:label>
				<form:input path="nom" class="form-control" />
				<form:errors path="nom" element="div" cssClass="alert alert-danger"></form:errors>
				
			</div>
			<div class="form-group">
				<form:label path="experience">experience:</form:label>
				<form:input path="experience" class="form-control" />
				<form:errors path="experience"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="adresse.numero">numero:</form:label>
				<form:input path="adresse.numero" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.rue">rue:</form:label>
				<form:input path="adresse.rue" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.codePostal">code postal:</form:label>
				<form:input path="adresse.codePostal" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="adresse.ville">ville:</form:label>
				<form:input path="adresse.ville" class="form-control" />
				<form:errors path="adresse.ville"></form:errors>
			</div>
			<table class="table">
			<tbody>
				<c:forEach var="co" items="${competences}">
					<tr>
						<td>ici =${co.libelle}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
			<!-- <div><form:form action="competence/add" method="get" modelAttribute="formateur">
			<button type="submit" class="btn btn-outline-primary">ajouter competence</button></form:form></div> -->
			<a href="competence/add?id=${formateur.id}" class="btn btn-outline-warning">ajouter competence</a>
			
			<div class="form-group">
				<button type="submit" class="btn btn-outline-primary">enregistrer</button>
				<a href="formateur" class="btn btn-outline-warning">annuler</a>
			</div>
		</form:form>
		
	</div>
</body>
</html>