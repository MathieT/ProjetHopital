<%@page import="java.util.List"%>
<%@page import="formation.entities.Formateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formation</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<base href="${pageContext.request.contextPath}/" />
</head>
<body>
	<div class="container">
		<h1>liste des formateurs</h1>
		<table class="table">
			<thead>
				<tr>
					<th>id:</th>
					<th>nom:</th>
					<th>experience:</th>
					<th>adresse</th>
					<th>code postal</th>
					<th>ville</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="f" items="${formateurs}">
					<tr>
						<td>${f.id }</td>
						<td>${f.nom}</td>
						<td>${f.experience}</td>
						<td>${f.adresse.numero}&nbsp;${f.adresse.rue}</td>
						<td>${f.adresse.codePostal}</td>
						<td>${f.adresse.ville}</td>
						<td><a href="formateur/edit?id=${f.id}"
							class="btn btn-outline-primary">editer</a></td>
						<td><a href="formateur/delete?id=${f.id}"
							class="btn btn-outline-danger">supprimer</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="formateur/add" class="btn btn-link">ajout formateur</a>
	</div>
</body>
</html>