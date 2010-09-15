<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta name="mobile-web-app-capable" content="yes">
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0">
<meta name="keywords" content="">
<meta name="description" content="">
<title>My Web Application</title>
<link rel="shortcut icon" href="img/favicon.png">
<link rel="stylesheet"
	href="css/style.css?<%=System.currentTimeMillis()%>" />

</head>
<body>

	<header>
		<div>
			<h2>
				<c:out value="${requestScope.header_title}" />
			</h2>
		</div>
	</header>
	<div style="text-align: left; background-color: #f0f0f0">
		<strong>Menu contents </strong>
	</div>
	<nav>
		<ul>
			<li><a href="index.htm"><strong>Top page</strong></a></li>
			<li><a href="secret_page.htm"><strong>Secret page</strong></a></li>
			<c:choose>
				<c:when
					test="${!empty sessionScope.SESSION_KEY_IS_USER_LOGGED_IN && sessionScope.SESSION_KEY_IS_USER_LOGGED_IN==true}">
					<li><a href="logout.htm"><strong>Log out</strong></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="login.htm"><strong>Log in</strong></a></li>
				</c:otherwise>
			</c:choose>


		</ul>


	</nav>