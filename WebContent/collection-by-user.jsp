<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Existing Collections</title>
</head>
<body>
<form method = "post" action = "listnavigationServlet">
<table>
<c:forEach items="${requestScope.allCollections}" var="currentcomic">
<tr>
	<td><input type = "radio" name="id" value="${currentcomic.collectionID}"></td>
	<td><h2>${currentcomic.collectionName}</h2></td></tr>
	<tr><td colspan="3">Collector: ${currentcomic.collector.collectorName}</td></tr>
		<c:forEach var = "listVal" items = "${currentcomic.listOfComics}">
			<tr><td></td><td colspan="3">
				${listVal.writer}, ${listVal.artist}, ${listVal.publisher}
				</td>
			</tr>
		</c:forEach>
	</c:forEach>
</table>
<input type = "submit" value = "edit" name="doThisToList">
<input type = "submit" value = "delete" name="doThisToList">
<input type = "submit" value = "add" name="doThisToList">
</form>
<a href="addComicsForCollectionsServlet">Create a new collection</a>
<a href="index.html">Insert a new comic</a>
</body>
</html>