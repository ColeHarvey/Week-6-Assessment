<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create a new collection</title>
</head>
<body>
<form action = "createNewCollectionServlet" method="post">
List Name: <input type = "text" name = "collectionName"><br />
Collector Name: <input type = "text" name = "collectorName"><br />

Available Comics:<br />
<select name = "allComicsToAdd" multiple size="6">
<c:forEach items="${requestScope.allComics}" var="currentComic">
	<option value = "${currentComic.id}">${currentComic.writer} | ${currentComic.artist} | ${currentComic.publisher}</option>
</c:forEach>
</select>
<br />
<input type = "submit" value = "Create Collection and Add Comics">
</form>
<a href = "index.html"> Go add new comics instead.</a>
</body>
</html>