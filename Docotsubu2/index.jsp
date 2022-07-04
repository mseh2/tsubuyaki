<%--http://localhost:8080/DocoTsubu2/ --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>つぶやき</title>
		</head>
		<body>
			<%
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/include/header.jsp");
			dispatcher.include(request, response);
			%>
			<form action="/DocoTsubu2/Login" method="post">
			UserID：<input type="text" name="name"><br>
			Pass：<input type="password" name="pass"><br>
			<input type="submit" value="Login">
			</form>
		</body>
	</html>