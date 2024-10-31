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
	<c:set var="operOne" value="${ requestScope.operOne }"/>
	<c:set var="operTwo" value="${ requestScope.operTwo }"/>
	<c:set var="operator" value="${ requestScope.operator }"/>
	<c:set var="compVal" value="${ requestScope.compVal }"/>
	<section>
		<h1>The result of calculating 
			<c:out value="${ operOne }"/> 
			<c:out value="${ operator }"/> 
			<c:out value="${ operTwo }"/> is
			<c:out value="${ compVal }"/>
		</h1>
		<a href="/sumangweb2/math/calc">Back</a>
	</section>
</body>
</html>