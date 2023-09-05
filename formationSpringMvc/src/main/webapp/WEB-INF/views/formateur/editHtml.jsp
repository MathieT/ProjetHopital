<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<form action="formateur" method="post">

			<div class="form-group">
				<label for="nom">nom:</label> <input id="nom" name="nom"
					class="form-control" value="${formateur.nom}">
				<c:if test="${erreurNom!=null}">
						*obligatoire
					</c:if>
			</div>
			<div class="form-group">
				<label for="experience">experience:</label> <input type="number"
					id="experience" name="experience" class="form-control"
					value="${formateur.experience }">
			</div>
			<div class="form-group">
				<label for="adresse.numero">numero:</label> <input
					id="adresse.numero" name="adresse.numero" class="form-control"
					value="${formateur.adresse.numero}">
			</div>
			<div class="form-group">
				<label for="adresse.rue">rue:</label> <input id="adresse.rue"
					name="adresse.rue" class="form-control"
					value="${formateur.adresse.rue }">
			</div>
			<div class="form-group">
				<label for="adresse.codePostal">code postal:</label> <input
					id="adresse.codePostal" name="adresse.codePostal"
					class="form-control" value="${formateur.adresse.codePostal }">
			</div>
			<div class="form-group">
				<label for="adresse.ville">ville:</label> <input id="adresse.ville"
					name="adresse.ville" class="form-control"
					value="${formateur.adresse.ville }">
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-outline-primary">enregistrer</button>
				<a href="formateur" class="btn btn-outline-warning">annuler</a>
			</div>
		</form>
	</div>
</body>
</html>