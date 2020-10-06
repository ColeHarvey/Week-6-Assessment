<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit An Existing Collection</title>
</head>
<body>
<form action = "editCollectionDetailsServlet" method = "post">
<input type = "hidden" name = "id" value = "${listToEdit.id}">
Collection Name: <input type = "text" name = "listName" value = "${listToEdit.listName}"><br />

Trip Date: <input type = "text" name = "month" placeholder = "mm" size = "4" value = "${month}">
<input type = "text" name = "day" placeholder = "dd" size = "4" value = "${day}">
<input type = "text" name = "year" placeholder = "yyyy" size = "4" value = "${year}">

Collector Name: <input type = "text" name = "collectorName" value = "${listToEdit.collector.collectorName}"><br />

Available Items: <br />

<select name="allItemsToAdd" multiple size="6">
<c:forEach items="${requestScope.allItems}" var="currentitem">
	<option value = "${currentitem.id}">${currentitem.store} | ${currentitem.item}</option>
</c:forEach>
</select>
<br />
<input type = "submit" value="Edit List and Add Items">
</form>
<a href = "index.html">Go add new items instead.</a>
</body>
</html>