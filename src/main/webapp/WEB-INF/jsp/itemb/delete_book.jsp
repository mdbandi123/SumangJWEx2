<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookstore</title>
</head>
<body>
	<c:set var="titles" value="${requestScope.titles}" />
	<section>
		<form action="/sumangweb2/book/delete" method="POST">
			<select name="titles">
				<c:forEach var="title" items="${titles}">
					<option><c:out value="${title}"/></option>
				</c:forEach>
			</select>
			<input type="submit" value="Delete"/>
		</form>
	</section>
</body>
</html>