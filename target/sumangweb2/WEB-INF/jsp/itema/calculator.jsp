<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculator</title>
</head>
<body>
	<section>
		<form action="/sumangweb2/math/calc" method="POST"
			style="display:flex; flex-direction:column; gap:1em;">
			<div>
				<input type="text" name="operOne"/>
				<select name="operator">
					<option value="+">+</option>
					<option value="-">-</option>
					<option value="*">*</option>
					<option value="/">/</option>
				</select>
				<input type="text" name="operTwo"/>
			</div>
			<div>
				<input type="submit" value="Submit">
			</div>
		</form>
	</section>
</body>
</html>