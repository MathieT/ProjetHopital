<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<h1>les formations</h1>
		<a href="formation/add" class="btn btn-link">nouvelle formation</a>
		<table class="table">
			<thead>
				<tr>
					<th>id:</th>
					<th>type</th>
					<th>nom</th>
					<th>date</th>
					<th>referent</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="f" items="${formations}">
					<tr>
						<td>${f.id}</td>
						<td>${f.type}</td>
						<td>${f.nom}</td>
						<td><fmt:parseDate value="${f.date}" pattern="yyyy-MM-dd"
								var="parseDate" type="date"></fmt:parseDate> <fmt:formatDate
								value="${parseDate}" pattern="dd/MM/yyyy" /></td>
						<td>${f.referent.nom}</td>
						<td><a href="formation/edit?id=${f.id}"
							class="btn btn-primary">editer</a></td>
						<td><a href="formation/delete?id=${f.id}"
							class="btn btn-danger">supprimer</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>