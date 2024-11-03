<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	<section>
		<h1>Error Encountered</h1>
		<p>
			Status code:
			<c:out value="${requestScope['jakarta.servlet.error.status_code']}" />
		</p>

		<p>
			Error Message:
			<c:out value="${requestScope['jakarta.servlet.error.message']}" />
		</p>
		<a href="/sumangweb2/jsp/book/add_book.html">Go back to form</a>
	</section>
</body>
</html>