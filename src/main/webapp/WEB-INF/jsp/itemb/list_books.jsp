<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books</title>
</head>
<body>
	<c:set var="books" value="${requestScope.books}"></c:set>
	<section>
		<table>
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
			</tr>
			<c:forEach var="book" items="${books}">
				<tr>
					<td><c:out value="${book.isbn()}" /></td>
					<td><c:out value="${book.title()}" /></td>
					<td><c:out value="${book.author()}" /></td>
					<td><c:out value="${book.price()}" /></td>
					<td><c:out value="${book.qty()}" /></td>
				</tr>
			</c:forEach>
		</table>
	</section>
</body>
</html>
