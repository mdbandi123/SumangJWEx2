<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookstore</title>
</head>
<body>
	<section>
		<form action="/sumangweb2/book/entry" method="POST" style="display:flex; flex-direction:column; gap:1em; width: 20%">
			<div style="display:flex ;justify-content:space-between">
				<label for="isbn">ISBN:</label> <input type="text" name="isbn" />
			</div>
			<div style="display:flex; justify-content:space-between">
				<label for="title">Title:</label> <input type="text" name="title" />
			</div>
			<div style="display:flex; justify-content:space-between">
				<label for="author">Author:</label> <input type="text" name="author" />
			</div>
			<div style="display:flex; justify-content:space-between">
				<label for="price">Price:</label> <input type="text" name="price" />
			</div>
			<div style="display:flex; justify-content:space-between">
				<label for="qty">Quantity:</label> <input type="text" name="qty" />
			</div>
			<input type="submit" value="Add Record"/>
		</form>
	</section>
</body>
</html>