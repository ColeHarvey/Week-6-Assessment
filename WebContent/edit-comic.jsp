<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action ="editComicServlet"method="post">
Writer: <input type ="text"name ="writer"value="${itemToEdit.writer}">
Artist: <input type ="text"name ="artist"value="${itemToEdit.artist}">
Publisher: <input type ="text"name ="publisher"value="${itemToEdit.publisher}">
<input type ="hidden"name ="id"value="${itemToEdit.id}">
<input type ="submit"value="Save Edited Comic">
</form>
</body>
</html>