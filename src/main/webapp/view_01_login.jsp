<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="keywords" content="">
<meta name="description" content="">
<title>Example Login Screen</title>
<link rel="shortcut icon" href="img/favicon.png">
</head>
<body>
	<div>
		<h1>
			Login Form: <br />
		</h1>

		<form action="login.htm" method="post">
			<table>
				<tr>
					<td>Username</td>
					<td><input type="text" name="user_name" placeholder="username"
						value="${cusername}" /></td>
				</tr>

				<tr>
					<td>Password</td>
					<td><input type="password" name="password"
						placeholder="password" value="${cpassword}" /></td>
				</tr>
			</table>
			<button type="submit">login</button>
			<font color=red><br /> <c:out value=" ${loginMessage}" /></font> <br />



		</form>
	</div>
</body>
</html>