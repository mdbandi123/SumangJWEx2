<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookstore</title>
</head>
<body>
	<section style="display: flex; flex-direction: column; gap:1.5em">
		<a href="/sumangweb2/jsp/book/add_book.html">
			<button type="button" style="padding: .5em 2em; font-size:1em">Insert Book</button>
		</a>
		<a href="/sumangweb2/book/handler/save">
			<button type="button" style="padding: .5em 2em; font-size:1em">List Books</button>
		</a>
		<a href="/sumangweb2/book/service/fileread">
			<button type="button" style="padding: .5em 2em; font-size:1em">Delete Book</button>
		</a>
	</section>
</body>
</html>