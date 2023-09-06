<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formation</title>
<base href="${pageContext.request.contextPath}/" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>edition formation</h1>

		<form:form action="formation" method="post" modelAttribute="formation">
			<div class="form-group">
				<form:label path="id">id:</form:label>
				<form:input path="id" class="form-control" readonly="true" />
			</div>
			<div class="form-group">
				<form:radiobuttons path="type" items="${types}" />
			</div>
			<div class="form-group">
				<form:label path="nom">nom:</form:label>
				<form:input path="nom" class="form-control" />
				<form:errors path="nom"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="description">description:</form:label>
				<form:textarea class="form-control" rows="15" path="description" />
			</div>
			<div class="form-group">
				<form:label path="date">date:</form:label>
				<form:input type="date" path="date" class="form-control" />
				<form:errors path="date"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="referent">referent:</form:label>
				<form:select path="referent.id" items="${formateurs}"
					itemLabel="infos" itemValue="id" class="form-control"></form:select>
				<form:errors path="referent"></form:errors>
			</div>


			<table class="table">
				<thead>
					<tr>
						<th>Liste des cours</th>
					</tr>
					<tr>
						<th>Matiere</th>
						<th>Date</th>
						<th>Animateur</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="co" items="${cours}">
						<tr>
							<td>${co.id.matiere.libelle}</td>
							<td>${co.date}</td>
							<td>${co.animateur.nom}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
				<button name="save" value="save2" type="submit" class="btn btn-primary">ajouter cours</button>
			<div class="form-group">
				<button name="save" value="save" type="submit" class="btn btn-primary">enregistrer</button>
				<a href="formation" class="btn btn-warning">annuler</a>
			</div>
		</form:form>
	</div>
</body>
</html>