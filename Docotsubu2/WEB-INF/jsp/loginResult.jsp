<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="model.User" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
    
    <%
    User loginUser = (User)session.getAttribute("loginUser");
    %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン結果</title>
	</head>
	<body>
		<%if(loginUser != null){%>
			<p>ログインに成功しました。</p>
			<P>こんにちは、<c:out value="${loginUser.name }"/>さん</P>
			<a href="/DocoTsubu2/Main">投稿・閲覧はこちら</a><br>
			<a href="/DocoTsubu2/">TOPへ</a>
		<% } else { %>
			<p>ログインに失敗しました。</p>
			<a href="/DocoTsubu2/">TOPへ</a>
		<%} %>
	</body>
</html>