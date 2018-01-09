<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Format16</title>
</head>
<body>
	<form method="post" enctype="multipart/form-data"
		action="Format16Servlet">
		<input name="format-16" type="File"> <input type="Submit"
			value="calculate"> 
		<input name="createDatabase" type="Submit" value="CreateTableOnDB">
		<input name = "addDatabase" type="Submit" value="addOnDB">
		<input name = "consol" type="Submit" value="fromConsol">
		<input type="text" size="100" value="${result16}">
	</form>
	<form action="index.jsp">
		<input type="Submit" value="Back to Menu">
	</form>
</body>
</html>