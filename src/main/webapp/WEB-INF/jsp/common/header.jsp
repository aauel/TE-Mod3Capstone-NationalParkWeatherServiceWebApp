<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title><c:out value="National Park Geek Home"/></title>

	<link href="https://fonts.googleapis.com/css?family=Calligraffitti|Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <c:url var="cssUrl" value="/css/site.css"/>
    <link rel="stylesheet" href="${cssUrl}" />
</head>
<body>
	<header>
		<c:url var="headerImgURL" value="/img/logo.png"/>
		<img src="${headerImgURL}" alt="National Park Geek Logo" />
	</header>
	<nav>
		<c:url var="homeLink" value="/" />
		<c:url var="surveyLink" value="/survey" />
		<c:url var="favoritesLink" value="/favoriteParks" />
		<ul>
			<li><a href="${homeLink}">Home</a></li>
			<li><a href="${surveyLink}">Survey</a></li>
			<li><a href="${favoritesLink}">Favorite Parks</a></li>
		</ul>
	</nav>
