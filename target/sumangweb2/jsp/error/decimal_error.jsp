<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculator</title>
</head>
<body>
	<section>
		<h1>Decimal Error</h1>
		<p>
			Status code:
			<c:out value="${requestScope['jakarta.servlet.error.status_code']}" />
		</p>

		<p>
			Error Message:
			<c:out value="${requestScope['jakarta.servlet.error.message']}" />
		</p>
		<a href="/sumangweb2/math/calc">Go back to form</a>
	</section>
</body>
</html>