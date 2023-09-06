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
		<h1>edition cours</h1>
		<form:form action="cours" method="post" modelAttribute="cours">
			<form:hidden path="version" />
			<div class="form-group">
					<form:label path="id.formation">formation_id:</form:label>
					<form:input path="id.formation.id" class="form-control"
						readonly="true" value="${formation.id}" />
			</div>
			<div class="form-group">
				<form:label path="id.matiere">id.matiere:</form:label>
				<form:select path="id.matiere.id" items="${competences}"
					itemValue="id" itemLabel="libelle" class="form-control"></form:select>
			</div>

			<div class="form-group">
				<form:label path="date">date:</form:label>
				<form:input type="date" path="date" class="form-control" />
				<form:errors path="date"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="animateur">animateur:</form:label>
				<form:select path="animateur.id" items="${formateurs}"
					itemLabel="infos" itemValue="id" class="form-control"></form:select>
				<form:errors path="animateur"></form:errors>
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-outline-primary">enregistrer</button>
				<a href="formateur" class="btn btn-outline-warning">annuler</a>
			</div>
		</form:form>
	</div>
</body>
</html>